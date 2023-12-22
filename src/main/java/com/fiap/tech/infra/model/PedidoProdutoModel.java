package com.fiap.tech.infra.model;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
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
@Table(name ="pedido_produtos")
public class PedidoProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_produtos_seq")
    @SequenceGenerator(name = "pedido_produtos_seq", sequenceName = "pedido_produtos_seq", allocationSize = 1)
    private Long id;
    private Float valor;
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    private UUID produtoUuid;

//    @JoinColumn(name = "pedido_uuid", table = "pedidos", referencedColumnName = "uuid")
    private UUID pedidoUuid;
/*
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_uuid")
    private List<ProdutoModel> produtos;*/

}