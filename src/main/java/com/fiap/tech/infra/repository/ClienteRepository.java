package com.fiap.tech.infra.repository;

import com.fiap.tech.infra.model.ClienteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends JpaRepository<ClienteModel, Long> {

    ClienteModel findByCpf(String cpf);

}
