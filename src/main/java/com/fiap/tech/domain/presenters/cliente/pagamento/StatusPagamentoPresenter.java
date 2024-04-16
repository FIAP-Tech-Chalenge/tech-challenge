package com.fiap.tech.domain.presenters.cliente.pagamento;

import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.pagamento.StatusPagamentoOutput;

import java.util.HashMap;
import java.util.Map;

public class StatusPagamentoPresenter implements PresenterInterface {
    private final StatusPagamentoOutput statusPagamentoOutput;

    public StatusPagamentoPresenter(StatusPagamentoOutput statusPagamentoOutput) {
        this.statusPagamentoOutput = statusPagamentoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> cliente = new HashMap<>();
        cliente.put("status_pagamento", this.statusPagamentoOutput.getStatusPagamento());
        return cliente;
    }

    public StatusPagamentoOutput getOutput() {
        return this.statusPagamentoOutput;
    }
}
