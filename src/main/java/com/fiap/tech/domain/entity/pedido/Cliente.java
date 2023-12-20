package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.entity.cliente.validation.IdentificaClienteValidation;
import com.fiap.tech.domain.exception.pedido.PedidoNaoPertenceAoClienteException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class Cliente {

    private UUID uuid;
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email, UUID uuid) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.uuid = uuid;
    }
}
