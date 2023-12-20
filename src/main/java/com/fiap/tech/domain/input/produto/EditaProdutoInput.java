package com.fiap.tech.domain.input.produto;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;

import java.util.Date;

public record EditaProdutoInput(String nome, Float valor, String descricao, CategoriaEnum categoria, Integer quantidade, Date dataCriacao) {
}
