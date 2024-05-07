package com.fiap.tech.infra.adpter.repository.cliente;

import com.fiap.tech.domain.entity.pedido.Cliente;
import com.fiap.tech.domain.exception.pedido.ClienteNaoEncontradoException;
import com.fiap.tech.domain.gateway.cliente.ClienteInterface;
import com.fiap.tech.infra.model.ClienteModel;
import com.fiap.tech.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class ClienteEntityRepository implements ClienteInterface {
    private final ClienteRepository clienteRepository;

    @Override
    public Cliente buscaClientePorUuid(UUID uuid) throws ClienteNaoEncontradoException {
        ClienteModel clienteModel = clienteRepository.findByUuid(uuid);
        if (clienteModel == null) {
            throw new ClienteNaoEncontradoException("Cliente não encontrado");
        }
        return new Cliente(
                clienteModel.getNome(),
                clienteModel.getCpf(),
                clienteModel.getEmail(),
                clienteModel.getUuid()
        );
    }

    @Override
    public List<com.fiap.tech.domain.entity.cliente.Cliente> buscarClientes() {
        List<ClienteModel> clientesModels = clienteRepository.findAll();
        List<com.fiap.tech.domain.entity.cliente.Cliente> clienteList = new ArrayList<>();
        for (ClienteModel clienteModel : clientesModels) {
            com.fiap.tech.domain.entity.cliente.Cliente pedidoEntity = new com.fiap.tech.domain.entity.cliente.Cliente(
                    clienteModel.getNome(),
                    clienteModel.getCpf(),
                    clienteModel.getEmail(),
                    clienteModel.getUuid()
            );
            clienteList.add(pedidoEntity);
        }
        return clienteList;
    }

    @Override
    public com.fiap.tech.domain.entity.cliente.Cliente getClienteByUuid(UUID uuid) {
        ClienteModel clienteModel = clienteRepository.findByUuid(uuid);
        if (clienteModel == null) {
            return null;
        }
        return new com.fiap.tech.domain.entity.cliente.Cliente(
                clienteModel.getNome(),
                clienteModel.getCpf(),
                clienteModel.getEmail(),
                clienteModel.getUuid()
        );
    }
}
