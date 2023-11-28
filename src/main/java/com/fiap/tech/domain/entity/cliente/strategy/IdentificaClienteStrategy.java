package com.fiap.tech.domain.entity.cliente.strategy;

import com.fiap.tech.domain.entity.cliente.Cliente;

import java.util.UUID;

public class IdentificaClienteStrategy {

    public Cliente validaEntidade(Cliente cliente) throws Exception{
        if (cliente.getCpf() != null) {
            if (cliente.getNome() == null) {
                throw new Exception("Nome não pode ser nulo");
            }

            if (cliente.getEmail() == null) {
                throw new Exception("Email não pode ser nulo");
            }

            return new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail());
        }

        String uuid = UUID.randomUUID().toString();
        Cliente clienteEntity = new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail());
        clienteEntity.setUuid(uuid);

        return clienteEntity;
    }

}
