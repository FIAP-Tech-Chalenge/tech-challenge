package com.fiap.tech.domain.useCase.produto;


import com.fiap.tech.domain.entity.produto.CategoriaProtudo;
import com.fiap.tech.domain.enums.produto.CategoriaEnum;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.CategoriaDeProdutoInvalidaException;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
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

    public void execute(String categoria) {
        try {
            CategoriaProtudo produtoEntity = new CategoriaProtudo(categoria);
            CategoriaEnum categoriaEnum= produtoEntity.validaCategoriaEnum();

            List<Produto> listProdutos = this.produtoRepository.encontraProdutoPorCategoria(categoriaEnum);

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
                new OutputStatus(404, "Not Found", e.getMessage())
            );

        } catch (CategoriaDeProdutoInvalidaException e) {
            this.buscaProdutoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(400, "Bad request", e.getMessage())
            );
        }
        catch (Exception e) {
            buscaProdutoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}