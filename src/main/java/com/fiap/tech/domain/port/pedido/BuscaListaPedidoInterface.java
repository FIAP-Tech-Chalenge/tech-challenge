package com.fiap.tech.domain.port.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;

import java.util.List;

public interface BuscaListaPedidoInterface {
    List<Pedido> findListaPedidos();
}
