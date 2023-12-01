package com.fiap.tech.infra.repository;

import com.fiap.tech.domain.entity.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
