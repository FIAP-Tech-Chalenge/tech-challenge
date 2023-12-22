package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.infra.model.PedidoModel;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;


@Data
@Getter
@Setter
public class Produto {
    private final UUID uuid;
    private final Integer quantidade;
    private Float valor;
    private CategoriaEnum categoria;
    private List<PedidoModel> pedidos = null;

    public Produto(UUID uuid, Integer quantidade) {
        this.uuid = uuid;
        this.quantidade = quantidade;

    }
}
