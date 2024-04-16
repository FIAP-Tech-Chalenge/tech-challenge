package com.fiap.tech.domain.generic.presenter;

import com.fiap.tech.domain.generic.output.OutputInterface;

import java.util.Map;

public interface PresenterInterface {
    Map<String, Object> toArray();

    OutputInterface getOutput();
}
