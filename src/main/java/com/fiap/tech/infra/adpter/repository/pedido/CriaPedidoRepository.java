package com.fiap.tech.infra.adpter.repository.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.tech.domain.port.pedido.PedidoInterface;
import com.fiap.tech.infra.model.PedidoModel;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.UUID;

@RequiredArgsConstructor
public class CriaPedidoRepository implements PedidoInterface {

    private final PedidoRepository pedidoRepository;

    @Override
    public Pedido buscaPedido(UUID uuid) throws PedidoNaoEncontradoException {
        PedidoModel pedidoModel = pedidoRepository.findByUuid(uuid);
        if (pedidoModel == null) {
            throw new PedidoNaoEncontradoException("Pedido não encontrado");
        }
        Pedido pedidoEntity = new Pedido(pedidoModel.getClienteId());
        pedidoEntity.setUuid(pedidoModel.getUuid());
        pedidoEntity.setStatus(pedidoModel.getStatus());
        pedidoEntity.setTotal(pedidoModel.getValorTotal());

        return pedidoEntity;
    }

    @Override
    public Pedido criaPedido(Pedido pedido) {
        PedidoModel pedidoModel = new PedidoModel();
        pedidoModel.setUuid(pedido.getUuid());
        pedido.valorTotalDoPeido();
        pedidoModel.setClienteId(pedido.getClienteUuid());
        pedidoModel.setStatus(pedido.getStatus());
        pedidoModel.setDataCriacao(new Date());

        pedidoModel = pedidoRepository.save(pedidoModel);

        pedido.setUuid(pedidoModel.getUuid());
        pedido.setTotal(pedidoModel.getValorTotal());
        return pedido;
    }
}
