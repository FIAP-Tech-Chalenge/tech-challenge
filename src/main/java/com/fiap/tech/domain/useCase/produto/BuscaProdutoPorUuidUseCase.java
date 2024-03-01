package com.fiap.tech.domain.useCase.produto;


import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.output.produto.BuscaProdutoOutput;
import com.fiap.tech.domain.gateway.produto.BuscaProdutoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


import java.util.UUID;
@RequiredArgsConstructor
@Getter
public class BuscaProdutoPorUuidUseCase {

    private final BuscaProdutoInterface buscaProduto;
    private OutputInterface buscaProdutoOutput;

    public void execute(UUID uuid) {
        try {
            Produto produtoEntity = this.buscaProduto.encontraProdutoPorUuid(uuid);

            this.buscaProdutoOutput = new BuscaProdutoOutput(
                    produtoEntity,
                    new OutputStatus(200, "OK", "Produto encontrado com sucesso")
            );

        } catch (ProdutoNaoEncontradoException e) {
            this.buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(404, "Not Found", "Produto não encontrado")
            );
        } catch (Exception e) {
            this.buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}