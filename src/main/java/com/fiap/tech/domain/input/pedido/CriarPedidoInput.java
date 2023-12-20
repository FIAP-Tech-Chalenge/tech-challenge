package com.fiap.tech.domain.input.pedido;


import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.pedido.Produto;
import com.fiap.tech.domain.enums.produto.CategoriaEnum;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public record CriarPedidoInput(UUID clienteUuid, List<ProdutoPedidoInput> produtoList) {
}
