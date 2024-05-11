package com.fiap.tech.domain.outputerror;

import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class BuscarPedidoOutputError implements OutputInterface {

    private OutputStatus outputStatus;

    public BuscarPedidoOutputError(OutputStatus outputStatus) {
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return new Object() {
            public final String status = outputStatus.getCodeName();
        };
    }
}