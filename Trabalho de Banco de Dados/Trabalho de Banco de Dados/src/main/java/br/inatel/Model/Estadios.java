package br.inatel.Model;

public class Estadios {

    public String localizacao;
    public int capacidade;
    public String gramado;
    public String nome;

    public Estadios(String localizacao, int capacidade, String gramado, String nome) {
        this.localizacao = localizacao;
        this.capacidade = capacidade;
        this.gramado = gramado;
        this.nome = nome;
    }

    public void exibirInfo() {
        System.out.println("Estádio: " + nome + " - Localização: " + localizacao + ", Capacidade: " + capacidade + ", Gramado: " + gramado);
    }
}