package com.fiap.tech.application.controllers.admin.produtos.delete;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.produto.DeletaProdutoOutput;
import com.fiap.tech.domain.presenters.cliente.produto.DeleteProdutoPresenter;
import com.fiap.tech.domain.useCase.produto.DeletaProdutoUseCase;
import com.fiap.tech.infra.adpter.repository.produto.BuscarProdutoRepository;
import com.fiap.tech.infra.adpter.repository.produto.DeletaProdutoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/produto")
public class DeleteProdutoController {
    private final ProdutoRepository produtoRepository;

    @DeleteMapping("/{uuid}")
    @Operation(tags = {"admin"})
    public ResponseEntity<Object> deletaProduto(@PathVariable UUID uuid) {
        DeletaProdutoUseCase useCase = new DeletaProdutoUseCase(
                new DeletaProdutoRepository(produtoRepository),
                new BuscarProdutoRepository(produtoRepository)
        );
        useCase.execute(uuid);
        OutputInterface outputInterface = useCase.getDeletaProdutoOutput();
        if (outputInterface.getOutputStatus().getCode() != 204) {
            return new GenericResponse().response(outputInterface);
        }

        DeleteProdutoPresenter presenter = new DeleteProdutoPresenter((DeletaProdutoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
