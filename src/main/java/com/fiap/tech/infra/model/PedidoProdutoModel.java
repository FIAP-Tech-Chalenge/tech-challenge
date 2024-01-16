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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float valor;
    private Integer quantidade;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    private UUID produtoUuid;
    private UUID pedidoUuid;

}