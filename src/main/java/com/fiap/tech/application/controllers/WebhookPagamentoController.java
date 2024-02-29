package com.fiap.tech.application.controllers;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.input.pagamento.WebhookPagamentoStatus;
import com.fiap.tech.domain.useCase.pagamento.MudaStatusPagamentoUseCase;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import com.fiap.tech.infra.adpter.repository.pedido.CriaPedidoRepository;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webhook/status-pagamento")
public class WebhookPagamentoController {
    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoRepository produtoRepository;
    @PostMapping("/{pedido_uuid}/pagamento/atualizar-status")
    public ResponseEntity<Object> atualizarStatusPagamento(@PathVariable UUID pedido_uuid, WebhookPagamentoStatus webhookPagamentoStatus) {
        MudaStatusPagamentoUseCase statusPagamentoUseCase = new MudaStatusPagamentoUseCase(
            new BuscarPedidoRepository(
                this.pedidoRepository,
                this.pedidoProdutoRepository
            ),
            new CriaPedidoRepository(
                this.pedidoRepository,
                this.produtoRepository,
                this.pedidoProdutoRepository
            )
        );
        statusPagamentoUseCase.execute(pedido_uuid, webhookPagamentoStatus);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }
}
