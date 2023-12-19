package com.fiap.tech.domain.output.produto;

import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.infra.model.ProdutoModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class EditaProdutoOutput implements OutputInterface {

    private final Produto produto;
    private final OutputStatus outputStatus;

    public EditaProdutoOutput(Produto produtoEntity, OutputStatus outputStatus) {
        this.produto = produtoEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return produto;
    }
}
