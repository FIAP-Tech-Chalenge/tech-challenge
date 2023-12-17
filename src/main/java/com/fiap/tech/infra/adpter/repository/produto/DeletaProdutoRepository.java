package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.port.produto.DeletarProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class DeletaProdutoRepository implements DeletarProdutoInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public void deletaProduto(String uuid) throws ProdutoNaoEncontradoException {
        Optional<ProdutoModel> produto = this.produtoRepository.findByUuid(uuid);
        if (!produto.isPresent()) {
            throw new ProdutoNaoEncontradoException("Produto with UUID " + uuid + " not found.");
        }
        this.produtoRepository.delete(produto.get());
    }

    @Override
    public Optional<ProdutoModel> encontraProdutoPorUuid(String uuid) {
        return this.produtoRepository.findByUuid(uuid);
    }
}