package br.inatel.Model;

public abstract class Pessoa {
    protected String nome;
    protected int cpf;

    public Pessoa(String nome, int cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public int getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public abstract void exibirInfo();
}
