package com.fiap.tech.application.controllers.pedido.list;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.tech.domain.presenters.cliente.pedido.GetPedidosPresenter;
import com.fiap.tech.domain.useCase.pedido.BuscaListaPedidosUseCase;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarListaPedidoRepository;
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
@RequestMapping("/pedido")
public class ListPedidosController {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @GetMapping("/lista-pedido")
    @Operation(tags = {"pedido"})
    public ResponseEntity<Object> getAllListaPedidos() {
        BuscaListaPedidosUseCase useCase = new BuscaListaPedidosUseCase(new BuscarListaPedidoRepository(pedidoRepository, pedidoProdutoRepository));
        useCase.execute();
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        GetPedidosPresenter presenter = new GetPedidosPresenter((BuscaTodosPedidoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}


