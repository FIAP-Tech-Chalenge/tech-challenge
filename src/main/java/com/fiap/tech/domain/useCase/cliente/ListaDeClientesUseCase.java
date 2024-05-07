package com.fiap.tech.domain.useCase.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.gateway.cliente.ClienteInterface;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.output.cliente.ListaDeClienteOutput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ListaDeClientesUseCase {

    private final ClienteInterface clienteInterface;
    private OutputInterface outputStatus;

    public void execute() throws Exception {
        try {
            List<Cliente> listPedidos = clienteInterface.buscarClientes();

            this.outputStatus = new ListaDeClienteOutput(
                    listPedidos,
                    new OutputStatus(200, "OK", "Lista de pedidos")
            );
        } catch (Exception e) {
            this.outputStatus = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Error", e.getMessage())
            );
        }

    }
}
