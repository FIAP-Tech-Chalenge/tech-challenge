package com.fiap.tech.domain.useCase;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.input.IdentificaClienteInput;
import com.fiap.tech.domain.output.IdentificaClienteOutput;
import com.fiap.tech.domain.port.IdentificarClienteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IdentificarClienteUseCase {

    private final IdentificarClienteInterface identificaCliente;
    private IdentificaClienteOutput identificaClienteOutput;

    public void execute(IdentificaClienteInput identificaClienteInput) throws Exception {
        Cliente cliente = new Cliente(
                identificaClienteInput.getNome(),
                identificaClienteInput.getCpf(),
                identificaClienteInput.getEmail()
        );
        cliente = cliente.identificarCliente();
        Cliente clienteEntity = this.identificaCliente.identificarCliente(cliente);
        this.identificaClienteOutput = new IdentificaClienteOutput(clienteEntity);
    }
}
