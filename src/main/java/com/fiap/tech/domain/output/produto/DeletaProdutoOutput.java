package com.fiap.tech.domain.output.produto;
import com.fiap.tech.domain.entity.produto.Produto;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class DeletaProdutoOutput implements OutputInterface {

    private final OutputStatus outputStatus;
    private final Produto produto;

    public DeletaProdutoOutput(Produto produtoEntity, OutputStatus outputStatus) {
        this.produto = produtoEntity;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.produto;
    }
}

