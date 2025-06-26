package br.inatel.Model;

public class Jogador extends Pessoa {
    public String posicao;
    public int idade;
    public String time_nome;

    public Jogador(String nome, String posicao, int idade, int cpf, String time_nome){
        super(nome, cpf);
        this.posicao = posicao;
        this.idade = idade;
        this.time_nome = time_nome;
    }

    @Override
    public void exibirInfo(){
        System.out.println("Jogador: " + nome + ", Posição: " + posicao + ", Idade: " + idade);
    }
}
