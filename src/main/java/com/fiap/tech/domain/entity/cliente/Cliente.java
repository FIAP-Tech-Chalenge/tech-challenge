package com.fiap.tech.domain.entity.cliente;

import com.fiap.tech.domain.entity.cliente.strategy.IdentificaClienteStrategy;
import lombok.*;

@Data
@Getter
@Setter

public class Cliente {

    private String uuid;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public Cliente identificarCliente() throws Exception {
        return new IdentificaClienteStrategy().validaEntidade(this);
    }
}
