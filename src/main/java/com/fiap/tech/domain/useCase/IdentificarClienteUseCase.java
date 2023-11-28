package com.fiap.tech.domain.useCase;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.input.IdentificaClienteInput;
import com.fiap.tech.domain.port.IdentificarCliente;

public class IdentificarClienteUseCase {

    private final IdentificarCliente identificaCliente;

    public IdentificarClienteUseCase(IdentificarCliente identificaCliente) {
        this.identificaCliente = identificaCliente;
    }

    public void execute(IdentificaClienteInput identificaClienteInput){
        Cliente clienteEntity = this.identificaCliente.salvar(
            new Cliente(
                identificaClienteInput.getNome(),
                identificaClienteInput.getCpf(),
                identificaClienteInput.getEmail()
            )
        );
    }
}
