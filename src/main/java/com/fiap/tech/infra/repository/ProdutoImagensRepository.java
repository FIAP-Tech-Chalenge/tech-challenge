package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.ProdutoImagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoImagensRepository extends JpaRepository<ProdutoImagemModel, Long> {
}
