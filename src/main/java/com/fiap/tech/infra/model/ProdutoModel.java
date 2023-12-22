package com.fiap.tech.infra.model;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid", updatable = false, nullable = false)
    private UUID uuid;
    private String nome;
    private Float valor;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;
    private Integer quantidade;

    public ProdutoModel(UUID uuid, String nome, Float valor, String descricao, CategoriaEnum categoria, Integer quantidade) {
        this.uuid = uuid;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }


}