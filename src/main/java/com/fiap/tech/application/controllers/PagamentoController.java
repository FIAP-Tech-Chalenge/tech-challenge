package com.fiap.tech.application.controllers;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.useCase.checkout.ProcessaCheckoutUseCase;
import com.fiap.tech.infra.adpter.integration.pagamento.qrCode.MercadoPagoIntegration;
import com.fiap.tech.infra.adpter.repository.checkout.CheckoutRepository;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pagamento")
public class PagamentoController {

    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;

    @PostMapping("/{uuid}/checkout")
    public ResponseEntity<Object> processarCheckout(@PathVariable UUID uuid) {
        ProcessaCheckoutUseCase processaCheckoutUseCase = new ProcessaCheckoutUseCase(
            new BuscarPedidoRepository(pedidoRepository, pedidoProdutoRepository),
            new CheckoutRepository(pedidoRepository),
            new MercadoPagoIntegration()
        );
        processaCheckoutUseCase.execute(uuid);
        OutputInterface outputInterface = processaCheckoutUseCase.getCheckoutOutput();
        return new GenericResponse().response(outputInterface);
    }
}
