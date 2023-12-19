package com.fiap.tech.domain.useCase.produto;


import com.fiap.tech.domain.entity.produto.Categoria;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.produto.BuscaTodosProdutoOutput;
import com.fiap.tech.domain.port.produto.BuscaProdutoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
@Getter
public class BuscaProdutoPorCategoriaUseCase {

    private final BuscaProdutoInterface produtoRepository;
    private OutputInterface buscaProdutoOutput;

    public void execute(Categoria categoria) {
        try {
            List<Produto> listProdutos = this.produtoRepository.encontraProdutoPorCategoria(categoria);

            if (listProdutos == null || listProdutos.isEmpty()) {
                throw new ProdutoNaoEncontradoException("Nenhum Produto na categoria encontrado");
            }

            buscaProdutoOutput = new BuscaTodosProdutoOutput(
                    listProdutos,
                    new OutputStatus(200, "OK", "Lista de produtos")
            );
        } catch (ProdutoNaoEncontradoException e) {
            this.buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(404, "Not Found", "Nenhum Produto na categoria encontrado")
            );

        } catch (Exception e) {
            buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}