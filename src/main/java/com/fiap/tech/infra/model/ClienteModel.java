package com.fiap.tech.infra.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="clientes")
public class ClienteModel {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    private String nome;
    private String cpf;
    private String email;

    public ClienteModel(String nome, String cpf, String email, UUID uuid) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.uuid = uuid;
    }

}
