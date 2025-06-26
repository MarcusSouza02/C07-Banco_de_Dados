package br.inatel.Model;

public class ParticipacaoCampeonato {
    public String time_nome;
    public int tecnico_cpf;
    public String campeonato_nome;

    public ParticipacaoCampeonato(String time_nome, int tecnico_cpf, String campeonato_nome) {
        this.time_nome = time_nome;
        this.tecnico_cpf = tecnico_cpf;
        this.campeonato_nome = campeonato_nome;
    }

    public void exibirInfo() {
        System.out.println("Time: " + time_nome + " participa do campeonato " + campeonato_nome + " com técnico CPF " + tecnico_cpf);
    }
}
