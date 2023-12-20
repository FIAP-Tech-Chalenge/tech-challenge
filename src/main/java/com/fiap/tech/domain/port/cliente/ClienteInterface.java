package com.fiap.tech.domain.port.cliente;

import com.fiap.tech.domain.entity.pedido.Cliente;
import com.fiap.tech.domain.exception.pedido.ClienteNaoEncontradoException;

import java.util.UUID;


public interface ClienteInterface {

    Cliente buscaClientePorUuid(UUID uuid) throws ClienteNaoEncontradoException;
}
