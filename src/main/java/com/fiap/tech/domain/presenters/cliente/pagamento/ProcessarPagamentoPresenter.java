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
        Map<String, Object> array = new HashMap<>();
        array.put("status_pagamento", this.statusPagamentoOutput.getCheckout().getStatusPagamento());
        array.put("qr_code", this.statusPagamentoOutput.getCheckout().getQrCode());
        array.put("uuid_pedido", this.statusPagamentoOutput.getCheckout().getUuidPedido());
        return array;
    }

    public CheckOutOutput getOutput() {
        return this.statusPagamentoOutput;
    }
}
