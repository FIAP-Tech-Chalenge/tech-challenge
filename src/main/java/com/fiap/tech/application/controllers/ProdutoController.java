package com.fiap.tech.application.controllers;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.useCase.produto.CriaProdutoUseCase;
import com.fiap.tech.domain.useCase.produto.DeletaProdutoUseCase;
import com.fiap.tech.infra.adpter.repository.produto.CriaProtutoRepository;
import com.fiap.tech.infra.adpter.repository.produto.DeletaProdutoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deletaProduto(@PathVariable String uuid) {
        try {
            DeletaProdutoUseCase useCase = new DeletaProdutoUseCase(new DeletaProdutoRepository(produtoRepository));
            useCase.execute(uuid);
            return new GenericResponse().response(useCase.getDeletaProdutoOutput());

        } catch (Exception e) {
            return new GenericResponse().response(new OutputError(
                    e.getMessage(),
                    new OutputStatus(HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND.name(), e.getMessage())
            ));
        }
    }
}
