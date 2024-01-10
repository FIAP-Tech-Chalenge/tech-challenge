package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import lombok.Getter;

import java.util.UUID;
@Getter
public class Checkout {
    private final StatusPagamento statusPagamento;
    private final UUID uuidPedido;

    public Checkout(UUID uuidPedido, StatusPagamento statusPagamento) {
        this.uuidPedido = uuidPedido;
        this.statusPagamento = statusPagamento;
    }
}
