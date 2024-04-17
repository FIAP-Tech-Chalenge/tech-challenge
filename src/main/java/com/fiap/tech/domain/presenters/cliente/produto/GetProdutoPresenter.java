package com.fiap.tech.domain.presenters.cliente.produto;

import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.produto.BuscaProdutoOutput;

import java.util.HashMap;
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
        return array;
    }

    public BuscaProdutoOutput getOutput() {
        return this.buscaProdutoOutput;
    }
}