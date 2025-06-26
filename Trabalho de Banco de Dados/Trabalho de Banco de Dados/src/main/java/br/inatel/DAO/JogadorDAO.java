package br.inatel.DAO;

import br.inatel.Model.Jogador;
import java.sql.SQLException;
import java.util.ArrayList;

public class JogadorDAO extends ConnectionDAO {

    public boolean insertJogador(Jogador jogador) {
        connectToDb();
        boolean sucesso;
        String sql = "INSERT INTO Jogador (nome, posicao, idade, cpf, time_nome) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jogador.getNome());
            pst.setString(2, jogador.posicao);
            pst.setInt(3, jogador.idade);
            pst.setInt(4, jogador.getCpf());
            pst.setString(5, jogador.time_nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao inserir jogador: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close(); pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updateJogador(int cpf, Jogador jogador) {
        connectToDb();
        boolean sucesso;
        String sql = "UPDATE Jogador SET nome=?, posicao=?, idade=?, time_nome=? WHERE cpf=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, jogador.getNome());
            pst.setString(2, jogador.posicao);
            pst.setInt(3, jogador.idade);
            pst.setString(4, jogador.time_nome);
            pst.setInt(5, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao atualizar jogador: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close(); pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean deleteJogador(int cpf) {
        connectToDb();
        boolean sucesso;
        String sql = "DELETE FROM Jogador WHERE cpf=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao deletar jogador: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close(); pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Jogador> selectJogadores() {
        connectToDb();
        ArrayList<Jogador> jogadores = new ArrayList<>();
        String sql = "SELECT * FROM Jogador";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Jogador jogador = new Jogador(
                        rs.getString("nome"),
                        rs.getString("posicao"),
                        rs.getInt("idade"),
                        rs.getInt("cpf"),
                        rs.getString("time_nome")
                );
                jogadores.add(jogador);
                System.out.println("Jogador: " + jogador.getNome() + " - Time: " + jogador.time_nome);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao consultar jogadores: " + exc.getMessage());
        } finally {
            try {
                con.close(); st.close(); rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return jogadores;
    }
}
