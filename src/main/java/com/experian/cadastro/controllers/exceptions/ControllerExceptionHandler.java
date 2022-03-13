package com.experian.cadastro.controllers.exceptions;

import com.experian.cadastro.services.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity registroNaoEncontrado() {
        return ResponseEntity.noContent().build();
    }
}
