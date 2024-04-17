package com.fiap.tech.application.controllers.produto.update;


import com.fiap.tech.application.controllers.produto.update.requests.UpdateProdutoRequest;
import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.input.produto.EditaProdutoInput;
import com.fiap.tech.domain.output.produto.EditaProdutoOutput;
import com.fiap.tech.domain.presenters.cliente.produto.UpdateProdutoPresenter;
import com.fiap.tech.domain.useCase.produto.EditaProdutoUseCase;
import com.fiap.tech.infra.adpter.repository.produto.BuscarProdutoRepository;
import com.fiap.tech.infra.adpter.repository.produto.EditaProdutoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/produto")
public class UpdateProdutoController {
    private final ProdutoRepository produtoRepository;

    @PutMapping("/{uuid}")
    @Operation(tags = {"produto"})
    public ResponseEntity<Object> editaProduto(@PathVariable UUID uuid, @RequestBody UpdateProdutoRequest produtoRequest) {
        EditaProdutoInput produtoInput = new EditaProdutoInput(
                produtoRequest.nome(),
                produtoRequest.valor(),
                produtoRequest.descricao(),
                produtoRequest.categoria(),
                produtoRequest.quantidade(),
                produtoRequest.dataCriacao()
        );
        EditaProdutoUseCase useCase = new EditaProdutoUseCase(
                new EditaProdutoRepository(produtoRepository),
                new BuscarProdutoRepository(produtoRepository)
        );
        useCase.execute(produtoInput, uuid);
        OutputInterface outputInterface = useCase.getEditaProdutoOutput();
        if (outputInterface.getOutputStatus().getCode() != 201) {
            return new GenericResponse().response(outputInterface);
        }

        UpdateProdutoPresenter presenter = new UpdateProdutoPresenter((EditaProdutoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
