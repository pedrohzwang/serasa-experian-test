package com.experian.cadastro.controllers;

import com.experian.cadastro.dto.PessoaDTOIndividual;
import com.experian.cadastro.dto.PessoaDTOLista;
import com.experian.cadastro.dto.PessoaDTOSalvamento;
import com.experian.cadastro.entities.Pessoa;
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
import java.util.Optional;

@RestController
@RequestMapping(path = "/pessoa")
public class PessoaController {

    @Autowired
    PessoaService service;

    @GetMapping
    public ResponseEntity<List<PessoaDTOLista>> listarTodos() {
        List<PessoaDTOLista> pessoas = service.listarTodos();
        if (pessoas.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(pessoas);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PessoaDTOIndividual> getPessoaPorId(@PathVariable Long id) {
        PessoaDTOIndividual pessoa = service.getPessoaPorId(id);
        return ResponseEntity.ok(pessoa);
    }

    @PostMapping
    public ResponseEntity<PessoaDTOSalvamento> cadastrarPessoa(@RequestBody PessoaDTOSalvamento dto) {
        service.cadastrarPessoa(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
