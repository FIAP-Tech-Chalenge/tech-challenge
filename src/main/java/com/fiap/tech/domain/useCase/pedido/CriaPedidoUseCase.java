package com.fiap.tech.domain.useCase.pedido;

import com.fiap.tech.domain.entity.pedido.Cliente;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.enums.pedido.StatusPedido;
import com.fiap.tech.domain.exception.pedido.ClienteNaoEncontradoException;
import com.fiap.tech.domain.exception.pedido.PedidoVazioException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.input.pedido.CriarPedidoInput;
import com.fiap.tech.domain.input.pedido.ProdutoPedidoInput;
import com.fiap.tech.domain.output.pedido.CriaPedidoOutput;
import com.fiap.tech.domain.port.cliente.ClienteInterface;
import com.fiap.tech.domain.port.pedido.PedidoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class CriaPedidoUseCase {

    private final PedidoInterface pedidoInterface;
    private final ClienteInterface clienteInterface;
    private OutputInterface criaPedidoOutput;

    public void execute(CriarPedidoInput criarPedidoInput) {
        try {
            Cliente clienteEntity = clienteInterface.buscaClientePorUuid(criarPedidoInput.clienteUuid());
            Pedido pedidoEntity = new Pedido(clienteEntity.getUuid());
            pedidoEntity.setItens(this.covertListaPedidosInput(criarPedidoInput.produtoList()));
            pedidoEntity.verificaItensDoPedido();
            pedidoEntity.setStatus(StatusPedido.RECEBIDO);

            pedidoEntity = pedidoInterface.criaPedido(pedidoEntity);


            this.criaPedidoOutput = new CriaPedidoOutput(
                pedidoEntity,
                new OutputStatus(201, "Created", "Pedido criado")
            );

        } catch (PedidoVazioException e) {
            this.criaPedidoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(400, "Bad Request", e.getMessage())
            );
        } catch (ClienteNaoEncontradoException e) {
            this.criaPedidoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(404, "Not Found", e.getMessage())
            );
        } catch (Exception e) {
            this.criaPedidoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(500, "Internal Error", e.getMessage())
            );
        }
    }

    private List<Produto> covertListaPedidosInput(List<ProdutoPedidoInput> produtoPedidoInputs) {
        List<Produto> produtos = new ArrayList<>();

        for (ProdutoPedidoInput produtoInput : produtoPedidoInputs) {
            produtos.add(new Produto(produtoInput.uuid(), produtoInput.quantidade()));
        }

        return produtos;
    }
}
