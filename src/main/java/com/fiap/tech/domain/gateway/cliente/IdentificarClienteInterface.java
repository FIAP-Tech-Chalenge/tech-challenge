package com.fiap.tech.domain.gateway.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;


public interface IdentificarClienteInterface {

    Cliente buscaClientePorCpf(String cpf);
    Cliente identificarCliente(Cliente cliente);
}
