package com.fiap.tech.domain.entity.produto;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.domain.exception.produto.CategoriaDeProdutoInvalidaException;

import java.util.List;

public record CategoriaProtudo(String categoriaEnum) {

    public CategoriaEnum validaCategoriaEnum() throws Exception {
        for (CategoriaEnum enumValue : CategoriaEnum.values()) {
            if (enumValue.name().equalsIgnoreCase(categoriaEnum)) {
                return enumValue;
            }
        }

        throw new CategoriaDeProdutoInvalidaException("Categoria inválida: " + categoriaEnum);
    }
}
