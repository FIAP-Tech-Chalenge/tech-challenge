package com.fiap.tech.infra.dependecy.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Base64;
import java.util.Map;

public class JWTDecodeAdapter implements JWTDecodeInterface {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public String claimClienteUuid(String token) throws Exception {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            // Invalid token format
            return "Invalid token format";
        }

        // Decode the payload (the second part)
        byte[] decodedPayload = Base64.getUrlDecoder().decode(parts[1]);

        try {
            // Parse the JSON payload
            Map payloadMap = objectMapper.readValue(decodedPayload, Map.class);

            // Extract the issuer field
            return (String) payloadMap.get("username");
        } catch (Exception e) {
            return null;
        }
    }
}
