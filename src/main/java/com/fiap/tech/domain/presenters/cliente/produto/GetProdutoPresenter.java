package com.fiap.tech.domain.presenters.cliente.produto;

import com.fiap.tech.domain.entity.produto.Imagem;
import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.produto.BuscaProdutoOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProdutoPresenter implements PresenterInterface {
    private final BuscaProdutoOutput buscaProdutoOutput;

    public GetProdutoPresenter(BuscaProdutoOutput buscaProdutoOutput) {
        this.buscaProdutoOutput = buscaProdutoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid", this.buscaProdutoOutput.getProduto().getUuid());
        array.put("nome", this.buscaProdutoOutput.getProduto().getNome());
        array.put("valor", this.buscaProdutoOutput.getProduto().getValor());
        array.put("descricao", this.buscaProdutoOutput.getProduto().getDescricao());
        array.put("categoria", this.buscaProdutoOutput.getProduto().getCategoria());
        array.put("quantidade", this.buscaProdutoOutput.getProduto().getQuantidade());
        if (this.buscaProdutoOutput.getProduto().getImagens() != null) {
            List<Map<String, Object>> produtoImagensMapList = new ArrayList<>();
            for (Imagem imagem : this.buscaProdutoOutput.getProduto().getImagens()) {
                Map<String, Object> produtoImagemMap = new HashMap<>();
                produtoImagemMap.put("nome", imagem.nome());
                produtoImagemMap.put("url", imagem.url());
                produtoImagensMapList.add(produtoImagemMap);
            }
            array.put("imagens", produtoImagensMapList);
        }
        return array;
    }

    public BuscaProdutoOutput getOutput() {
        return this.buscaProdutoOutput;
    }
}