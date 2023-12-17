package com.fiap.tech.domain.entity.produto;

import com.fiap.tech.domain.entity.produto.validations.CriaProdutoValidation;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;
import java.util.UUID;
@Data
public class Produto {
    private final Float valor;
    private final String nome;
    private String uuid;

    public Produto(String nome, Float valor, String uuid) {
        this.nome = nome;
        this.valor = valor;
        this.uuid = uuid;
    }

    public Produto criaProduto() throws NomeNaoPodeSerVazioException, ValorDoProdutoMenorQueZeroException {
        this.setUuid(getUuid());
        return new CriaProdutoValidation().validaEntidade(this);
    }
}
