package com.fiap.tech.domain.generic.output;

import lombok.Getter;

@Getter
public class OutputStatus {
    private final int code;
    private final String codeName;
    private final String message;

    public OutputStatus(int code, String codeName, String message){
        this.code = code;
        this.codeName = codeName;
        this.message = message;
    }
}
