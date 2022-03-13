package com.experian.cadastro.dto;

import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.enums.Score;

public class PessoaDTOIndividual {

    private String nome;
    private String telefone;
    private Integer idade;
    private String scoreDescricao;

    public PessoaDTOIndividual() {
    }

    public PessoaDTOIndividual(Pessoa pessoa, Score score) {
        this.nome = pessoa.getNome();
        this.telefone = pessoa.getTelefone();
        this.idade = pessoa.getIdade();
        this.scoreDescricao = score.getDescricao();
    }

    public PessoaDTOIndividual(String nome, String telefone, Integer idade, String scoreDescricao) {
        this.nome = nome;
        this.telefone = telefone;
        this.idade = idade;
        this.scoreDescricao = scoreDescricao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getScoreDescricao() {
        return scoreDescricao;
    }

    public void setScoreDescricao(String scoreDescricao) {
        this.scoreDescricao = scoreDescricao;
    }
}
