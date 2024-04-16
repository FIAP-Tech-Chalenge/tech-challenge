package com.fiap.tech.domain.presenters.cliente.pagamento;

import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.pedido.CheckOutOutput;

import java.util.HashMap;
import java.util.Map;

public class ProcessarPagamentoPresenter implements PresenterInterface {
    private final CheckOutOutput statusPagamentoOutput;

    public ProcessarPagamentoPresenter(CheckOutOutput statusPagamentoOutput) {
        this.statusPagamentoOutput = statusPagamentoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("status_pagamento", this.statusPagamentoOutput.getCheckout().getStatusPagamento());
        cliente.put("qr_code", this.statusPagamentoOutput.getCheckout().getQrCode());
        cliente.put("uuid_pedido", this.statusPagamentoOutput.getCheckout().getUuidPedido());
        return cliente;
    }

    public CheckOutOutput getOutput() {
        return this.statusPagamentoOutput;
    }
}
