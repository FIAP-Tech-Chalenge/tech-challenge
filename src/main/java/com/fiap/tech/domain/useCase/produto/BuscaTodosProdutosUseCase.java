package com.fiap.tech.domain.useCase.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.produto.BuscaTodosProdutoOutput;
import com.fiap.tech.domain.port.produto.BuscaProdutoInterface;
import lombok.Getter;

import java.util.List;

@Getter
public class BuscaTodosProdutosUseCase {

    private final BuscaProdutoInterface produtoRepository;
    private OutputInterface buscaProdutoOutput;

    public BuscaTodosProdutosUseCase(BuscaProdutoInterface produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void execute() {
        try {
            List<Produto> listProdutos = this.produtoRepository.findAll();

            buscaProdutoOutput = new BuscaTodosProdutoOutput(
                listProdutos,
                new OutputStatus(200, "OK", "Lista de produtos")
            );

        } catch (Exception e) {
            buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}
