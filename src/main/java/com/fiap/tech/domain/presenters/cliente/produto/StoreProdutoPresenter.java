package com.fiap.tech.domain.presenters.cliente.produto;

import com.fiap.tech.domain.entity.produto.Imagem;
import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.produto.CriaProdutoOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoreProdutoPresenter implements PresenterInterface {
    private final CriaProdutoOutput criaProdutoOutput;

    public StoreProdutoPresenter(CriaProdutoOutput criaProdutoOutput) {
        this.criaProdutoOutput = criaProdutoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid", this.criaProdutoOutput.getProduto().getUuid());
        array.put("nome", this.criaProdutoOutput.getProduto().getNome());
        array.put("valor", this.criaProdutoOutput.getProduto().getValor());
        array.put("descricao", this.criaProdutoOutput.getProduto().getDescricao());
        array.put("categoria", this.criaProdutoOutput.getProduto().getCategoria());
        array.put("quantidade", this.criaProdutoOutput.getProduto().getQuantidade());
        List<Map<String, Object>> produtoImagensMapList = new ArrayList<>();
        for (Imagem imagem : this.criaProdutoOutput.getProduto().getImagens()) {
            Map<String, Object> produtoImagemMap = new HashMap<>();
            produtoImagemMap.put("nome", imagem.nome());
            produtoImagemMap.put("url", imagem.url());
            produtoImagensMapList.add(produtoImagemMap);
        }
        array.put("imagens", produtoImagensMapList);

        return array;
    }

    public CriaProdutoOutput getOutput() {
        return this.criaProdutoOutput;
    }
}