package com.fiap.tech.domain.output.produto;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
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

    @Override
    public Object getBody() {
        return "Produto deletado com sucesso.";
    }
}

