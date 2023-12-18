package com.fiap.tech.domain.entity.produto;

import com.fiap.tech.domain.entity.produto.validations.CriaProdutoValidation;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Data
@Getter
@Setter
public class Produto {
    private UUID uuid;
    private final Float valor;
    private final String nome;


    public Produto(UUID uuid, String nome, Float valor) {
        this.uuid = uuid;
        this.nome = nome;
        this.valor = valor;
    }

    public Produto criaProduto() throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        this.setUuid(getUuid());
        return new CriaProdutoValidation().validaEntidade(this);
    }

    public Produto atualizaProduto() throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        return new CriaProdutoValidation().validaEntidade(this);
    }

}
