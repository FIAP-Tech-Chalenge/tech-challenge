package com.fiap.tech.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public ProdutoModel(UUID uuid, String nome, Float valor) {
        this.uuid = uuid;
        this.nome = nome;
        this.valor = valor;
    }

}
