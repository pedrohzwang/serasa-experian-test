package com.experian.cadastro.controllers.exceptions;

import com.experian.cadastro.services.exceptions.RecursoNaoEncontradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity registroNaoEncontrado() {
        return ResponseEntity.noContent().build();
    }
}
