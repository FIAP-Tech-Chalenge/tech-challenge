package com.fiap.tech.infra.adpter.repository.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.port.pedido.BuscaPedidoInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BuscarPedidoRepository implements BuscaPedidoInterface {

    private final PedidoRepository pedidoRepository;

    @Override
    public List<Pedido> findAll() {
        List<PedidoModel> pedidosModels = pedidoRepository.findAll();
        List<Pedido> pedidosEntities = new ArrayList<>();

        for (PedidoModel pedidoModel : pedidosModels) {
            List<Produto> produtos = getProdutos(pedidoModel.getProdutos());
            Pedido pedidoEntity = new Pedido(
                    pedidoModel.getClienteId(),
                    pedidoModel.getStatus(),
                    produtos,
                    pedidoModel.getValorTotal());
            pedidoEntity.setUuid(pedidoModel.getUuid());
            pedidosEntities.add(pedidoEntity);
        }

        return pedidosEntities;
    }

    @Override
    public Pedido encontraPedidoPorUuid(UUID uuid) {
        PedidoModel pedidoModel = pedidoRepository.findByUuid(uuid);
        if (pedidoModel == null) {
            return null;
        }
        List<Produto> produtos = getProdutos(pedidoModel.getProdutos());
        Pedido pedidoEntity = new Pedido(
                pedidoModel.getClienteId(),
                pedidoModel.getStatus(),
                produtos,
                pedidoModel.getValorTotal()
        );
        pedidoEntity.setUuid(pedidoModel.getUuid());
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
}