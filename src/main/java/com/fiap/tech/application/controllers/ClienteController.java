package com.fiap.tech.application.controllers;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.input.cliente.IdentificaClienteInput;
import com.fiap.tech.domain.useCase.cliente.IdentificarClienteUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.IdentificarClienteRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {


    private final ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<Object> identificaCliente(@RequestBody IdentificaClienteInput identificaClienteInput) throws Exception {

        IdentificarClienteUseCase useCase = new IdentificarClienteUseCase(new IdentificarClienteRepository(clienteRepository));
        useCase.execute(identificaClienteInput);

        OutputInterface outputInterface = useCase.getIdentificaClienteOutput();

        return new GenericResponse().response(outputInterface);
    }

}
