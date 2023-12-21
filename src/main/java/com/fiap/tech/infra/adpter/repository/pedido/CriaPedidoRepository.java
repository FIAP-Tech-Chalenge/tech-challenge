package com.fiap.tech.infra.adpter.repository.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.tech.domain.port.pedido.PedidoInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class CriaPedidoRepository implements PedidoInterface {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido buscaPedido(UUID uuid) throws PedidoNaoEncontradoException {
        PedidoModel pedidoModel = pedidoRepository.findByUuid(uuid);
        if (pedidoModel == null) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
        List<Produto> produtos = getProdutos(pedidoModel.getProdutos());
        Pedido pedidoEntity = new Pedido(
                pedidoModel.getClienteId(),
                pedidoModel.getStatus(),
                produtos,
                pedidoModel.getValorTotal());
        pedidoEntity.setUuid(pedidoModel.getUuid()
        );
        return pedidoEntity;
    }

    private List<Produto> getProdutos(List<ProdutoModel> produtoModels) {
        List<Produto> produtos = new ArrayList<>();
        for (ProdutoModel produtoModel : produtoModels) {
            Produto produto = new Produto(
                produtoModel.getNome(),
                produtoModel.getValor(),
                produtoModel.getDescricao(),
                produtoModel.getCategoria(),
                produtoModel.getQuantidade()
            );
            produto.setUuid(produtoModel.getUuid());
            produtos.add(produto);
        }
        return produtos;
    }

    @Override
    public Pedido criaPedido(Pedido pedido) {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setUuid(pedido.getUuid());
        pedidoModel.setValorTotal(pedido.valorTotalDoPeido());
        pedidoModel.setClienteId(pedido.getClienteUuid());
        pedidoModel.setStatus(pedido.getStatus());
        pedidoModel.setDataCriacao(new Date());

        List<ProdutoModel> produtosDoPedido = new ArrayList<>();
        for (com.fiap.tech.domain.entity.pedido.Produto produto: pedido.getItens()) {
            ProdutoModel produtoModel = new ProdutoModel();
            produtoModel.setUuid(produto.getUuid());
            produtoModel.setQuantidade(produto.getQuantidade());
            produtosDoPedido.add(produtoModel);
        }
        pedidoModel = pedidoRepository.save(pedidoModel);


        pedido.setUuid(pedidoModel.getUuid());
        pedido.setTotal(pedidoModel.getValorTotal());
        return pedido;
    }
}
