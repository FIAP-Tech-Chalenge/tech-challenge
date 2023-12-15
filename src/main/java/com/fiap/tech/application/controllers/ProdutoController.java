package com.fiap.tech.application.controllers;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.input.cliente.IdentificaClienteInput;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.useCase.cliente.IdentificarClienteUseCase;
import com.fiap.tech.domain.useCase.produto.CriaProdutoUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.IdentificarClienteRepository;
import com.fiap.tech.infra.adpter.repository.produto.CriaProtutoRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<?> criaProduto(@RequestBody CriarProdutoInput criarProdutoInput) throws Exception {

        CriaProdutoUseCase useCase = new CriaProdutoUseCase(new CriaProtutoRepository(produtoRepository));
        useCase.execute(criarProdutoInput);

        OutputInterface outputInterface = useCase.getCriaProdutoOutput();

        return new GenericResponse().response(outputInterface);
    }

}
