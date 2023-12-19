package com.fiap.tech.domain.entity.produto;

import com.fiap.tech.domain.entity.produto.validations.CriaProdutoValidation;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;

import com.fiap.tech.domain.input.produto.EditaProdutoInput;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Data
@Getter
@Setter
public class Produto {
    private UUID uuid;
    private Float valor;
    private String nome;


    public Produto(String nome, Float valor) {
        this.nome = nome;
        this.valor = valor;
    }

    public Produto criaProduto() throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        return new CriaProdutoValidation().validaEntidade(this);
    }

    public void atualizaProduto(EditaProdutoInput editaProdutoInput) throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        this.setNome(editaProdutoInput.nome());
        this.setValor(editaProdutoInput.valor());
        new CriaProdutoValidation().validaEntidade(this);
    }

}
