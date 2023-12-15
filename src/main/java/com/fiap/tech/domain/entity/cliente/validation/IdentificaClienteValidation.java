package com.fiap.tech.domain.entity.cliente.validation;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.exception.cliente.EmailNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.cliente.NomeNaoPodeSerNuloException;

import java.util.UUID;

public class IdentificaClienteValidation {

    public Cliente validaEntidade(Cliente cliente) throws Exception{
        if (!cliente.getCpf().isEmpty()) {
            if (cliente.getNome().isEmpty()) {
                //adicionar mensagem personalizada: Nome não pode ser nulo
                throw new NomeNaoPodeSerNuloException("Nome é obrigatório");
            }

            if (cliente.getEmail().isEmpty()) {
                //adicionar mensagem personalizada: Email não pode ser nulo
                throw new EmailNaoPodeSerNuloException("Email é obrigatório");
            }

            return new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), null);
        }

        String uuid = UUID.randomUUID().toString();
        Cliente clienteEntity = new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), uuid);
        clienteEntity.setUuid(uuid);

        return clienteEntity;
    }

}
