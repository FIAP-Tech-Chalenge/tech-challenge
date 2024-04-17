package com.fiap.tech.domain.presenters.cliente.produto;

import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.produto.DeletaProdutoOutput;

import java.util.HashMap;
import java.util.Map;

public class DeleteProdutoPresenter implements PresenterInterface {
    private final DeletaProdutoOutput deleteProdutoOutput;

    public DeleteProdutoPresenter(DeletaProdutoOutput deleteProdutoOutput) {
        this.deleteProdutoOutput = deleteProdutoOutput;
    }

    public Map<String, Object> toArray() {
        return new HashMap<>();
    }

    public DeletaProdutoOutput getOutput() {
        return this.deleteProdutoOutput;
    }
}