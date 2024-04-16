package com.fiap.tech.application.controllers.produto.store.requests;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;

import java.util.Date;

public record StoreProdutoRequest(
        String nome,
        Float valor,
        String descricao,
        CategoriaEnum categoria,
        Integer quantidade,
        Date dataCriacao
) {
}
