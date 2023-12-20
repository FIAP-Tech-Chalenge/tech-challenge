package com.fiap.tech.domain.input.pedido;


import com.fiap.tech.domain.entity.pedido.Produto;

import java.util.List;
import java.util.UUID;

public record ProdutoPedidoInput(UUID uuid, Integer quantidade) {
}
