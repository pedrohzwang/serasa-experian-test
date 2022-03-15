package com.experian.cadastro.controllers;

import com.experian.cadastro.dto.PessoaDTO;
import com.experian.cadastro.dto.PessoaDTOListarTodos;
import com.experian.cadastro.dto.PessoaDTOSalvar;
import com.experian.cadastro.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping
    public ResponseEntity<List<PessoaDTOListarTodos>> listarTodos() {
        List<PessoaDTOListarTodos> pessoas = service.listarTodos();
        if (pessoas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PessoaDTO> buscarPessoaPorId(@PathVariable Long id) {
        PessoaDTO pessoa = service.buscarPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<PessoaDTOSalvar> cadastrarPessoa(@RequestBody PessoaDTOSalvar dto) {
        service.salvarPessoa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
