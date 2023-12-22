package com.fiap.tech.domain.input.pedido;


import com.fiap.tech.domain.enums.produto.CategoriaEnum;

import java.util.UUID;

public record ProdutoPedidoInput(UUID uuid, Integer quantidade, CategoriaEnum categoria) {
}
