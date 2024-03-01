package com.fiap.tech.infra.adpter.integration.pagamento.qrCode;

import com.fiap.tech.domain.entity.pagamento.GatewayQrCode;
import com.fiap.tech.domain.gateway.dependency.HttpAdapterInterface;
import com.fiap.tech.domain.gateway.pagamento.PagamentoQrCodeInterface;
import com.google.gson.Gson;
import lombok.SneakyThrows;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MercadoPagoIntegration implements PagamentoQrCodeInterface {
    private final HttpAdapterInterface httpAdapterInterface;
    private String urlGateway = "";
    private final Map<String, String> headers;
    public MercadoPagoIntegration(HttpAdapterInterface httpAdapterInterface) {
        this.httpAdapterInterface = httpAdapterInterface;
        this.headers = new HashMap<>();
        this.headers.put("Content-Type", "application/json");
        this.headers.put("Authorization", "Bearer your_token_here");
        this.headers.put("User-Agent", "MyJavaApp/1.0");
    }

    @SneakyThrows
    @Override
    public GatewayQrCode geraQrCodePagamento(UUID uuid, Float valorTotal) {
        Map<String, String> data = new HashMap<>();
        data.put("cash_out", valorTotal.toString());
        data.put("external_reference", uuid.toString());

        Gson gson = new Gson();
        String json = gson.toJson(data);

        String response = this.httpAdapterInterface.post(this.urlGateway, json, headers);

        Map<String, String> dataResponse = gson.fromJson(response, Map.class);

        return new GatewayQrCode(dataResponse.get("qr_data"), dataResponse.get("in_store_order_id"));
    }
}
