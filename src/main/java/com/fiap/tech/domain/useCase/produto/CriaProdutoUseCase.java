package com.fiap.tech.domain.useCase.produto;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.exception.cliente.EmailNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.cliente.NomeNaoPodeSerNuloException;
import com.fiap.tech.domain.exception.produto.NomeNaoPodeSerVazioException;
import com.fiap.tech.domain.exception.produto.ValorDoProdutoMenorQueZeroException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.input.cliente.IdentificaClienteInput;
import com.fiap.tech.domain.input.produto.CriarProdutoInput;
import com.fiap.tech.domain.output.cliente.IdentificaClienteOutput;
import com.fiap.tech.domain.output.produto.CriaProdutoOutput;
import com.fiap.tech.domain.port.cliente.IdentificarClienteInterface;
import com.fiap.tech.domain.port.produto.ProdutoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CriaProdutoUseCase {

    private final ProdutoInterface criaProduto;
    private OutputInterface criaProdutoOutput;

    public void execute(CriarProdutoInput criarProdutoInput) throws Exception {
        try {
            Produto produto = new Produto(criarProdutoInput.getNome(), criarProdutoInput.getValor()).criaProduto();
            this.criaProduto.criaProduto(produto);
            this.criaProdutoOutput = new CriaProdutoOutput(
                produto,
                new OutputStatus(201, "Created", "Produto criado")
            );

        } catch (NomeNaoPodeSerVazioException|ValorDoProdutoMenorQueZeroException nomeNaoPodeSerVazioException) {
            this.criaProdutoOutput = new OutputError(
                    nomeNaoPodeSerVazioException.getMessage(),
                new OutputStatus(422, "Unprocessable Entity", nomeNaoPodeSerVazioException.getMessage())
            );
        }
    }
}
