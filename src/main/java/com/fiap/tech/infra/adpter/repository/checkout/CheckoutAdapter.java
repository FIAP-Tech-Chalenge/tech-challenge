package com.fiap.tech.infra.adpter.repository.checkout;

import com.fiap.tech.domain.entity.pedido.Checkout;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.port.checkout.CheckoutProcessorInterface;
import org.springframework.stereotype.Component;

@Component
public class CheckoutAdapter implements CheckoutProcessorInterface {


    @Override
    public Checkout processarCheckout(Pedido pedido) {

        return new Checkout(pedido.getUuid(), StatusPagamento.PAGO);
    }
}
