package com.experian.cadastro.tests;

import com.experian.cadastro.dto.PessoaDTOListarTodos;
import com.experian.cadastro.dto.PessoaDTOSalvar;
import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.enums.Score;

import java.util.LinkedList;
import java.util.List;

public class PessoaFactory {

    public static Pessoa criarPessoa() {
        return new Pessoa(1L, "Pedro Henrique", "47 999999999", 20, "Blumenau", "SC", 1000);
    }

    public static List<Pessoa> criarListaPessoas() {
        List pessoaLista = new LinkedList();
        for (int i = 0; i < 5; i++) {
            pessoaLista.add(criarPessoa());
        }

        return pessoaLista;
    }

    public static PessoaDTOSalvar criarPessoaDTOSalvar() {
        return new PessoaDTOSalvar(criarPessoa());
    }

}
