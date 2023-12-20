package com.fiap.tech.application.response;

import com.fiap.tech.domain.genic.output.OutputInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class GenericResponse {
    public ResponseEntity<Object> response(OutputInterface outputInterface) {
        if (outputInterface.getOutputStatus().getCode() == 200) {
            return ResponseEntity.status(HttpStatus.OK).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == 201) {
            return ResponseEntity.status(HttpStatus.CREATED).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == 404) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == 204) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == 422) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(outputInterface.getBody());
        }

        if (outputInterface.getOutputStatus().getCode() == 400) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(outputInterface.getBody());
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(outputInterface.getBody());
    }
}
