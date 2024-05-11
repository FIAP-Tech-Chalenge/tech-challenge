package com.fiap.tech.infra.dependecy.resolvers;

import com.fiap.tech.infra.dependecy.jwt.JWTDecodeAdapter;

import java.util.UUID;

public class RequestClienteResolver {
    public static String resolve(String authorizationHeader, UUID clienteUuid) throws Exception {
        if (authorizationHeader != null) {
            return new JWTDecodeAdapter().claimClienteUuid(authorizationHeader);
        }
        if (clienteUuid != null) {
            return clienteUuid.toString();
        }

        return null;
    }
}
