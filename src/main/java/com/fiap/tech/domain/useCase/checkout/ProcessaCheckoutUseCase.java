package com.fiap.tech.domain.useCase.checkout;

import java.util.UUID;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.checkout.PedidoNaoEncontradoException;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.pedido.CheckOutOutput;
import com.fiap.tech.domain.port.checkout.CheckoutProcessorInterface;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class ProcessaCheckoutUseCase {
    private final BuscarPedidoRepository buscarPedidoRepository;
    private final CheckoutProcessorInterface checkoutProcessor;
    private OutputInterface checkoutOutput;

    public void execute(UUID pedidoUuid) {
        try {
            Pedido pedido = buscarPedidoRepository.encontraPedidoPorUuid(pedidoUuid);
            if (pedido == null) {
                throw new PedidoNaoEncontradoException("Pedido não encontrado");
            }

            checkoutProcessor.processarCheckout(pedido);
            checkoutOutput = new CheckOutOutput(
                pedido,
                new OutputStatus(200, "Ok", "Checkout realizado com sucesso")
            );
        } catch (PedidoNaoEncontradoException pedidoNaoEncontradoException) {
            this.checkoutOutput = new OutputError(
                pedidoNaoEncontradoException.getMessage(),
                new OutputStatus(404, "Not Found", "Produto não encontrado")
            );
        }
    }
}
