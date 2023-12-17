package com.fiap.tech.domain.port.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.infra.model.ProdutoModel;

import java.util.Optional;

public interface DeletarProdutoInterface {

    void deletaProduto(String uuid) throws ProdutoNaoEncontradoException;

    Optional<ProdutoModel> encontraProdutoPorUuid(String uuid);

}
