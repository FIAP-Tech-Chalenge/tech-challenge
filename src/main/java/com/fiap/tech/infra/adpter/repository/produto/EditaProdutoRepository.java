package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.gateway.produto.EditaProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;


@RequiredArgsConstructor
public class EditaProdutoRepository implements EditaProdutoInterface {

    private final ProdutoRepository produtoRepository;

    public void editaProduto(Produto produto, UUID uuid) throws ProdutoNaoEncontradoException {
        ProdutoModel produtoExistente = this.produtoRepository.findByUuid(uuid);
        if (produtoExistente == null) {
            throw new ProdutoNaoEncontradoException("Produto with UUID " + uuid + " not found.");
        }
        produtoRepository.save(new ProdutoModel(produto.getUuid(), produto.getNome(), produto.getValor(), produto.getDescricao(), produto.getCategoria(), produto.getQuantidade()));
    }

}