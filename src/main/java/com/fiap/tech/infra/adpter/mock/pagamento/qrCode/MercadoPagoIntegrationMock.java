package com.fiap.tech.infra.adpter.mock.pagamento.qrCode;

import com.fiap.tech.domain.entity.pagamento.GatewayQrCode;
import com.fiap.tech.domain.gateway.dependency.HttpAdapterInterface;
import com.fiap.tech.domain.gateway.pagamento.PagamentoQrCodeInterface;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@RequiredArgsConstructor
public class MercadoPagoIntegrationMock implements PagamentoQrCodeInterface {
    private final HttpAdapterInterface httpAdapterInterface;

    @Override
    public GatewayQrCode geraQrCodePagamento(UUID uuid, Float valorTotal) {
        return new GatewayQrCode(
    "00020101021243650016COM.MERCADOLIBRE02013063638f1192a-5fd1-4180-a180-8bcae3556bc35204000053039865802BR5925IZABEL AAAA DE MELO6007BARUERI62070503***63040B6D",
            uuid.toString()
        );
    }
}
