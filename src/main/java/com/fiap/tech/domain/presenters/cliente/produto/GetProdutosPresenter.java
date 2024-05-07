package com.fiap.tech.domain.presenters.cliente.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.produto.BuscaTodosProdutoOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetProdutosPresenter implements PresenterInterface {
    private final BuscaTodosProdutoOutput buscaTodosProdutoOutput;

    public GetProdutosPresenter(BuscaTodosProdutoOutput buscaTodosProdutoOutput) {
        this.buscaTodosProdutoOutput = buscaTodosProdutoOutput;
    }

    public Map<String, Object> toArray() {
        List<Produto> produtos = this.buscaTodosProdutoOutput.getListProdutos();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> produtosMapList = new ArrayList<>();

        for (Produto produto : produtos) {
            Map<String, Object> pedidoMap = new HashMap<>();
            pedidoMap.put("uuid", produto.getUuid().toString());
            pedidoMap.put("nome", produto.getNome());
            pedidoMap.put("descricao", produto.getDescricao());
            pedidoMap.put("categoria", produto.getCategoria());
            pedidoMap.put("quantidade", produto.getQuantidade());
            
            produtosMapList.add(pedidoMap);
        }

        map.put("pedidos", produtosMapList);

        return map;
    }

    public BuscaTodosProdutoOutput getOutput() {
        return this.buscaTodosProdutoOutput;
    }
}