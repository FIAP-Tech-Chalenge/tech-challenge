package com.fiap.tech.application.controllers;

import com.fiap.tech.application.response.GenericResponse;
import com.fiap.tech.domain.genic.output.OutputInterface;
import com.fiap.tech.domain.input.pedido.CriarPedidoInput;
import com.fiap.tech.domain.useCase.pedido.CriaPedidoUseCase;
import com.fiap.tech.infra.adpter.repository.cliente.ClienteEntityRepository;
import com.fiap.tech.infra.adpter.repository.pedido.CriaPedidoRepository;
import com.fiap.tech.infra.repository.ClienteRepository;
import com.fiap.tech.infra.repository.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    @PostMapping
    ResponseEntity<Object> criarProduto(@RequestBody CriarPedidoInput criarProdutoInput) throws Exception{

        CriaPedidoUseCase useCase = new CriaPedidoUseCase(
            new CriaPedidoRepository(pedidoRepository),
            new ClienteEntityRepository(clienteRepository)
        );
        useCase.execute(criarProdutoInput);
        OutputInterface outputInterface = useCase.getCriaPedidoOutput();
        return new GenericResponse().response(outputInterface);
    }

    /*@GetMapping
    public ResponseEntity<Object> getAllPedidos(){
        BuscaTodosPedidosUseCase useCase = new BuscaTodosPedidosUseCase(new BuscarPedidoRepository(pedidoRepository));
        useCase.execute();
        OutputInterface outputInterface = useCase.getBuscaPedidoOutput();
        return new GenericResponse().response(outputInterface);
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<Object> getPedido(@PathVariable UUID uuid){
        BuscaPedidoPorUuidUseCase useCase = new BuscaProdutoPorUuidUseCase(new BuscarPedidoRepository(pedidoRepository));
        useCase.execute(uuid);
        OutputInterface outputInterface = new useCase.getBuscaPedidoOutput();
        return new GenericResponse().response(outputInterface);
    }*/
}


