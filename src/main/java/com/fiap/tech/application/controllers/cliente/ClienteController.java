package com.fiap.tech.application.controllers.cliente;


import com.fiap.tech.application.controllers.cliente.requests.IdentificaClienteRequest;
import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.input.cliente.IdentificaClienteInput;
import com.fiap.tech.domain.output.cliente.IdentificaClienteOutput;
import com.fiap.tech.domain.presenters.cliente.identifica.IdentificaClientePresenter;
import com.fiap.tech.domain.useCase.cliente.IdentificarClienteUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.IdentificarClienteRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(tags = {"cliente"})
    public ResponseEntity<Object> identificaCliente(@RequestBody IdentificaClienteRequest identificaClienteRequest) throws Exception {

        IdentificaClienteInput identificaClienteInput = new IdentificaClienteInput(
                identificaClienteRequest.nome(),
                identificaClienteRequest.cpf(),
                identificaClienteRequest.email()
        );
        IdentificarClienteUseCase useCase = new IdentificarClienteUseCase(new IdentificarClienteRepository(clienteRepository));
        useCase.execute(identificaClienteInput);

        OutputInterface outputInterface = useCase.getIdentificaClienteOutput();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }
        
        IdentificaClientePresenter presenter = new IdentificaClientePresenter((IdentificaClienteOutput) outputInterface);

        return new PresenterResponse().response(presenter);
    }

}
