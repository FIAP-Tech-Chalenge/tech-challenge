package com.fiap.tech.domain.useCase.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.ProdutoNaoEncontradoException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.input.produto.EditaProdutoInput;
import com.fiap.tech.domain.output.produto.EditaProdutoOutput;
import com.fiap.tech.domain.port.produto.BuscaProdutoInterface;
import com.fiap.tech.domain.port.produto.EditaProdutoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@Getter
@RequiredArgsConstructor
public class EditaProdutoUseCase {

    private final EditaProdutoInterface editaProduto;
    private final BuscaProdutoInterface produtoInterface;
    private OutputInterface editaProdutoOutput;

    public void execute(EditaProdutoInput editaProdutoInput, UUID uuid) {
        try {
            Produto produtoEntity = this.produtoInterface.encontraProdutoPorUuid(uuid);
            produtoEntity.atualizaProduto(editaProdutoInput);

            this.editaProduto.editaProduto(produtoEntity, uuid);
            this.editaProdutoOutput = new EditaProdutoOutput(
                    produtoEntity,
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
