package com.fiap.tech.infra.adpter.repository.produto;

import com.fiap.tech.domain.port.produto.BuscaProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class BuscarProdutosPorUuidRepository implements BuscaProdutoInterface {

    private final ProdutoRepository produtoRepository;

    @Override
    public ProdutoModel encontraProdutoPorUuid(UUID uuid) {
        return this.produtoRepository.findByUuid(uuid);
    }
}