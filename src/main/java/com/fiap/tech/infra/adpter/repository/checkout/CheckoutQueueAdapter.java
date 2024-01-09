package com.fiap.tech.infra.adpter.repository.checkout;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.port.checkout.CheckoutProcessorInterface;
import org.springframework.stereotype.Component;

@Component
public class CheckoutQueueAdapter implements CheckoutProcessorInterface {


    @Override
    public void processarCheckout(Pedido pedido) {

    }
}
