package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.gateway.produto.CriarProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class CriaProtutoRepository implements CriarProdutoInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public Produto criaProduto(Produto produto) {
        ProdutoModel produtoModel = this.produtoRepository.save(
            new ProdutoModel(
                produto.getUuid(),
                produto.getNome(),
                produto.getValor(),
                produto.getDescricao(),
                produto.getCategoria(),
                produto.getQuantidade()
            )
        );
        produto.setUuid(produtoModel.getUuid());
        return produto;
    }

}
