package com.fiap.tech.domain.output;

import com.fiap.tech.domain.entity.cliente.Cliente;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IdentificaClienteOutput {
    private Cliente cliente;
    private OutputStatus outputStatus;

    public IdentificaClienteOutput(Cliente clienteEntity, OutputStatus outputStatus) {
        this.cliente = clienteEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.cliente;
    }
}
