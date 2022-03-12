package com.experian.cadastro.services;

import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    @Autowired
    PessoaRepository repository;

    public List<Pessoa> listarTodos() {
        return repository.findAll();
    }
}
