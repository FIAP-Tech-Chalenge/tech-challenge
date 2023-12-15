package com.fiap.cliente;

import com.fiap.tech.domain.entity.cliente.Cliente;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;


class SalvarTest {

    @Test
    public void test_create_new_cliente_with_nome_cpf_email() {
        Cliente cliente = new Cliente("John Doe", "123456789", "john.doe@example.com", "1");
        /*assertEquals("John Doe", cliente.getNome());
        assertEquals("123456789", cliente.getCpf());
        assertEquals("john.doe@example.com", cliente.getEmail());
        assertEquals(null, cliente.getUuid());*/
    }
}
