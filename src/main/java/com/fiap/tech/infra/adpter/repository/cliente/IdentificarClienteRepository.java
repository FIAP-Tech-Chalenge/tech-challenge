package com.fiap.tech.infra.adpter.repository.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.port.cliente.IdentificarClienteInterface;
import com.fiap.tech.infra.model.ClienteModel;
import com.fiap.tech.infra.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class IdentificarClienteRepository implements IdentificarClienteInterface {

    private final ClienteRepository clienteRepository;

    public Cliente buscaClientePorCpf(String cpf) {
        ClienteModel clienteModel = this.clienteRepository.findByCpf(cpf);
        if (clienteModel == null) {
            return null;
        }
        return new Cliente(clienteModel.getNome(), clienteModel.getCpf(), clienteModel.getEmail(), clienteModel.getUuid());
    }

    @Override
    public Cliente identificarCliente(Cliente cliente) {
        ClienteModel clienteModel =this.clienteRepository.save(
            new ClienteModel(cliente.getNome(), cliente.getCpf(), cliente.getEmail(), cliente.getUuid())
        );
        cliente.setUuid(clienteModel.getUuid());
        return cliente;
    }
}
