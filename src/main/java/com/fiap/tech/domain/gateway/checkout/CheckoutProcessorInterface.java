package com.fiap.tech.domain.gateway.checkout;
import com.fiap.tech.domain.entity.pedido.Checkout;
import com.fiap.tech.domain.entity.pedido.Pedido;

public interface CheckoutProcessorInterface {
    public Checkout processarCheckout(Pedido pedido);
}
