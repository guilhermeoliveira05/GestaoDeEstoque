package com.example.gestaodeestoque;

public class Produto {
    private String nome;
    private String categoria;
    private String corModelo;
    private int quantidade;

    public Produto(String nome, String categoria, String corModelo, int quantidade) {
        this.nome = nome;
        this.categoria = categoria;
        this.corModelo = corModelo;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getCorModelo() {
        return corModelo;
    }

    public int getQuantidade() {
        return quantidade;
    }
}

