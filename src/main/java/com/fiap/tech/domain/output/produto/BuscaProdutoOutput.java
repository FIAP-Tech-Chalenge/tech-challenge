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
public class BuscaProdutoOutput implements OutputInterface {

    private Produto produto;
    private OutputStatus outputStatus;

    public BuscaProdutoOutput(Produto produto, OutputStatus outputStatus) {
        this.produto = produto;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.produto;
    }
}