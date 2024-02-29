package com.fiap.tech.infra.adpter.repository.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.port.pedido.PedidoInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.model.PedidoProdutoModel;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
public class CriaPedidoRepository implements PedidoInterface {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;


    @Override
    public Pedido criaPedido(Pedido pedido) throws ProdutoNaoEncontradoException {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setUuid(pedido.getUuid());
        Long newNumeroPedido = pedidoRepository.findMaxNumeroPedido() + 1;
        pedidoModel.setNumeroPedido(newNumeroPedido);
        pedidoModel.setValorTotal(pedido.valorTotalDoPedido());
        pedidoModel.setClienteId(pedido.getClienteUuid());
        pedidoModel.setStatusPedido(pedido.getStatusPedido());
        pedidoModel.setStatusPagamento(pedido.getStatusPagamento());
        pedidoModel.setDataCriacao(new Date());
        pedidoModel = pedidoRepository.save(pedidoModel);
        pedido.setUuid(pedidoModel.getUuid());

        for (com.fiap.tech.domain.entity.pedido.Produto produto : pedido.getProdutos()) {
            ProdutoModel produtoModel = produtoRepository.findByUuid(produto.getUuid());
            if (produtoModel != null) {
                int newQuantity = produtoModel.getQuantidade() - produto.getQuantidade();
                if (newQuantity < 0) {
                    throw new ProdutoNaoEncontradoException("O produto está sem estoque");
                }
                produtoModel.setQuantidade(newQuantity);

                PedidoProdutoModel pedidoProduto = new PedidoProdutoModel();
                pedidoProduto.setPedidoUuid(pedido.getUuid());
                pedidoProduto.setProdutoUuid(produto.getUuid());
                pedidoProduto.setQuantidade(produto.getQuantidade());
                pedidoProduto.setValor(produto.getValor());
                pedidoProduto.setCategoria(produto.getCategoria());
                pedidoProdutoRepository.save(pedidoProduto);
                produtoRepository.save(produtoModel);
            }
        }

        pedido.setUuid(pedidoModel.getUuid());
        pedido.setTotal(pedidoModel.getValorTotal());
        pedido.setNumeroPedido(pedidoModel.getNumeroPedido());
        return pedido;
    }

    @Override
    public Pedido atualizaPagamento(Pedido pedido, StatusPagamento statusPagamento) {
        PedidoModel pedidoModel = this.pedidoRepository.findByUuid(pedido.getUuid());
        pedidoModel.setStatusPagamento(statusPagamento);
        this.pedidoRepository.save(pedidoModel);
        pedido.setStatusPagamento(statusPagamento);
        return pedido;
    }
}