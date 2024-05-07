package com.fiap.tech.domain.gateway.cliente;

import com.fiap.tech.domain.entity.pedido.Cliente;
import com.fiap.tech.domain.exception.pedido.ClienteNaoEncontradoException;

import java.util.List;
import java.util.UUID;


public interface ClienteInterface {

    Cliente buscaClientePorUuid(UUID uuid) throws ClienteNaoEncontradoException;

    List<com.fiap.tech.domain.entity.cliente.Cliente> buscarClientes();
}
