package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.PedidoProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PedidoProdutoRepository extends JpaRepository<PedidoProdutoModel, Long> {
    List<PedidoProdutoModel> findPedidoProdutoModelsByPedidoUuid(UUID pedidoUuid);
}
