package com.fiap.tech.infra.model;

import com.fiap.tech.domain.entity.pedido.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="pedido")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private Long clienteId;
    private String dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private String itens;
    private double valorTotal;

}
