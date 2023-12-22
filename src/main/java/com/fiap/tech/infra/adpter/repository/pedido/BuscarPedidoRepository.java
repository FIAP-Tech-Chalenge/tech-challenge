package com.fiap.tech.infra.adpter.repository.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.port.pedido.BuscaPedidoInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.model.PedidoProdutoModel;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BuscarPedidoRepository implements BuscaPedidoInterface {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @Override
    public List<Pedido> findAll() {
        List<PedidoModel> pedidosModels = pedidoRepository.findAll();
        List<Pedido> pedidosEntities = new ArrayList<>();

        for (PedidoModel pedidoModel : pedidosModels) {
            Pedido pedidoEntity = new Pedido(
                    pedidoModel.getClienteId(),
                    pedidoModel.getStatus(),
                    pedidoModel.getValorTotal()
            );

            List<PedidoProdutoModel> pedidosDoProduto = pedidoProdutoRepository.findPedidoProdutoModelsByPedidoUuid(pedidoModel.getUuid());
            List<Produto> produtosList = new ArrayList<>();
            for (PedidoProdutoModel pedidoProdutoModel : pedidosDoProduto) {
                Produto produtoEntity = new Produto(pedidoProdutoModel.getPedidoUuid(), pedidoProdutoModel.getQuantidade(), pedidoProdutoModel.getCategoria());
                produtoEntity.setValor(pedidoProdutoModel.getValor());
                produtosList.add(produtoEntity);
            }
            pedidoEntity.setProdutos(produtosList);

            pedidoEntity.setUuid(pedidoModel.getUuid());
            pedidosEntities.add(pedidoEntity);
        }
        return pedidosEntities;
    }

    @Override
    public Pedido encontraPedidoPorUuid(UUID uuid) {
        /*PedidoModel pedidoModel = pedidoRepository.findByUuid(uuid);
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
        pedidoEntity.setUuid(pedidoModel.getUuid());*/

        return new Pedido(uuid);
    }
}