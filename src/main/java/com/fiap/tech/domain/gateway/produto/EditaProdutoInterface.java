package com.fiap.tech.domain.gateway.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;

import java.util.UUID;

public interface EditaProdutoInterface {
    void editaProduto(Produto produto, UUID uuid) throws ProdutoNaoEncontradoException;
}
