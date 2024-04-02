package com.fiap.tech.domain.valuesObject;

import com.fiap.tech.domain.exception.valueObject.CpfInvalidoValueObjectException;

public class CpfValueObject {
    private final String cpf;

    public CpfValueObject(String cpf) throws CpfInvalidoValueObjectException {
        if (isValidCpf(cpf)) {
            this.cpf = cpf;
        } else {
            throw new CpfInvalidoValueObjectException("CPF inválido");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public boolean isValid() {
        return isValidCpf(cpf);
    }

    private boolean isValidCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");

        // Check if the CPF has 11 digits
        if (cpf.length() != 11) {
            return false;
        }

        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int[] digits = new int[11];
        for (int i = 0; i < 11; i++) {
            digits[i] = Integer.parseInt(cpf.substring(i, i + 1));
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            sum += digits[i] * (10 - i);
        }

        int remainder = sum % 11;
        int expectedDigit1 = (remainder < 2) ? 0 : (11 - remainder);

        if (digits[9] != expectedDigit1) {
            return false;
        }

        sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += digits[i] * (11 - i);
        }

        remainder = sum % 11;
        int expectedDigit2 = (remainder < 2) ? 0 : (11 - remainder);

        return digits[10] == expectedDigit2;
    }
}
