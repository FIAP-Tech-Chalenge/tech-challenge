package com.fiap.tech.application.controllers;


import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.input.IdentificaClienteInput;
import com.fiap.tech.domain.useCase.IdentificarClienteUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.IdentificarClienteRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Cliente> identificaCliente(@RequestBody IdentificaClienteInput identificaClienteInput) throws Exception {

        IdentificarClienteUseCase useCase = new IdentificarClienteUseCase(new IdentificarClienteRepository(clienteRepository));
        useCase.execute(identificaClienteInput);

        return ResponseEntity.ok(useCase.getIdentificaClienteOutput().getCliente());
    }


}
