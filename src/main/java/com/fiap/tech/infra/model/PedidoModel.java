package com.fiap.tech.infra.model;

import com.fiap.tech.domain.enums.pedido.StatusPedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
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
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private UUID clienteId;
    private Date dataCriacao;
    @Enumerated(EnumType.STRING)
    private StatusPedido status;
    private Float valorTotal;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_uuid")
    private List<ProdutoModel> produtos;
}