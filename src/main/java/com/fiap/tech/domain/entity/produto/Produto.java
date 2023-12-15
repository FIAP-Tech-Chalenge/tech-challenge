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
    private final Float valor;
    private final String nome;
    private String uiid;

    public Produto(String nome, Float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produto criaProduto() throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        this.setUiid(UUID.randomUUID().toString());
        return new CriaProdutoValidation().validaEntidade(this);
    }
}
