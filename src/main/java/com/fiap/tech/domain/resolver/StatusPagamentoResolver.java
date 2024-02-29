package com.fiap.tech.domain.resolver;

import com.fiap.tech.domain.enums.pedido.StatusPagamento;

import java.util.Objects;

public class StatusPagamentoResolver {
    public StatusPagamento resolve(String statusPagamento) {
        if (Objects.equals(statusPagamento, StatusPagamento.PAGO.name())) {
            return StatusPagamento.PAGO;
        }

        if (Objects.equals(statusPagamento, StatusPagamento.NAO_PAGO.name())) {
            return StatusPagamento.NAO_PAGO;
        }

        return StatusPagamento.AGUARDANDO_PAGAMENTO;
    }
}
