package com.fiap.tech.infra.adpter.repository.checkout;

import com.fiap.tech.domain.entity.pedido.Checkout;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.enums.pedido.StatusPedido;
import com.fiap.tech.domain.port.checkout.CheckoutProcessorInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckoutAdapter implements CheckoutProcessorInterface {

    private final PedidoRepository pedidoRepository;

    @Override
    public Checkout processarCheckout(Pedido pedido) {
        PedidoModel pedidoModel = pedidoRepository.findByUuid(pedido.getUuid());
        pedidoModel.setStatusPedido(StatusPedido.EMPREPARACAO);
        pedidoModel.setStatusPagamento(StatusPagamento.PAGO);
        pedidoRepository.save(pedidoModel);
        return new Checkout(pedido.getUuid(), StatusPagamento.PAGO);
    }
}
