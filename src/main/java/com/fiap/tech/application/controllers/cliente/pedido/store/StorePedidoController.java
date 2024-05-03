package com.fiap.tech.application.controllers.cliente.pedido.store;

import com.fiap.tech.application.controllers.cliente.pedido.store.requests.StorePedidoRequest;
import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.input.pedido.CriarPedidoInput;
import com.fiap.tech.domain.output.pedido.CriaPedidoOutput;
import com.fiap.tech.domain.presenters.cliente.pedido.StorePedidoPresenter;
import com.fiap.tech.domain.useCase.pedido.CriaPedidoUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.ClienteEntityRepository;
import com.fiap.tech.infra.adpter.repository.pedido.CriaPedidoRepository;
import com.fiap.tech.infra.adpter.repository.produto.BuscarProdutoRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("cliente/pedido")
public class StorePedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @PostMapping
    @Operation(tags = {"cliente"})
    ResponseEntity<Object> criaPedido(@RequestBody StorePedidoRequest criarProdutoRequest) {
        CriarPedidoInput criaPedidoInput = new CriarPedidoInput(
                criarProdutoRequest.clienteUuid(),
                criarProdutoRequest.produtoList(),
                criarProdutoRequest.numeroPedido()
        );
        CriaPedidoUseCase useCase = new CriaPedidoUseCase(
                new CriaPedidoRepository(pedidoRepository, produtoRepository, pedidoProdutoRepository),
                new ClienteEntityRepository(clienteRepository),
                new BuscarProdutoRepository(produtoRepository)
        );
        useCase.execute(criaPedidoInput);
        OutputInterface outputInterface = useCase.getCriaPedidoOutput();
        if (outputInterface.getOutputStatus().getCode() != 201) {
            return new GenericResponse().response(outputInterface);
        }
        StorePedidoPresenter presenter = new StorePedidoPresenter((CriaPedidoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}


