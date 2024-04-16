package com.fiap.tech.domain.generic.presenter;

import com.fiap.tech.domain.output.cliente.IdentificaClienteOutput;

import java.util.Map;

public interface PresenterInterface {
    Map<String, Object> toArray();

    IdentificaClienteOutput getOutput();
}
