package com.fiap.tech.application.controllers.cliente.pagamento.webhock;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.application.response.PresenterResponse;
import com.fiap.tech.domain.enums.pedido.StatusPagamento;
import com.fiap.tech.domain.generic.output.OutputInterface;
import com.fiap.tech.domain.output.pagamento.StatusPagamentoOutput;
import com.fiap.tech.domain.presenters.cliente.pagamento.StatusPagamentoPresenter;
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
@RequestMapping("cliente/webhook/statusPagamento")
public class WebhookPagamentoReprovadoController {
    private final PedidoRepository pedidoRepository;
    private final PedidoProdutoRepository pedidoProdutoRepository;
    private final ProdutoRepository produtoRepository;

    @PostMapping("/{uuid}/pagamento/reprovado")
    @Operation(tags = {"cliente"})
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

        if (outputInterface.getOutputStatus().getCode() != 200) {
            return new GenericResponse().response(outputInterface);
        }

        StatusPagamentoPresenter presenter = new StatusPagamentoPresenter((StatusPagamentoOutput) outputInterface);

        return new PresenterResponse().response(presenter);
    }
}
