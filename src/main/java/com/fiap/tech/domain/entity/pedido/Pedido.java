package com.fiap.tech.domain.entity.pedido;

import com.fiap.tech.domain.enums.pedido.StatusPedido;
import com.fiap.tech.domain.exception.pedido.PedidoVazioException;
import com.fiap.tech.domain.exception.pedido.ProdutoDoPedidoSemQuantidadeException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
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
        @ManyToMany
        @JoinTable(
                name = "pedido_produtos",
                joinColumns = @JoinColumn(name = "pedido_uuid"),
                inverseJoinColumns = @JoinColumn(name = "produto_uuid")
        )
        private List<Produto> produtos;
        private Float total;

        public Pedido(UUID clienteUuid) {
            this.clienteUuid = clienteUuid;
            this.produtos = new ArrayList<>();
        }

    public Pedido(UUID clienteId, StatusPedido status, List<com.fiap.tech.domain.entity.produto.Produto> produtos, Float valorTotal) {
        this.clienteUuid = clienteId;
        this.status = status;
        this.total = valorTotal;
    }

    public void addProduto(Produto produto) {
        produtos.add(produto);
    }

    public void verificaItensDoPedido() throws PedidoVazioException, ProdutoDoPedidoSemQuantidadeException {
        Iterator<Produto> iterator = produtos.iterator();
        while (iterator.hasNext()) {
            Produto produto = iterator.next();
            if (produto.getQuantidade() < 1) {
                throw new ProdutoDoPedidoSemQuantidadeException("Produto com quantidade inválida");
            }
        }
        if (produtos.isEmpty()) {
            throw new PedidoVazioException("Pedido vazio");
        }
    }

    public void gerarCombo()
    {

    }

    public float valorTotalDoPeido()
    {
        float total = (float) 0;
        for (Produto produto : produtos) {
            total += produto.getValor() * produto.getQuantidade();
        }

        return total;
    }

}
