package com.fiap.tech.application.controllers.admin.pedidos;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.pedido.BuscaPedidoOutput;
import com.fiap.tech.domain.presenters.cliente.pedido.GetPedidoPresenter;
import com.fiap.tech.domain.useCase.pedido.BuscaPedidoPorUuidUseCase;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping("admin/pedido")
public class AdminAcompanharPedidoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @GetMapping("/{uuid}")
    @Operation(tags = {"admin"})
    public ResponseEntity<Object> getPedido(@PathVariable UUID uuid) {
        BuscaPedidoPorUuidUseCase useCase = new BuscaPedidoPorUuidUseCase(new BuscarPedidoRepository(pedidoRepository, pedidoProdutoRepository));
        useCase.execute(uuid, null);
        OutputInterface outputInterface = useCase.getBuscaPedidoOutput();
        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        GetPedidoPresenter presenter = new GetPedidoPresenter((BuscaPedidoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}


