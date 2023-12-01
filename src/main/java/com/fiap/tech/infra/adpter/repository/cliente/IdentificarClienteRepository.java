package com.fiap.tech.infra.adpter.repository.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.port.IdentificarClienteInterface;
import com.fiap.tech.infra.model.ClienteModel;
import com.fiap.tech.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IdentificarClienteRepository implements IdentificarClienteInterface {

    private final ClienteRepository clienteRepository;


    @Override
    public Cliente identificarCliente(Cliente cliente) {
        this.clienteRepository.save(
            new ClienteModel(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getUuid())
        );

        return new Cliente(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getUuid());
    }
}
