package com.fiap.tech.application.controllers;


import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.input.IdentificaClienteInput;
import com.fiap.tech.domain.useCase.IdentificarClienteUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.IdentificarClienteRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {


    private final ClienteRepository clienteRepository;

    @PostMapping
    public ResponseEntity<?> identificaCliente(@RequestBody IdentificaClienteInput identificaClienteInput) throws Exception {

        IdentificarClienteUseCase useCase = new IdentificarClienteUseCase(new IdentificarClienteRepository(clienteRepository));
        useCase.execute(identificaClienteInput);

        OutputInterface outputInterface = useCase.getIdentificaClienteOutput();

        if (outputInterface.getOutputStatus().getCode() == 201) {
            return ResponseEntity.ok((Cliente) useCase.getIdentificaClienteOutput().getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == 422) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(useCase.getIdentificaClienteOutput().getBody());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(useCase.getIdentificaClienteOutput().getBody());
    }

}
