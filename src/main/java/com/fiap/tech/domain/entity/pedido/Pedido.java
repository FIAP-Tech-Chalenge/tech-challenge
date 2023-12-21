package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.enums.pedido.StatusPedido;
import com.fiap.tech.domain.exception.pedido.PedidoVazioException;
import com.fiap.tech.domain.exception.pedido.ProdutoDoPedidoSemQuantidadeException;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;
@Getter
@Setter
public class Pedido {
        private UUID uuid;
        private final UUID clienteUuid;
        @Enumerated(EnumType.STRING)
        private StatusPedido status;
        private List<Produto> itens;
        private Float total;

        public Pedido(UUID clienteUuid) {
            this.clienteUuid = clienteUuid;
        }

    public Pedido(UUID clienteId, StatusPedido status, List<com.fiap.tech.domain.entity.produto.Produto> produtos, Float valorTotal) {
        this.clienteUuid = clienteId;
        this.status = status;
        this.total = valorTotal;
    }

    public void addProduto(Produto produto) {
        itens.add(produto);
    }

    public void verificaItensDoPedido() throws PedidoVazioException, ProdutoDoPedidoSemQuantidadeException {
        Iterator<Produto> iterator = itens.iterator();
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getQuantidade() < 1) {
                throw new ProdutoDoPedidoSemQuantidadeException("Produto com quantidade inválida");
            }
        }
        if (itens.isEmpty()) {
            throw new PedidoVazioException("Pedido vazio");
        }
    }

    public void gerarCombo()
    {

    }

    public float valorTotalDoPeido()
    {
        float total = (float) 0;
        for (Produto produto : itens) {
            total += produto.getValor() * produto.getQuantidade();
        }

        return total;
    }

}
