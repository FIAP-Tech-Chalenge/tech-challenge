package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.entity.produto.validations.CriaProdutoValidation;
import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;
import com.fiap.tech.domain.input.produto.EditaProdutoInput;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


@Data
@Getter
@Setter
public class Produto {
    private final UUID uuid;
    private final Integer quantidade;
    private Float valor;
    private CategoriaEnum categoria;

    public Produto(UUID uuid, Integer quantidade) {
        this.uuid = uuid;
        this.quantidade = quantidade;
    }
}
