package com.fiap.tech.domain.useCase.produto;

import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;

import java.util.List;

public class BuscaTodosProdutosUseCase {

    private final ProdutoRepository produtoRepository;

    public BuscaTodosProdutosUseCase(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoModel> execute() {
        return produtoRepository.findAll();
    }
}
