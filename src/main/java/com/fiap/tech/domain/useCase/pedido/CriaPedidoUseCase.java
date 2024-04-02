package com.fiap.tech.domain.useCase.pedido;

import com.fiap.tech.domain.entity.pedido.Cliente;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.enums.pedido.StatusPedido;
import com.fiap.tech.domain.exception.pedido.ClienteNaoEncontradoException;
import com.fiap.tech.domain.exception.pedido.PedidoVazioException;
import com.fiap.tech.domain.exception.pedido.ProdutoDoPedidoSemQuantidadeException;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.input.pedido.CriarPedidoInput;
import com.fiap.tech.domain.input.pedido.ProdutoPedidoInput;
import com.fiap.tech.domain.output.pedido.CriaPedidoOutput;
import com.fiap.tech.domain.gateway.cliente.ClienteInterface;
import com.fiap.tech.domain.gateway.pedido.PedidoInterface;
import com.fiap.tech.domain.gateway.produto.BuscaProdutoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;

@Getter
@RequiredArgsConstructor
public class CriaPedidoUseCase {

    private final PedidoInterface pedidoInterface;
    private final ClienteInterface clienteInterface;
    private final BuscaProdutoInterface produtoInterface;
    private OutputInterface criaPedidoOutput;

    public void execute(CriarPedidoInput criarPedidoInput) {
        try {
            Cliente clienteEntity = clienteInterface.buscaClientePorUuid(criarPedidoInput.clienteUuid());
            Pedido pedidoEntity = new Pedido(clienteEntity.getUuid());
            pedidoEntity.setNumeroPedido(criarPedidoInput.numeroPedido());
            pedidoEntity.setStatusPedido(StatusPedido.RECEBIDO);
            pedidoEntity.setStatusPagamento(StatusPagamento.NAO_PAGO);
            pedidoEntity.setProdutos(new ArrayList<>());


            for ( ProdutoPedidoInput produto : criarPedidoInput.produtoList()) {
                var prod = produtoInterface.encontraProdutoPorUuid(produto.uuid());
                Produto produtoEntity = new Produto(prod.getUuid(), produto.quantidade(), prod.getCategoria());
                produtoEntity.setValor(prod.getValor());
                produtoEntity.setCategoria(prod.getCategoria());
                pedidoEntity.addProduto(produtoEntity);
            }

            pedidoEntity.verificaItensDoPedido();
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
        } catch (ProdutoDoPedidoSemQuantidadeException e) {
            this.criaPedidoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(422, "Unprocessable Entity", e.getMessage())
            );
        } catch (ClienteNaoEncontradoException | ProdutoNaoEncontradoException e) {
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

    /*private List<Produto> covertListaPedidosInput(List<ProdutoPedidoInput> produtoPedidoInputs) {
        List<Produto> produtos = new ArrayList<>();

        for (ProdutoPedidoInput produtoInput : produtoPedidoInputs) {
            produtos.add(new Produto(produtoInput.uuid(), produtoInput.quantidade()));
        }

        return produtos;
    }*/
}
