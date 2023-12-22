package com.fiap.tech.domain.entity.cliente.validation;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.exception.cliente.EmailNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.cliente.NomeNaoPodeSerNuloException;
import com.fiap.tech.domain.valuesObject.CpfValueObject;


public class IdentificaClienteValidation {

    //chain of responsability
    public Cliente validaEntidade(Cliente cliente) throws Exception{
        if (cliente.getCpf().isEmpty()) {
            return cliente;
        }

        new CpfValueObject(cliente.getCpf()).isValid();

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
}
