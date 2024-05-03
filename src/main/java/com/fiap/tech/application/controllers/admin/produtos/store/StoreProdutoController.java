package com.fiap.tech.application.controllers.admin.produtos.store;


import com.fiap.tech.application.controllers.admin.produtos.store.requests.StoreProdutoRequest;
import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.output.produto.CriaProdutoOutput;
import com.fiap.tech.domain.presenters.cliente.produto.StoreProdutoPresenter;
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
@RequestMapping("admin/produto")
public class StoreProdutoController {
    private final ProdutoRepository produtoRepository;

    @PostMapping
    @Operation(tags = {"admin"})
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
        if (outputInterface.getOutputStatus().getCode() != 201) {
            return new GenericResponse().response(outputInterface);
        }

        StoreProdutoPresenter presenter = new StoreProdutoPresenter((CriaProdutoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
