package com.fiap.tech.application.controllers.admin.produtos.update.requests;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;

import java.util.Date;

public record UpdateProdutoRequest(
        String nome,
        Float valor,
        String descricao,
        CategoriaEnum categoria,
        Integer quantidade,
        Date dataCriacao) {
}
