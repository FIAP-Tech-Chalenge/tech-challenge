package com.fiap.tech.domain.useCase.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.tech.domain.gateway.pedido.BuscaListaPedidoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@RequiredArgsConstructor
@Getter
public class BuscaListaPedidosUseCase {

    private final BuscaListaPedidoInterface buscaListaPedidoInterface;
    private OutputInterface buscaProdutoOutput;

    public void execute() {
        try {
            List<Pedido> listPedidos = buscaListaPedidoInterface.findListaPedidos();

            buscaProdutoOutput = new BuscaTodosPedidoOutput(
                    listPedidos,
                    new OutputStatus(200, "OK", "Lista de pedidos")
            );

        } catch (Exception e) {
            buscaProdutoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }

}
