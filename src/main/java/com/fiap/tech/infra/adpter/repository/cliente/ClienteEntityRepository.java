package com.fiap.tech.infra.adpter.repository.cliente;

import com.fiap.tech.domain.entity.pedido.Cliente;
import com.fiap.tech.domain.exception.pedido.ClienteNaoEncontradoException;
import com.fiap.tech.domain.port.cliente.ClienteInterface;
import com.fiap.tech.infra.model.ClienteModel;
import com.fiap.tech.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class ClienteEntityRepository implements ClienteInterface {
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente buscaClientePorUuid(UUID uuid) throws ClienteNaoEncontradoException {
        ClienteModel clienteModel = clienteRepository.findByUuid(uuid.toString());
        if (clienteModel == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return new Cliente(
            clienteModel.getNome(),
            clienteModel.getCpf(),
            clienteModel.getEmail(),
            UUID.fromString(clienteModel.getUuid())
        );
    }
}
