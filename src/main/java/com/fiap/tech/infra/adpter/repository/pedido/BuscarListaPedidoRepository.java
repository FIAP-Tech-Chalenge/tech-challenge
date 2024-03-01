package com.fiap.tech.infra.adpter.repository.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.gateway.pedido.BuscaListaPedidoInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.model.PedidoProdutoModel;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class BuscarListaPedidoRepository implements BuscaListaPedidoInterface {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @Override
    public List<Pedido> findListaPedidos() {
        List<PedidoModel> pedidosModels = pedidoRepository.findListaPedido();
        List<Pedido> pedidosEntities = new ArrayList<>();

        for (PedidoModel pedidoModel : pedidosModels) {
            Pedido pedidoEntity = new Pedido(
                    pedidoModel.getClienteId(),
                    pedidoModel.getStatusPedido(),
                    pedidoModel.getStatusPagamento(),
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
            pedidoEntity.setNumeroPedido(pedidoModel.getNumeroPedido());
            pedidosEntities.add(pedidoEntity);
        }
        return pedidosEntities;
    }
}
