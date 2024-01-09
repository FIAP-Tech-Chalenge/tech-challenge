package com.fiap.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


class SalvarTest {

    @Test
    void test_create_new_cliente_with_nome_cpf_email() {
        Cliente cliente = new Cliente("John Doe", "123456789", "john.doe@example.com", null);
        Assertions.assertEquals("John Doe", cliente.getNome());
        Assertions.assertEquals("123456789", cliente.getCpf());
        Assertions.assertEquals("john.doe@example.com", cliente.getEmail());
        Assertions.assertNull(cliente.getUuid());
    }
}
