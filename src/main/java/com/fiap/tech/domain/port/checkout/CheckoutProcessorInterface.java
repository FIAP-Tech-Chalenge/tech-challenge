package com.fiap.tech.domain.port.checkout;
import com.fiap.tech.domain.entity.pedido.Checkout;
import com.fiap.tech.domain.entity.pedido.Pedido;

public interface CheckoutProcessorInterface {
    public Checkout processarCheckout(Pedido pedido);
}
