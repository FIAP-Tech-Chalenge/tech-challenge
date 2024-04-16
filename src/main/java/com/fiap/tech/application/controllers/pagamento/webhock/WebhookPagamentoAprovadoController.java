package com.fiap.tech.application.controllers.pagamento.webhock;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.useCase.pagamento.MudaStatusPagamentoUseCase;
import com.fiap.tech.infra.adpter.repository.pedido.BuscarPedidoRepository;
import com.fiap.tech.infra.adpter.repository.pedido.CriaPedidoRepository;
import com.fiap.tech.infra.repository.PedidoProdutoRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import com.fiap.tech.infra.repository.ProdutoRepository;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/webhook/statusPagamento")
public class WebhookPagamentoAprovadoController {
    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoRepository produtoRepository;

    @PostMapping("/{uuid}/pagamento/aprovado")
    @Operation(tags = {"webhook-pagamento"})
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
}
