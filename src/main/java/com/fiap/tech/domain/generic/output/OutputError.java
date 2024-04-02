package com.fiap.tech.domain.generic.output;

import lombok.Getter;

@Getter
public class OutputError implements OutputInterface{
    public final String mensagem;
    public final OutputStatus outputStatus;
    public OutputError (String mensagem, OutputStatus outputStatus){
        this.mensagem = mensagem;
        this.outputStatus = outputStatus;

    }
    @Override
    public Object getBody() {
        return outputStatus;
    }
}
