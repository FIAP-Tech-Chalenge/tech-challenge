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
@RequestMapping("/webhook/statusPagamento")
public class WebhookPagamentoController {
    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoRepository produtoRepository;
    @PostMapping("/{uuid}/pagamento/aprovado")
    public ResponseEntity<Object> pagamentoAprovado(@PathVariable UUID uuid) {
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
        statusPagamentoUseCase.execute(uuid, StatusPagamento.PAGO);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }

    @PostMapping("/{uuid}/pagamento/reprovado")
    public ResponseEntity<Object> pagamentoReprovado(@PathVariable UUID uuid) {
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
        statusPagamentoUseCase.execute(uuid, StatusPagamento.NAO_PAGO);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }

    @PostMapping("/{uuid}/pagamento/aguardando")
    public ResponseEntity<Object> pagamentoAguardandoPagamento(@PathVariable UUID uuid) {
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
        statusPagamentoUseCase.execute(uuid, StatusPagamento.AGUARDANDO_PAGAMENTO);
        OutputInterface outputInterface = statusPagamentoUseCase.getBuscaPedidoOutput();

        return new GenericResponse().response(outputInterface);
    }
}
