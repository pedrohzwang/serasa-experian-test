package com.experian.cadastro.dto;

import com.experian.cadastro.entities.Pessoa;

public class PessoaDTOSalvamento {

    private String nome;
    private String telefone;
    private Integer idade;
    private String cidade;
    private String estado;
    private Integer score;

    public PessoaDTOSalvamento() {
    }

    public PessoaDTOSalvamento(Pessoa pessoa) {
        this.nome = pessoa.getNome();
        this.telefone = pessoa.getTelefone();
        this.idade = pessoa.getIdade();
        this.cidade = pessoa.getCidade();
        this.estado = pessoa.getEstado();
        this.score = pessoa.getScore();
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

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
