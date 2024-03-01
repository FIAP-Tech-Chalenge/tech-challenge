package com.fiap.tech.domain.useCase.checkout;

import java.util.UUID;

import com.fiap.tech.domain.entity.pagamento.GatewayQrCode;
import com.fiap.tech.domain.entity.pedido.Checkout;
import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.checkout.PedidoNaoEncontradoException;
import com.fiap.tech.domain.gateway.pagamento.PagamentoQrCodeInterface;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.output.pedido.CheckOutOutput;
import com.fiap.tech.domain.gateway.checkout.CheckoutProcessorInterface;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
@Getter
@RequiredArgsConstructor
public class ProcessaCheckoutUseCase {
    private final BuscarPedidoRepository buscarPedidoRepository;
    private final CheckoutProcessorInterface checkoutProcessor;
    private final PagamentoQrCodeInterface pagamentoQrCodeInterface;
    private OutputInterface checkoutOutput;

    public void execute(UUID pedidoUuid) {
        try {
            Pedido pedido = buscarPedidoRepository.encontraPedidoPorUuid(pedidoUuid);
            if (pedido == null) {
                throw new PedidoNaoEncontradoException("Pedido não encontrado");
            }

            Checkout checkout = checkoutProcessor.processarCheckout(pedido);
            GatewayQrCode gatewayQrCode = this.pagamentoQrCodeInterface.geraQrCodePagamento(pedidoUuid, pedido.getTotal());
            checkout.setQrCodeBase64(gatewayQrCode.getQrData());
            checkoutOutput = new CheckOutOutput(
                checkout,
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
