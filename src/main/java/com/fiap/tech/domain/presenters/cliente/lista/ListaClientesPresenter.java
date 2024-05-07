package com.fiap.tech.domain.presenters.cliente.lista;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.generic.presenter.PresenterInterface;
import com.fiap.tech.domain.output.cliente.ListaDeClienteOutput;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaClientesPresenter implements PresenterInterface {
    private final ListaDeClienteOutput listaDeClienteOutput;

    public ListaClientesPresenter(ListaDeClienteOutput listaDeClienteOutput) {
        this.listaDeClienteOutput = listaDeClienteOutput;
    }

    public Map<String, Object> toArray() {
        List<Cliente> clientes = this.listaDeClienteOutput.getClientes();
        Map<String, Object> map = new HashMap<>();
        List<Map<String, Object>> clientesMapList = new ArrayList<>();

        for (Cliente cliente : clientes) {
            Map<String, Object> clienteMap = new HashMap<>();
            clienteMap.put("uuid", cliente.getUuid().toString());
            clienteMap.put("nome", cliente.getNome());
            clienteMap.put("cpf", cliente.getCpf());
            clienteMap.put("email", cliente.getEmail());

            clientesMapList.add(clienteMap);
        }
        map.put("clientes", clientesMapList);

        return map;
    }

    public ListaDeClienteOutput getOutput() {
        return this.listaDeClienteOutput;
    }
}