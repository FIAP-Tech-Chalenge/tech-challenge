package com.fiap.tech.application.controllers.cliente.pedido.get;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.pedido.BuscaPedidoOutput;
import com.fiap.tech.domain.presenters.cliente.pedido.GetPedidoPresenter;
import com.fiap.tech.domain.useCase.pedido.BuscaPedidoPorUuidUseCase;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import com.fiap.tech.infra.dependecy.StringValidatorsAdapter;
import com.fiap.tech.infra.dependecy.jwt.JWTDecodeAdapter;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("cliente/pedido")
public class AcompanharPedidoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    private static String resolveClienteAtual(String authorizationHeader, UUID clienteUuid) throws Exception {
        if (authorizationHeader != null) {
            return new JWTDecodeAdapter().claimClienteUuid(authorizationHeader);
        }
        if (clienteUuid != null) {
            return clienteUuid.toString();
        }

        return null;
    }

    @GetMapping("/{pedidoUuid}")
    @Operation(tags = {"cliente"}, parameters = {
            @Parameter(name = "Bearer", description = "Authorization header", in = ParameterIn.HEADER)
    })
    public ResponseEntity<Object> getPedido(
            @PathVariable UUID pedidoUuid,
            @RequestParam(name = "cliente_uuid", required = false) UUID queryParamClientUuid,
            HttpServletRequest request
    ) throws Exception {
        //HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String authorizationHeader = request.getHeader("Bearer");

        String uuidClientResolved = resolveClienteAtual(authorizationHeader, queryParamClientUuid);
        if (!StringValidatorsAdapter.isValidUUID(uuidClientResolved)) {
            throw new Exception("Token de identificação do cliente não encontrado");
        }

        BuscaPedidoPorUuidUseCase useCase = new BuscaPedidoPorUuidUseCase(new BuscarPedidoRepository(pedidoRepository, pedidoProdutoRepository));
        useCase.execute(pedidoUuid, UUID.fromString(uuidClientResolved));
        OutputInterface outputInterface = useCase.getBuscaPedidoOutput();
        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        GetPedidoPresenter presenter = new GetPedidoPresenter((BuscaPedidoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}


