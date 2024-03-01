package com.fiap.tech.infra.adpter.integration.pagamento.qrCode;

import com.fiap.tech.domain.entity.pagamento.GatewayQrCode;
import com.fiap.tech.domain.gateway.pagamento.PagamentoQrCodeInterface;

import java.util.UUID;

public class MercadoPagoIntegration implements PagamentoQrCodeInterface {
    @Override
    public GatewayQrCode geraQrCodePagamento(UUID uuid, Float valorTotal) {
        return new GatewayQrCode("asdas", "asdasd");
    }
}
