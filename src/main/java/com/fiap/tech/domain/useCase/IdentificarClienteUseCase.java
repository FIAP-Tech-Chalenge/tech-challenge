package com.fiap.tech.domain.useCase;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.input.IdentificaClienteInput;
import com.fiap.tech.domain.port.IdentificarClienteInterface;

public class IdentificarClienteUseCase {

    private final IdentificarClienteInterface identificaCliente;

    public IdentificarClienteUseCase(IdentificarClienteInterface identificaCliente) {
        this.identificaCliente = identificaCliente;
    }

    public void execute(IdentificaClienteInput identificaClienteInput){
        Cliente clienteEntity = this.identificaCliente.identificarCliente(
            new Cliente(
                identificaClienteInput.getNome(),
                identificaClienteInput.getCpf(),
                identificaClienteInput.getEmail()
            )
        );
    }
}
