package com.fiap.tech.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="produtos")
public class ProdutoModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String uuid;
    private String nome;
    private Float valor;

    public ProdutoModel(String nome, Float valor, String uuid) {
        this.nome = nome;
        this.valor = valor;
        this.uuid = uuid;
    }

}
