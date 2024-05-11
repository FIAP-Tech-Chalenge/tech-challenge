package com.fiap.tech.domain.gateway.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.pedido.PedidoNaoEncontradoException;

import java.util.List;
import java.util.UUID;


public interface BuscaPedidoInterface {

    Pedido encontraPedidoPorUuid(UUID pedidoUuid, UUID clienteUuid) throws PedidoNaoEncontradoException;

    List<Pedido> findAll();


}
