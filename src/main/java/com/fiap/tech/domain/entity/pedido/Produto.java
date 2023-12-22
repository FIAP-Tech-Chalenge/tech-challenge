package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
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
    @Enumerated(EnumType.STRING)
    private CategoriaEnum categoria;


    public Produto(UUID uuid, Integer quantidade, CategoriaEnum categoria) {
        this.uuid = uuid;
        this.quantidade = quantidade;
        this.categoria = categoria;
    }
}
