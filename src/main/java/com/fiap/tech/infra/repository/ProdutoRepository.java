package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, Long> {

}
