package br.inatel;

import br.inatel.DAO.*;
import br.inatel.Model.*;

import java.util.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TecnicosDAO tecnicosDAO = new TecnicosDAO();
        JogadorDAO jogadorDAO = new JogadorDAO();
        TimeDAO timeDAO = new TimeDAO();
        CampeonatoDAO campeonatoDAO = new CampeonatoDAO();
        EstadiosDAO estadiosDAO = new EstadiosDAO();

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Inserir dado");
            System.out.println("2. Atualizar dado");
            System.out.println("3. Deletar dado");
            System.out.println("4. Consultar dados");
            System.out.println("0. Sair");
            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 0) break;

            switch (opcao) {
                case 1 -> MenuInsert.executar(scanner, tecnicosDAO, jogadorDAO, timeDAO, campeonatoDAO, estadiosDAO);
                case 2 -> MenuUpdate.executar(scanner, tecnicosDAO, jogadorDAO, timeDAO, campeonatoDAO, estadiosDAO);
                case 3 -> MenuDelete.executar(scanner, tecnicosDAO, jogadorDAO, timeDAO, campeonatoDAO, estadiosDAO);
                case 4 -> MenuSelect.executar(scanner, tecnicosDAO, jogadorDAO, timeDAO, campeonatoDAO, estadiosDAO);
                default -> System.out.println("Opção inválida.");
            }
        }

        System.out.println("Encerrando o programa.");
        scanner.close();
    }
}

