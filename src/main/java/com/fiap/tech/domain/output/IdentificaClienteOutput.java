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
    IdentificaClienteOutput (Cliente cliente) {
        this.cliente = cliente;
    }
}
