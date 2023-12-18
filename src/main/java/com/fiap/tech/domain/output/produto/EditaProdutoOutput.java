package com.fiap.tech.domain.output.produto;

import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.infra.model.ProdutoModel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EditaProdutoOutput implements OutputInterface {

    private final ProdutoModel produtoModel;
    private final OutputStatus outputStatus;

    @Override
    public Object getBody() {
        return produtoModel;
    }
}
