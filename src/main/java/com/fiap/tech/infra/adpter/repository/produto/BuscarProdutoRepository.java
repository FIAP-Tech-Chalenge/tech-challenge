package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.gateway.produto.BuscaProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class BuscarProdutoRepository implements BuscaProdutoInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto encontraProdutoPorUuid(UUID uuid) throws ProdutoNaoEncontradoException {
        ProdutoModel produtoModel = this.produtoRepository.findByUuid(uuid);
        if (produtoModel == null) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        Produto produtoEntity = new Produto(produtoModel.getNome(), produtoModel.getValor(), produtoModel.getDescricao(), produtoModel.getCategoria(), produtoModel.getQuantidade());
        produtoEntity.setUuid(produtoModel.getUuid());
        return produtoEntity;
    }

    @Override
    public List<Produto> findAll() {
        List<ProdutoModel> produtosModels = produtoRepository.findAll();
        List<Produto> produtosEntities = new ArrayList<>();

        for (ProdutoModel produtoModel : produtosModels) {
            Produto produtoEntity = new Produto(produtoModel.getNome(),
                    produtoModel.getValor(),
                    produtoModel.getDescricao(),
                    produtoModel.getCategoria(),
                    produtoModel.getQuantidade());
            produtoEntity.setUuid(produtoModel.getUuid());
            produtosEntities.add(produtoEntity);
        }

        return produtosEntities;
    }

    @Override
    public List<Produto> encontraProdutoPorCategoria(CategoriaEnum categoria) throws ProdutoNaoEncontradoException {
        List<ProdutoModel> produtosModel = this.produtoRepository.findByCategoria(categoria);
        if (produtosModel.isEmpty()) {
            throw new ProdutoNaoEncontradoException("Produto não encontrado");
        }
        List<Produto> produtosEntity = new ArrayList<>();
        for (ProdutoModel produtoModel : produtosModel) {
            Produto produtoEntity = new Produto(produtoModel.getNome(), produtoModel.getValor(), produtoModel.getDescricao(), produtoModel.getCategoria(), produtoModel.getQuantidade());
            produtoEntity.setUuid(produtoModel.getUuid());
            produtosEntity.add(produtoEntity);
        }
        return produtosEntity;
    }
}