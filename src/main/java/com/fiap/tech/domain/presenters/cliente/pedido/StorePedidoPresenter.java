package com.fiap.tech.domain.presenters.cliente.pedido;

import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.pedido.CriaPedidoOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StorePedidoPresenter implements PresenterInterface {
    private final CriaPedidoOutput criaPedidoOutput;

    public StorePedidoPresenter(CriaPedidoOutput criaPedidoOutput) {
        this.criaPedidoOutput = criaPedidoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid_pedido", this.criaPedidoOutput.getPedido().getUuid());
        array.put("status_pagamento", this.criaPedidoOutput.getPedido().getStatusPagamento());
        array.put("numero_pedido", this.criaPedidoOutput.getPedido().getNumeroPedido());
        array.put("total", this.criaPedidoOutput.getPedido().getTotal());
        array.put("cliente_uuid", this.criaPedidoOutput.getPedido().getClienteUuid());
        array.put("status_pedido", this.criaPedidoOutput.getPedido().getStatusPedido());
        List<Map<String, Object>> produtosMapList = new ArrayList<>();
        for (Produto produto : this.criaPedidoOutput.getPedido().getProdutos()) {
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

    public CriaPedidoOutput getOutput() {
        return this.criaPedidoOutput;
    }
}