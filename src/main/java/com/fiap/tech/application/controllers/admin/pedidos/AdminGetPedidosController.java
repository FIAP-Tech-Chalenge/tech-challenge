package com.fiap.tech.application.controllers.admin.pedidos;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.tech.domain.presenters.cliente.pedido.GetPedidosPresenter;
import com.fiap.tech.domain.useCase.pedido.BuscaTodosPedidosUseCase;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/pedido")
public class AdminGetPedidosController {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @GetMapping("/todos-pedidos")
    @Operation(tags = {"admin"})
    public ResponseEntity<Object> getAllPedidos() {
        BuscaTodosPedidosUseCase useCase = new BuscaTodosPedidosUseCase(new BuscarPedidoRepository(pedidoRepository, pedidoProdutoRepository));
        useCase.execute();
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        GetPedidosPresenter presenter = new GetPedidosPresenter((BuscaTodosPedidoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}


