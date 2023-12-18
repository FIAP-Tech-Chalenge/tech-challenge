package com.fiap.tech.domain.port.produto;

import com.fiap.tech.infra.model.ProdutoModel;

import java.util.UUID;

public interface BuscaProdutoInterface {
    ProdutoModel encontraProdutoPorUuid(UUID uuid);
}