package br.inatel.Model;

public class Tecnicos extends Pessoa {
    public String estilo_de_jogo;
    public String nacionalidade;

    public Tecnicos(int cpf, String nome, String estilo_de_jogo, String nacionalidade)
    {
        super(nome, cpf);
        this.estilo_de_jogo = estilo_de_jogo;
        this.nacionalidade = nacionalidade;
    }

    @Override
    public void exibirInfo() {
        System.out.println("Técnico: " + nome + ", CPF: " + cpf +
                ", Estilo: " + estilo_de_jogo + ", Nacionalidade: " + nacionalidade);
    }
}
