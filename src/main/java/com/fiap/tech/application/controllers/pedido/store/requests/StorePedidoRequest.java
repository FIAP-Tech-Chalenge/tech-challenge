package com.fiap.tech.application.controllers.pedido.store.requests;

import com.fiap.tech.domain.input.pedido.ProdutoPedidoInput;

import java.util.List;
import java.util.UUID;

public record StorePedidoRequest(UUID clienteUuid, List<ProdutoPedidoInput> produtoList, Long numeroPedido) {
}
