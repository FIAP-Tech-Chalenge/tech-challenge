package com.fiap.tech.domain.port.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.infra.model.ProdutoModel;

import java.util.UUID;

public interface EditaProdutoInterface {
    ProdutoModel encontraProdutoPorUuid(UUID uuid);
    ProdutoModel editaProduto(ProdutoModel produtoModel) throws ProdutoNaoEncontradoException;
}
