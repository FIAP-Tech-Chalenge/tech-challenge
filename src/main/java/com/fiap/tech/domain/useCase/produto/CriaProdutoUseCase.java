package com.fiap.tech.domain.useCase.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;
import com.fiap.tech.domain.gateway.produto.CriarProdutoInterface;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.output.produto.CriaProdutoOutput;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CriaProdutoUseCase {

    private final CriarProdutoInterface criaProdutoRepository;
    private OutputInterface criaProdutoOutput;

    public void execute(CriarProdutoInput criarProdutoInput) {
        try {
            Produto produto;
            produto = new Produto(criarProdutoInput.nome(), criarProdutoInput.valor(), criarProdutoInput.descricao(), criarProdutoInput.categoria(), criarProdutoInput.quantidade()).criaProduto();
            produto.setImagens(criarProdutoInput.imagens());
            this.criaProdutoRepository.criaProduto(produto);
            this.criaProdutoOutput = new CriaProdutoOutput(
                    produto,
                    new OutputStatus(201, "Created", "Produto criado")
            );

        } catch (NomeNaoPodeSerVazioException | ValorDoProdutoMenorQueZeroException nomeNaoPodeSerVazioException) {
            this.criaProdutoOutput = new OutputError(
                    nomeNaoPodeSerVazioException.getMessage(),
                    new OutputStatus(422, "Unprocessable Entity", nomeNaoPodeSerVazioException.getMessage())
            );
        }
    }
}
