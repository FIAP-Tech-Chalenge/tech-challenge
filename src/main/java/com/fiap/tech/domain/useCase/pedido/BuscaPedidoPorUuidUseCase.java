package com.fiap.tech.domain.useCase.pedido;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.output.pedido.BuscaPedidoOutput;
import com.fiap.tech.domain.gateway.pedido.BuscaPedidoInterface;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class BuscaPedidoPorUuidUseCase {

    private final BuscaPedidoInterface buscaPedido;
    private OutputInterface buscaPedidoOutput;

    public void execute(UUID uuid) {
        try {
            Pedido pedidoEntity = this.buscaPedido.encontraPedidoPorUuid(uuid);

            this.buscaPedidoOutput = new BuscaPedidoOutput(
                pedidoEntity,
                new OutputStatus(200, "OK", "Pedido encontrado")
            );

        } catch (PedidoNaoEncontradoException e) {
            this.buscaPedidoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(404, "Not Found", "Produto não encontrado")
            );
        }catch (Exception e) {
            this.buscaPedidoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}
