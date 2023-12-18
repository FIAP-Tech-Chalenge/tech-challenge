package com.fiap.tech.domain.useCase.produto;

import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.produto.EditaProdutoOutput;
import com.fiap.tech.domain.port.produto.EditaProdutoInterface;
import com.fiap.tech.infra.model.ProdutoModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditaProdutoUseCase {

    private final EditaProdutoInterface editaProduto;
    private OutputInterface editaProdutoOutput;

    public void execute(ProdutoModel produtoModel) {
        try {
            ProdutoModel produtoExistente = this.editaProduto.encontraProdutoPorUuid(produtoModel.getUuid());

            if (produtoExistente == null) {
                throw new ProdutoNaoEncontradoException("Produto com UUID " + produtoModel.getUuid() + " não encontrado.");
            }

            ProdutoModel produtoEditado = this.editaProduto.editaProduto(produtoModel);
            this.editaProdutoOutput = new EditaProdutoOutput(
                    produtoEditado,
                    new OutputStatus(200, "OK", "Produto editado com sucesso")
            );
        } catch (ProdutoNaoEncontradoException e) {
            this.editaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(404, "Not Found", "Produto não encontrado")
            );
        } catch (Exception e) {
            this.editaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}
