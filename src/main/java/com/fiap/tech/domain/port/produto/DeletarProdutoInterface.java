package com.fiap.tech.domain.port.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.infra.model.ProdutoModel;

import java.util.UUID;

public interface DeletarProdutoInterface {

    void deletaProduto(UUID uuid) throws ProdutoNaoEncontradoException;

    ProdutoModel encontraProdutoPorUuid(UUID uuid);

}
