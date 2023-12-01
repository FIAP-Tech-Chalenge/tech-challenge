package com.fiap.tech.domain.entity.cliente.strategy;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.exception.cliente.EmailNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.cliente.NomeNaoPodeSerNuloException;

import java.util.UUID;

public class IdentificaClienteStrategy {

    public Cliente validaEntidade(Cliente cliente) throws Exception{
        if (cliente.getCpf() != null) {
            if (cliente.getNome() == null) {
                //adicionar mensagem personalizada: Nome não pode ser nulo
                throw new NomeNaoPodeSerNuloException();
            }

            if (cliente.getEmail() == null) {
                //adicionar mensagem personalizada: Email não pode ser nulo
                throw new EmailNaoPodeSerNuloException();
            }

            return new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), null);
        }

        String uuid = UUID.randomUUID().toString();
        Cliente clienteEntity = new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), uuid);
        clienteEntity.setUuid(uuid);

        return clienteEntity;
    }

}
