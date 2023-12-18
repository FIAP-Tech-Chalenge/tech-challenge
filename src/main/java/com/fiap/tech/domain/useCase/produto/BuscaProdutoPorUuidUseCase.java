package com.fiap.tech.domain.useCase.produto;


import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.produto.BuscaProdutoOutput;
import com.fiap.tech.domain.port.produto.BuscaProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
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
            ProdutoModel produtoEncontrado = this.buscaProduto.encontraProdutoPorUuid(uuid);

            if (produtoEncontrado == null) {
                throw new ProdutoNaoEncontradoException("Produto com UUID " + uuid + " não encontrado.");
            }

            this.buscaProdutoOutput = new BuscaProdutoOutput(
                    produtoEncontrado,
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