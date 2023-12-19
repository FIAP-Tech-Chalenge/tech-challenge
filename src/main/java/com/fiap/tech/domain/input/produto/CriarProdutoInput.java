package com.fiap.tech.domain.input.produto;


import com.fiap.tech.domain.entity.produto.Categoria;

import java.util.Date;

public record CriarProdutoInput(String nome, Float valor, String descricao, Categoria categoria, Integer quantidade, Date dataCriacao) {
}
