package com.fiap.tech.domain.output.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class CriaProdutoOutput implements OutputInterface {
    private Produto produto;
    private OutputStatus outputStatus;

    public CriaProdutoOutput(Produto produtoEntity, OutputStatus outputStatus) {
        this.produto = produtoEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.produto;
    }
}
