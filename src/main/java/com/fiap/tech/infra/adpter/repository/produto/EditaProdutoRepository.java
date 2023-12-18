package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.port.produto.EditaProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
public class EditaProdutoRepository implements EditaProdutoInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoModel encontraProdutoPorUuid(UUID uuid) {
        return this.produtoRepository.findByUuid(uuid);
    }

    public ProdutoModel editaProduto(ProdutoModel produtoModel) throws ProdutoNaoEncontradoException {
        ProdutoModel produtoExistente = this.produtoRepository.findByUuid(produtoModel.getUuid());
        if (produtoExistente == null) {
            throw new ProdutoNaoEncontradoException("Produto with UUID " + produtoModel.getUuid() + " not found.");
        }
        return produtoRepository.save(produtoModel);
    }

}