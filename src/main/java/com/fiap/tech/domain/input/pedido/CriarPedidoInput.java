package com.fiap.tech.domain.input.pedido;



import java.util.List;
import java.util.UUID;

public record CriarPedidoInput(UUID clienteUuid, List<ProdutoPedidoInput> produtoList) {
}
