package br.inatel.Model;

import java.util.List;

public class Time {
    public String nome;
    public String cor;
    public String localizacao;
    public Tecnicos tecnico;
    public Estadios estadio;
    public List<Jogador> jogadores;

    public Time(String nome, String cor, String localizacao, Tecnicos tecnico, Estadios estadio, List<Jogador> jogadores) {
        this.nome = nome;
        this.cor = cor;
        this.localizacao = localizacao;
        this.tecnico = tecnico;
        this.estadio = estadio;
        this.jogadores = jogadores;
    }

    public Time(String nome, String cor, String localizacao, int tecnicos_cpf, String estadio_localizacao) {
        this.nome = nome;
        this.cor = cor;
        this.localizacao = localizacao;
        this.tecnico = new Tecnicos(tecnicos_cpf, null, null, null);
        this.estadio = new Estadios(estadio_localizacao, 0, null, null);
        this.jogadores = null;
    }

    public void exibirInfo(List<ParticipacaoCampeonato> participacoes) {
        System.out.println("Time: " + nome + " - Cor: " + cor + ", Localização: " + localizacao);
        if (tecnico != null) tecnico.exibirInfo();
        if (estadio != null) estadio.exibirInfo();
        if (jogadores != null && !jogadores.isEmpty()) {
            for (Jogador j : jogadores) {
                if (j != null) j.exibirInfo();
            }
        }

        for (ParticipacaoCampeonato p : participacoes) {
            if (p.time_nome.equals(this.nome)) {
                System.out.println("Participa do Campeonato: " + p.campeonato_nome);
                break;
            }
        }
    }



    public String getNome() {
        return nome;
    }
}
