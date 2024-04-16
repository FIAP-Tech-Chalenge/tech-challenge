package com.fiap.tech.domain.presenters.cliente.produto;

import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.produto.EditaProdutoOutput;

import java.util.HashMap;
import java.util.Map;

public class UpdateProdutoPresenter implements PresenterInterface {
    private final EditaProdutoOutput updateProdutoOutput;

    public UpdateProdutoPresenter(EditaProdutoOutput updateProdutoOutput) {
        this.updateProdutoOutput = updateProdutoOutput;
    }

    public Map<String, Object> toArray() {
        Map<String, Object> array = new HashMap<>();
        array.put("uuid", this.updateProdutoOutput.getProduto().getUuid());
        array.put("nome", this.updateProdutoOutput.getProduto().getNome());
        array.put("valor", this.updateProdutoOutput.getProduto().getValor());
        array.put("descricao", this.updateProdutoOutput.getProduto().getDescricao());
        array.put("categoria", this.updateProdutoOutput.getProduto().getCategoria());
        array.put("quantidade", this.updateProdutoOutput.getProduto().getQuantidade());
        return array;
    }

    public EditaProdutoOutput getOutput() {
        return this.updateProdutoOutput;
    }
}