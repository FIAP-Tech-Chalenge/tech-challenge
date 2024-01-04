package com.fiap.tech.domain.useCase.checkout;

import java.util.UUID;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.checkout.PedidoNaoEncontradoException;
import com.fiap.tech.domain.port.checkout.CheckoutProcessorInterface;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProcessaCheckoutUseCase {
    private final BuscarPedidoRepository buscarPedidoRepository;
    private final CheckoutProcessorInterface checkoutProcessor;

    public void execute(UUID pedidoUuid) {
        Pedido pedido = buscarPedidoRepository.encontraPedidoPorUuid(pedidoUuid);
        if (pedido == null) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }

        checkoutProcessor.processarCheckout(pedido);

    }
}
