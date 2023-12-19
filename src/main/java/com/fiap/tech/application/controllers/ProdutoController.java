package com.fiap.tech.application.controllers;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.input.produto.EditaProdutoInput;
import com.fiap.tech.domain.useCase.produto.*;
import com.fiap.tech.infra.adpter.repository.produto.BuscarProdutoRepository;
import com.fiap.tech.infra.adpter.repository.produto.CriaProtutoRepository;
import com.fiap.tech.infra.adpter.repository.produto.DeletaProdutoRepository;
import com.fiap.tech.infra.adpter.repository.produto.EditaProdutoRepository;
import com.fiap.tech.infra.model.ProdutoModel;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produtos")
public class ProdutoController {


    private final ProdutoRepository produtoRepository;

    @PostMapping
    public ResponseEntity<?> criaProduto(@RequestBody CriarProdutoInput criarProdutoInput){

        CriaProdutoUseCase useCase = new CriaProdutoUseCase(new CriaProtutoRepository(produtoRepository));
        useCase.execute(criarProdutoInput);
        OutputInterface outputInterface = useCase.getCriaProdutoOutput();
        return new GenericResponse().response(outputInterface);
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<?> deletaProduto(@PathVariable UUID uuid) {
            DeletaProdutoUseCase useCase = new DeletaProdutoUseCase(
                new DeletaProdutoRepository(produtoRepository),
                new BuscarProdutoRepository(produtoRepository)
            );
            useCase.execute(uuid);
            OutputInterface outputInterface = useCase.getDeletaProdutoOutput();
            return new GenericResponse().response(outputInterface);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<?> getProduto(@PathVariable UUID uuid) {
        BuscaProdutoPorUuidUseCase useCase = new BuscaProdutoPorUuidUseCase(new BuscarProdutoRepository(produtoRepository));
        useCase.execute(uuid);
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();
        return new GenericResponse().response(outputInterface);
    }

    @GetMapping
    public ResponseEntity<?> getAllProdutos() {
        BuscaTodosProdutosUseCase useCase = new BuscaTodosProdutosUseCase(new BuscarProdutoRepository(produtoRepository));
        useCase.execute();
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();
        return new GenericResponse().response(outputInterface);
    }

    @PutMapping("/{uuid}")
    public ResponseEntity<?> editaProduto(@PathVariable UUID uuid, @RequestBody EditaProdutoInput produtoInput) {
        EditaProdutoUseCase useCase = new EditaProdutoUseCase(
            new EditaProdutoRepository(produtoRepository),
            new BuscarProdutoRepository(produtoRepository)
        );
        useCase.execute(produtoInput, uuid);
        OutputInterface outputInterface = useCase.getEditaProdutoOutput();
        return new GenericResponse().response(outputInterface);
    }
}
