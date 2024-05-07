package com.fiap.tech.domain.input.produto;


import com.fiap.tech.domain.entity.produto.Imagem;
import com.fiap.tech.domain.enums.produto.CategoriaEnum;

import java.util.Date;
import java.util.List;

public record CriarProdutoInput(
        String nome,
        Float valor,
        String descricao,
        CategoriaEnum categoria,
        Integer quantidade,
        Date dataCriacao,
        List<Imagem> imagens
) {
}
