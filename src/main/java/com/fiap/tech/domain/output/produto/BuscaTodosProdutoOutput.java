package com.fiap.tech.domain.output.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class BuscaTodosProdutoOutput implements OutputInterface {
    private List<Produto> listProdutos;
    private OutputStatus outputStatus;

    public BuscaTodosProdutoOutput(List<Produto> listProdutos, OutputStatus outputStatus) {
        this.listProdutos = listProdutos;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.listProdutos;
    }
}