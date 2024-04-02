package com.fiap.tech.domain.useCase.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.exception.valueObject.CpfInvalidoValueObjectException;
import com.fiap.tech.domain.exception.cliente.EmailNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.cliente.NomeNaoPodeSerNuloException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.input.cliente.IdentificaClienteInput;
import com.fiap.tech.domain.output.cliente.IdentificaClienteOutput;
import com.fiap.tech.domain.gateway.cliente.IdentificarClienteInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class IdentificarClienteUseCase {

    private final IdentificarClienteInterface identificaCliente;
    private OutputInterface identificaClienteOutput;

    public void execute(IdentificaClienteInput identificaClienteInput) throws Exception {
        Cliente cliente = new Cliente(
            identificaClienteInput.getNome(),
            identificaClienteInput.getCpf(),
            identificaClienteInput.getEmail(),
            null
        );
        try {
            Cliente clienteBusca = this.identificaCliente.buscaClientePorCpf(cliente.getCpf());
            if (clienteBusca == null) {
                cliente = cliente.identificarCliente();
                Cliente clienteEntity = this.identificaCliente.identificarCliente(cliente);
                this.identificaClienteOutput = new IdentificaClienteOutput(
                    clienteEntity,
                    new OutputStatus(201, "Created", "Cliente criado")
                );
                return;
            }

            this.identificaClienteOutput = new IdentificaClienteOutput(
                clienteBusca,
                new OutputStatus(200, "Ok", "Cliente encontrado")
            );

        } catch (NomeNaoPodeSerNuloException | EmailNaoPodeSerNuloException | CpfInvalidoValueObjectException validationException) {
            this.identificaClienteOutput = new OutputError(
                validationException.getMessage(),
                new OutputStatus(422, "Unprocessable Entity", validationException.getMessage())
            );
        } catch (Exception e) {
            this.identificaClienteOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(500, "Internal Error", e.getMessage())
            );
        }
    }
}