class MenuInsert {
    public static void executar(Scanner scanner, TecnicosDAO tecnicosDAO, JogadorDAO jogadorDAO, TimeDAO timeDAO, CampeonatoDAO campeonatoDAO, EstadiosDAO estadiosDAO) {
        System.out.println("\n-- Inserção de Dados --");
        System.out.println("1. Técnico");
        System.out.println("2. Jogador");
        System.out.println("3. Time");
        System.out.println("4. Campeonato");
        System.out.println("5. Estádio");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> {
                System.out.print("CPF: ");
                int cpf = scanner.nextInt(); scanner.nextLine();
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Estilo de jogo: ");
                String estilo = scanner.nextLine();
                System.out.print("Nacionalidade: ");
                String nac = scanner.nextLine();
                tecnicosDAO.insertTecnico(new Tecnicos(cpf, nome, estilo, nac));
            }
            case 2 -> {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Posição: ");
                String pos = scanner.nextLine();
                System.out.print("Idade: ");
                int idade = scanner.nextInt(); scanner.nextLine();
                System.out.print("CPF: ");
                int cpf = scanner.nextInt(); scanner.nextLine();
                System.out.print("Nome do time: ");
                String time = scanner.nextLine();
                jogadorDAO.insertJogador(new Jogador(nome, pos, idade, cpf, time));
            }
            case 3 -> {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Cor: ");
                String cor = scanner.nextLine();
                System.out.print("Localização: ");
                String loc = scanner.nextLine();
                System.out.print("CPF do Técnico: ");
                int cpfTec = scanner.nextInt(); scanner.nextLine();
                System.out.print("Localização do Estádio: ");
                String estLoc = scanner.nextLine();
                timeDAO.insertTime(new Time(nome, cor, loc, cpfTec, estLoc));
            }
            case 4 -> {
                System.out.print("Nome: ");
                String nome = scanner.nextLine();
                System.out.print("Formato: ");
                String formato = scanner.nextLine();
                System.out.print("Divisão: ");
                String divisao = scanner.nextLine();
                campeonatoDAO.insertCampeonato(new Campeonato(nome, formato, divisao));
            }
            case 5 -> {
                System.out.print("Localização: ");
                String local = scanner.nextLine();
                System.out.print("Capacidade: ");
                int cap = scanner.nextInt(); scanner.nextLine();
                System.out.print("Tipo de gramado: ");
                String gram = scanner.nextLine();
                System.out.print("Nome do estádio: ");
                String nome = scanner.nextLine();
                estadiosDAO.insertEstadio(new Estadios(local, cap, gram, nome));
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}

class MenuUpdate {
    public static void executar(Scanner scanner, TecnicosDAO tecnicosDAO, JogadorDAO jogadorDAO, TimeDAO timeDAO, CampeonatoDAO campeonatoDAO, EstadiosDAO estadiosDAO) {
        System.out.println("\n-- Atualização de Dados --");
        System.out.println("1. Técnico");
        System.out.println("2. Jogador");
        System.out.println("3. Time");
        System.out.println("4. Campeonato");
        System.out.println("5. Estádio");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> {
                System.out.print("CPF do técnico a atualizar: ");
                int cpf = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo estilo: ");
                String estilo = scanner.nextLine();
                System.out.print("Nova nacionalidade: ");
                String nac = scanner.nextLine();
                tecnicosDAO.updateTecnico(cpf, new Tecnicos(cpf, nome, estilo, nac));
            }
            case 2 -> {
                System.out.print("CPF do jogador a atualizar: ");
                int cpf = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Nova posição: ");
                String pos = scanner.nextLine();
                System.out.print("Nova idade: ");
                int idade = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo nome do time: ");
                String time = scanner.nextLine();
                jogadorDAO.updateJogador(cpf, new Jogador(nome, pos, idade, cpf, time));
            }
            case 3 -> {
                System.out.print("Nome do time a atualizar: ");
                String nome = scanner.nextLine();
                System.out.print("Nova cor: ");
                String cor = scanner.nextLine();
                System.out.print("Nova localização: ");
                String loc = scanner.nextLine();
                System.out.print("Novo CPF do técnico: ");
                int cpfTec = scanner.nextInt(); scanner.nextLine();
                System.out.print("Nova localização do estádio: ");
                String estLoc = scanner.nextLine();
                timeDAO.updateTime(nome, new Time(nome, cor, loc, cpfTec, estLoc));
            }
            case 4 -> {
                System.out.print("Nome do campeonato a atualizar: ");
                String nome = scanner.nextLine();
                System.out.print("Novo formato: ");
                String formato = scanner.nextLine();
                System.out.print("Nova divisão: ");
                String div = scanner.nextLine();
                campeonatoDAO.updateCampeonato(nome, new Campeonato(nome, formato, div));
            }
            case 5 -> {
                System.out.print("Localização do estádio a atualizar: ");
                String loc = scanner.nextLine();
                System.out.print("Nova capacidade: ");
                int cap = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo tipo de gramado: ");
                String gram = scanner.nextLine();
                System.out.print("Novo nome do estádio: ");
                String nome = scanner.nextLine();
                estadiosDAO.updateEstadio(loc, new Estadios(loc, cap, gram, nome));
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}

class MenuDelete {
    public static void executar(Scanner scanner, TecnicosDAO tecnicosDAO, JogadorDAO jogadorDAO, TimeDAO timeDAO, CampeonatoDAO campeonatoDAO, EstadiosDAO estadiosDAO) {
        System.out.println("\n-- Remoção de Dados --");
        System.out.println("1. Técnico");
        System.out.println("2. Jogador");
        System.out.println("3. Time");
        System.out.println("4. Campeonato");
        System.out.println("5. Estádio");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> {
                System.out.print("CPF do técnico a deletar: ");
                int cpf = scanner.nextInt(); scanner.nextLine();
                tecnicosDAO.deleteTecnico(cpf);
            }
            case 2 -> {
                System.out.print("CPF do jogador a deletar: ");
                int cpf = scanner.nextInt(); scanner.nextLine();
                jogadorDAO.deleteJogador(cpf);
            }
            case 3 -> {
                System.out.print("Nome do time a deletar: ");
                String nome = scanner.nextLine();
                timeDAO.deleteTime(nome);
            }
            case 4 -> {
                System.out.print("Nome do campeonato a deletar: ");
                String nome = scanner.nextLine();
                campeonatoDAO.deleteCampeonato(nome);
            }
            case 5 -> {
                System.out.print("Localização do estádio a deletar: ");
                String loc = scanner.nextLine();
                estadiosDAO.deleteEstadio(loc);
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}

class MenuSelect {
    public static void executar(Scanner scanner, TecnicosDAO tecnicosDAO, JogadorDAO jogadorDAO, TimeDAO timeDAO, CampeonatoDAO campeonatoDAO, EstadiosDAO estadiosDAO) {
        System.out.println("\n-- Consulta de Dados --");
        System.out.println("1. Técnico");
        System.out.println("2. Jogador");
        System.out.println("3. Time");
        System.out.println("4. Campeonato");
        System.out.println("5. Estádio");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        System.out.println("\nTipos de consulta:");
        System.out.println("1. Todos os dados");
        System.out.println("2. Um dado específico");
        System.out.println("3. Consulta com JOIN");
        int tipo = scanner.nextInt();
        scanner.nextLine();

        switch (escolha) {
            case 1 -> {
                if (tipo == 1) tecnicosDAO.selectTecnicos();
                else if (tipo == 2) {
                    System.out.print("CPF: ");
                    int cpf = scanner.nextInt(); scanner.nextLine();
                    tecnicosDAO.selectTecnicos().stream().filter(t -> t.getCpf() == cpf).forEach(System.out::println);
                }
            }
            case 2 -> {
                if (tipo == 1) jogadorDAO.selectJogadores();
                else if (tipo == 2) {
                    System.out.print("CPF: ");
                    int cpf = scanner.nextInt(); scanner.nextLine();
                    jogadorDAO.selectJogadores().stream().filter(j -> j.getCpf() == cpf).forEach(System.out::println);
                }
            }
            case 3 -> {
                if (tipo == 1) timeDAO.selectTimes();
                else if (tipo == 2) {
                    System.out.print("Nome do time: ");
                    String nome = scanner.nextLine();
                    timeDAO.selectTimes().stream().filter(t -> t.nome.equalsIgnoreCase(nome)).forEach(System.out::println);
                } else if (tipo == 3) {
                    System.out.println("Exibindo times e seus técnicos:");
                    List<Time> times = timeDAO.selectTimes();
                    List<Tecnicos> tecnicos = tecnicosDAO.selectTecnicos();
                    for (Time t : times) {
                        for (Tecnicos tec : tecnicos) {
                            if (t.tecnico.getCpf() == tec.getCpf()) {
                                System.out.println("Time: " + t.nome + " - Técnico: " + tec.getNome());
                            }
                        }
                    }
                }
            }
            case 4 -> {
                if (tipo == 1) campeonatoDAO.selectCampeonatos();
                else if (tipo == 2) {
                    System.out.print("Nome do campeonato: ");
                    String nome = scanner.nextLine();
                    campeonatoDAO.selectCampeonatos().stream().filter(c -> c.nome.equalsIgnoreCase(nome)).forEach(System.out::println);
                }
            }
            case 5 -> {
                if (tipo == 1) estadiosDAO.selectEstadios();
                else if (tipo == 2) {
                    System.out.print("Localização: ");
                    String loc = scanner.nextLine();
                    estadiosDAO.selectEstadios().stream().filter(e -> e.localizacao.equalsIgnoreCase(loc)).forEach(System.out::println);
                }
            }
            default -> System.out.println("Opção inválida.");
        }
    }
}