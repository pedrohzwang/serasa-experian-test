package com.experian.cadastro.dto;

import com.experian.cadastro.entities.Pessoa;
import com.experian.cadastro.enums.Score;

public class PessoaDTOLista {

    private String nome;
    private String cidade;
    private String estado;
    private String scoreDescricao;

    public PessoaDTOLista() {
    }

    public PessoaDTOLista(Pessoa pessoa, Score scoreDescricao) {
        this.nome = pessoa.getNome();
        this.cidade = pessoa.getCidade();
        this.estado = pessoa.getEstado();
        this.scoreDescricao = scoreDescricao.getDescricao();
    }

    public PessoaDTOLista(String nome, String cidade, String estado, Score scoreDescricao) {
        this.nome = nome;
        this.cidade = cidade;
        this.estado = estado;
        this.scoreDescricao = scoreDescricao.getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public String getScoreDescricao() {
        return scoreDescricao;
    }

    public void setScoreDescricao(String scoreDescricao) {
        this.scoreDescricao = scoreDescricao;
    }
}
