package com.fiap.tech.domain.input;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class IdentificaClienteInput {

    private String nome;
    private String cpf;
    private String email;

    public IdentificaClienteInput(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }
}
