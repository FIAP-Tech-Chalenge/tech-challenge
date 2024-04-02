package com.fiap.tech.domain.output.pedido;

import com.fiap.tech.domain.entity.pedido.Checkout;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import lombok.Getter;

@Getter
public class CheckOutOutput implements OutputInterface {
    private final Checkout checkout;
    private final OutputStatus outputStatus;

    public CheckOutOutput(Checkout checkout, OutputStatus outputStatus) {
        this.checkout = checkout;
        this.outputStatus = outputStatus;
    }

    @Override
    public Object getBody() {
        return this.checkout;
    }
}
