package com.fiap.tech.domain.input.produto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CriarProdutoInput {
    private final Float valor;
    private final String nome;

    public CriarProdutoInput(String nome, Float valor) {
        this.nome = nome;
        this.valor = valor;
    }
}
