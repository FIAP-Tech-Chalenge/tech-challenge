package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoModel, Long> {

}
