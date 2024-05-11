package com.fiap.tech.infra.dependecy.jwt;

public interface JWTDecodeInterface {
    String claimClienteUuid(String token) throws Exception;
}
