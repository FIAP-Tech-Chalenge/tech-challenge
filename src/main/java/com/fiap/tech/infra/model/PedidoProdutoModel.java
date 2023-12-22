package com.fiap.tech.infra.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="pedido_produtos")
public class PedidoProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Float valor;
    private Integer quantidade;

    private UUID produtoUuid;

//    @JoinColumn(name = "pedido_uuid", table = "pedidos", referencedColumnName = "uuid")
    private UUID pedidoUuid;
/*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_uuid")
    private List<ProdutoModel> produtos;*/

}