package com.fiap.tech.application.services;

import com.fiap.tech.domain.useCase.checkout.ProcessaCheckoutUseCase;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class CheckoutService {

    private final ProcessaCheckoutUseCase processaCheckoutUseCase;

    public CheckoutService(ProcessaCheckoutUseCase processaCheckoutUseCase) {
        this.processaCheckoutUseCase = processaCheckoutUseCase;
    }

    public void processarCheckout(UUID pedidoUuid) {
        processaCheckoutUseCase.execute(pedidoUuid);
    }
}

