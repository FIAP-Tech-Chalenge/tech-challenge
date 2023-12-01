package com.fiap.tech.domain.genic.output;

import lombok.Getter;

@Getter
public class OutputStatus {
    private final int code;
    private final String codeName;

    public OutputStatus(int code, String codeName){
        this.code = code;
        this.codeName = codeName;
    }
}
