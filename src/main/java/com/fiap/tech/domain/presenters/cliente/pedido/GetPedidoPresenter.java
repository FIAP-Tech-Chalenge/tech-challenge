package com.fiap.tech.domain.presenters.cliente.pedido;

import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.pedido.BuscaPedidoOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetPedidoPresenter implements PresenterInterface {
    private final BuscaPedidoOutput buscaPedidoOutput;

    public GetPedidoPresenter(BuscaPedidoOutput buscaPedidoOutput) {
        this.buscaPedidoOutput = buscaPedidoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid_pedido", this.buscaPedidoOutput.getPedido().getUuid());
        array.put("status_pagamento", this.buscaPedidoOutput.getPedido().getStatusPagamento());
        array.put("numero_pedido", this.buscaPedidoOutput.getPedido().getNumeroPedido());
        array.put("total", this.buscaPedidoOutput.getPedido().getTotal());
        array.put("cliente_uuid", this.buscaPedidoOutput.getPedido().getClienteUuid());
        array.put("status_pedido", this.buscaPedidoOutput.getPedido().getStatusPedido());
        List<Map<String, Object>> produtosMapList = new ArrayList<>();
        for (Produto produto : this.buscaPedidoOutput.getPedido().getProdutos()) {
            Map<String, Object> produtoMap = new HashMap<>();
            produtoMap.put("uuid", produto.getUuid().toString());
            produtoMap.put("quantidade", produto.getQuantidade());
            produtoMap.put("valor", produto.getValor());
            produtoMap.put("categoria", produto.getCategoria().toString());
            produtosMapList.add(produtoMap);
        }
        array.put("produtos", produtosMapList);
        return array;
    }

    public BuscaPedidoOutput getOutput() {
        return this.buscaPedidoOutput;
    }
}