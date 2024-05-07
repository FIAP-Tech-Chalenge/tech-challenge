package com.fiap.tech.application.controllers.admin.clientes;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.cliente.IdentificaClienteOutput;
import com.fiap.tech.domain.presenters.cliente.identifica.IdentificaClientePresenter;
import com.fiap.tech.domain.useCase.cliente.GetClienteByUuidUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.ClienteEntityRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/cliente")
public class GetClienteByUuidController {


    private final ClienteRepository clienteRepository;

    @GetMapping("/{uuid}")
    @Operation(tags = {"admin"})
    public ResponseEntity<Object> getClienteByUuid(@PathVariable UUID uuid) throws Exception {
        GetClienteByUuidUseCase useCase = new GetClienteByUuidUseCase(new ClienteEntityRepository(this.clienteRepository));
        useCase.execute(uuid);

        OutputInterface outputInterface = useCase.getOutputInterface();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        IdentificaClientePresenter presenter = new IdentificaClientePresenter((IdentificaClienteOutput) outputInterface);

        return new PresenterResponse().response(presenter);
    }

}
