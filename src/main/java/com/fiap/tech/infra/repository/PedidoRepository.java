package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.PedidoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface PedidoRepository extends JpaRepository<PedidoModel, UUID> {

    PedidoModel findByUuid(UUID uuid);

    //List<PedidoModel> findAll();

    @Query("SELECT p FROM PedidoModel p ORDER BY CASE p.statusPedido " +
            "WHEN 'RECEBIDO' THEN 0 " +
            "WHEN 'EM_PREPARACAO' THEN 1 " +
            "WHEN 'PRONTO' THEN 2 " +
            "ELSE 3 END")
    List<PedidoModel> findAll();


    @Query("SELECT COALESCE(MAX(p.numeroPedido), 0) FROM PedidoModel p")
    Long findMaxNumeroPedido();

}
