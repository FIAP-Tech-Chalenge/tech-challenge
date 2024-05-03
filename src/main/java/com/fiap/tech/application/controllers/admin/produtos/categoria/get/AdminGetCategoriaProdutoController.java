package com.fiap.tech.application.controllers.admin.produtos.categoria.get;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.produto.BuscaTodosProdutoOutput;
import com.fiap.tech.domain.presenters.cliente.produto.GetProdutosPresenter;
import com.fiap.tech.domain.useCase.produto.BuscaProdutoPorCategoriaUseCase;
import com.fiap.tech.infra.adpter.repository.produto.BuscarProdutoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("admin/produto")
public class AdminGetCategoriaProdutoController {
    private final ProdutoRepository produtoRepository;

    @GetMapping("/categoria/{categoria}")
    @Operation(tags = {"admin"})
    public ResponseEntity<Object> getProdutoPorCategoria(String categoria) {
        BuscaProdutoPorCategoriaUseCase useCase = new BuscaProdutoPorCategoriaUseCase(new BuscarProdutoRepository(produtoRepository));
        useCase.execute(categoria);
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        GetProdutosPresenter presenter = new GetProdutosPresenter((BuscaTodosProdutoOutput) outputInterface);
        return new PresenterResponse().response(presenter);
    }
}
