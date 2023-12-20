package com.fiap.tech.infra.model;

import com.fiap.tech.domain.entity.produto.Categoria;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String nome;
    private Float valor;
    private String descricao;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private Integer quantidade;

    public ProdutoModel(UUID uuid, String nome, Float valor, String descricao, Categoria categoria, Integer quantidade) {
        this.uuid = uuid;
        this.nome = nome;
        this.valor = valor;
        this.descricao = descricao;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

}