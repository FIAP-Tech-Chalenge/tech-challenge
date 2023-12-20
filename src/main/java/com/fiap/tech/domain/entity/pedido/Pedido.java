package com.fiap.tech.domain.entity.pedido;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.util.UUID;

public class Pedido {
        private UUID uuid;
        private Long clienteId;
        private String dataCriacao;
        @Enumerated(EnumType.STRING)
        private StatusPedido status;
        private String itens;
        private double valorTotal;

        public Pedido(UUID uuid, Long clienteId, String dataCriacao, StatusPedido status, String itens, double valorTotal) {
            this.uuid = uuid;
            this.clienteId = clienteId;
            this.dataCriacao = dataCriacao;
            this.status = status;
            this.itens = itens;
            this.valorTotal = valorTotal;
        }

}
