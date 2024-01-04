package com.fiap.tech.domain.port.checkout;
import com.fiap.tech.domain.entity.pedido.Pedido;
import java.util.UUID;

public interface CheckoutProcessorInterface {
    void processarCheckout(Pedido pedido);
}
