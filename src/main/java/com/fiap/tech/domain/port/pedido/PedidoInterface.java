package com.fiap.tech.domain.port.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.pedido.PedidoNaoEncontradoException;

import java.util.UUID;

public interface PedidoInterface {
    public Pedido buscaPedido(UUID uuid) throws PedidoNaoEncontradoException;
    public Pedido criaPedido(Pedido pedido);
}
