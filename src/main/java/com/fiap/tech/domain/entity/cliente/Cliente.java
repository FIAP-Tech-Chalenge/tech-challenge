package com.fiap.tech.domain.entity.cliente;

import com.fiap.tech.domain.entity.cliente.strategy.IdentificaClienteStrategy;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor


public class Cliente {

    private String uuid;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email, String uuid) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.uuid = uuid;
    }


    public Cliente identificarCliente() throws Exception {
        return new IdentificaClienteStrategy().validaEntidade(this);
    }
}
