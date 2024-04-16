package com.fiap.tech.application.controllers.produto.store;


import com.fiap.tech.application.controllers.produto.store.requests.StoreProdutoRequest;
import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.useCase.produto.CriaProdutoUseCase;
import com.fiap.tech.infra.adpter.repository.produto.CriaProtutoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")
public class StoreProdutoController {
    private final ProdutoRepository produtoRepository;

    @PostMapping
    @Operation(tags = {"produto"})
    public ResponseEntity<Object> criaProduto(@RequestBody StoreProdutoRequest criarProdutoRequest) {
        CriarProdutoInput criarProdutoInput = new CriarProdutoInput(
                criarProdutoRequest.nome(),
                criarProdutoRequest.valor(),
                criarProdutoRequest.descricao(),
                criarProdutoRequest.categoria(),
                criarProdutoRequest.quantidade(),
                criarProdutoRequest.dataCriacao()
        );
        CriaProdutoUseCase useCase = new CriaProdutoUseCase(new CriaProtutoRepository(produtoRepository));
        useCase.execute(criarProdutoInput);
        OutputInterface outputInterface = useCase.getCriaProdutoOutput();
        return new GenericResponse().response(outputInterface);
    }
}
