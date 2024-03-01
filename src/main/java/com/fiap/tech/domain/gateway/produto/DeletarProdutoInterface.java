package com.fiap.tech.domain.gateway.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;

import java.util.UUID;

public interface DeletarProdutoInterface {

    void deletaProduto(UUID uuid) throws ProdutoNaoEncontradoException;

}
