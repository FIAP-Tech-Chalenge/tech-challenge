package com.fiap.tech.infra.model;

import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.enums.pedido.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="pedidos")
public class PedidoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    @Column(name = "clienteid")
    private UUID clienteId;
    @Column(name = "datacriacao")
    private Date dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusPedido statusPedido;
    @Enumerated(EnumType.STRING)
    private StatusPagamento statusPagamento;
    @Column(name = "valortotal")
    private Float valorTotal;

}

