package com.fiap.tech.domain.gateway.dependency;

import java.util.Map;

public interface HttpAdapterInterface {
    public String get(String url, Map<String, String> headers) throws Exception;
    public String post(String url, String requestBody, Map<String, String> headers) throws Exception;
}
