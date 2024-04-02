package com.fiap.tech.infra.repository;

import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.infra.model.ProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModel, UUID> {
    ProdutoModel findByUuid(UUID uuid);
    void delete(ProdutoModel entity);
    List<ProdutoModel> findAll();
    ProdutoModel save(ProdutoModel produtoModel);

    List<ProdutoModel> findByCategoria(CategoriaEnum categoria);

}
