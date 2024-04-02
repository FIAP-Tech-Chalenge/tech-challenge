package com.fiap.tech.domain.entity.pagamento;

import lombok.Getter;

@Getter
public class GatewayQrCode {
    private final String qrData;
    private final String inStoreOrderId;

    public GatewayQrCode(String qrData, String inStoreOrderId) {
        this.qrData = qrData;
        this.inStoreOrderId = inStoreOrderId;
    }
}
