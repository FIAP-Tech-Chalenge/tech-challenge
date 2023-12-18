package com.fiap.tech.domain.input.produto;


import java.util.UUID;

public record CriarProdutoInput(UUID uuid, String nome, Float valor) {
}
