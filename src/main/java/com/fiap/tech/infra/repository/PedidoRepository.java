package com.fiap.tech.infra.repository;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.infra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

    PedidoModel findByUuid(UUID uuid);
}
