package com.fiap.tech.domain.useCase.pagamento;

import com.fiap.tech.domain.entity.pedido.Pedido;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.exception.pedido.PedidoNaoEncontradoException;
import com.fiap.tech.domain.generic.output.OutputError;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.generic.output.OutputStatus;
import com.fiap.tech.domain.input.pagamento.WebhookPagamentoStatus;
import com.fiap.tech.domain.output.pagamento.StatusPagamentoOutput;
import com.fiap.tech.domain.port.pedido.BuscaPedidoInterface;
import com.fiap.tech.domain.port.pedido.PedidoInterface;
import com.fiap.tech.domain.resolver.StatusPagamentoResolver;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
@Getter
public class MudaStatusPagamentoUseCase {
    private final BuscaPedidoInterface buscaPedido;
    private final PedidoInterface pedidoInterface;
    private OutputInterface buscaPedidoOutput;

    public void execute(UUID uuid, WebhookPagamentoStatus statusPagamento) {
        try {
            Pedido pedidoEntity = this.buscaPedido.encontraPedidoPorUuid(uuid);
            StatusPagamento statusPagamentoEnum = new StatusPagamentoResolver().resolve(statusPagamento.status());
            pedidoEntity = this.pedidoInterface.atualizaPagamento(pedidoEntity, statusPagamentoEnum);

            this.buscaPedidoOutput = new StatusPagamentoOutput(
                pedidoEntity.getStatusPagamento(),
                new OutputStatus(200, "OK", "Status do pagamento atualizado")
            );
        } catch (PedidoNaoEncontradoException e) {
            this.buscaPedidoOutput = new OutputError(
                e.getMessage(),
                new OutputStatus(404, "Not Found", "Pedido não encontrado")
            );
        }catch (Exception e) {
            this.buscaPedidoOutput = new OutputError(
                    e.getMessage(),
                    new OutputStatus(500, "Internal Server Error", "Erro no servidor")
            );
        }
    }
}
