package com.fiap.tech.application.controllers;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.generic.output.OutputInterface;
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
    @PostMapping("/{pedido_uuid}/pagamento/aprovado")
    public ResponseEntity<Object> pagamentoAprovado(@PathVariable UUID pedido_uuid) {
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
        statusPagamentoUseCase.execute(pedido_uuid, StatusPagamento.PAGO);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }

    @PostMapping("/{pedido_uuid}/pagamento/reprovado")
    public ResponseEntity<Object> pagamentoReprovado(@PathVariable UUID pedido_uuid) {
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
        statusPagamentoUseCase.execute(pedido_uuid, StatusPagamento.NAO_PAGO);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }

    @PostMapping("/{pedido_uuid}/pagamento/aguardando")
    public ResponseEntity<Object> pagamentoAguardandoPagamento(@PathVariable UUID pedido_uuid) {
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
        statusPagamentoUseCase.execute(pedido_uuid, StatusPagamento.AGUARDANDO_PAGAMENTO);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }
}
