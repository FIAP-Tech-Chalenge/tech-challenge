package com.fiap.tech.domain.useCase.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.genic.output.OutputError;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.genic.output.OutputStatus;
import com.fiap.tech.domain.output.pedido.BuscaTodosPedidoOutput;
import com.fiap.tech.domain.port.pedido.BuscaPedidoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;
@Getter
@RequiredArgsConstructor
public class BuscaTodosPedidosUseCase {

    private final BuscaPedidoInterface buscaPedidoInterface;
    private OutputInterface buscaProdutoOutput;

    public void execute() {
        try {
            List<Pedido> listPedidos = buscaPedidoInterface.findAll();

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

