package com.fiap.tech.domain.port.produto;

import com.fiap.tech.domain.entity.cliente.Cliente;
import com.fiap.tech.domain.entity.produto.Produto;


public interface ProdutoInterface {

    Produto criaProduto(Produto produto);
}
