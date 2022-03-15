package com.experian.cadastro.enums;

public enum Score {
    INSUFICIENTE("Insuficiente"),
    INACEITAVEL("Inaceitável"),
    ACEITAVEL("Aceitável"),
    RECOMENDAVEL("Recomendável");

    private String descricao;

    Score(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
