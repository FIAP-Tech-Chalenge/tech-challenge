package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.port.produto.DeletarProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class DeletaProdutoRepository implements DeletarProdutoInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public void deletaProduto(UUID uuid) throws ProdutoNaoEncontradoException {
        ProdutoModel produtoModel = this.produtoRepository.findByUuid(uuid);
        if (produtoModel == null) {
            throw new ProdutoNaoEncontradoException("Produto with UUID " + uuid + " not found.");
        }
        this.produtoRepository.delete(produtoModel);
    }

    @Override
    public ProdutoModel encontraProdutoPorUuidDeleta(UUID uuid) {
        return this.produtoRepository.findByUuid(uuid);
    }
}