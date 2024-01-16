package com.fiap.tech.domain.port.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;

public interface PedidoInterface {
    Pedido criaPedido(Pedido pedido) throws ProdutoNaoEncontradoException;

}
