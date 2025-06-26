package br.inatel.Model;

public class Campeonato {

    public String nome;
    public String formato;
    public String divisao;

    public Campeonato(String nome, String formato, String divisao) {
        this.nome = nome;
        this.formato = formato;
        this.divisao = divisao;
    }

    public void exibirInfo() {
        System.out.println("Campeonato: " + nome + ", Formato: " + formato + ", Divisão: " + divisao);
    }

    public String getNome() {
        return nome;
    }
}
