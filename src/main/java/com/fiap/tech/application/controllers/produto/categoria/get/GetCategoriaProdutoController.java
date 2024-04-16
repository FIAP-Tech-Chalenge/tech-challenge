package com.fiap.tech.application.controllers.produto.categoria.get;


import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
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
@RequestMapping("/produto")
public class GetCategoriaProdutoController {
    private final ProdutoRepository produtoRepository;

    @GetMapping("/categoria/{categoria}")
    @Operation(tags = {"produto-categoria"})
    public ResponseEntity<Object> getProdutoPorCategoria(String categoria) {
        BuscaProdutoPorCategoriaUseCase useCase = new BuscaProdutoPorCategoriaUseCase(new BuscarProdutoRepository(produtoRepository));
        useCase.execute(categoria);
        OutputInterface outputInterface = useCase.getBuscaProdutoOutput();
        return new GenericResponse().response(outputInterface);
    }
}
