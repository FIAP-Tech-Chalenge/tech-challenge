package com.fiap.tech.domain.port.produto;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;

import java.util.List;
import java.util.UUID;

public interface BuscaProdutoInterface {
    Produto encontraProdutoPorUuid(UUID uuid) throws ProdutoNaoEncontradoException;

    List<Produto> findAll();

    List<Produto> encontraProdutoPorCategoria(CategoriaEnum categoria) throws ProdutoNaoEncontradoException;
}