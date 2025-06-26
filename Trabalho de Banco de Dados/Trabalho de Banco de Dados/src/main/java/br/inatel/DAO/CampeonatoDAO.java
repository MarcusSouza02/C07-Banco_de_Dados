package br.inatel.DAO;

import br.inatel.Model.Campeonato;
import java.sql.SQLException;
import java.util.ArrayList;

public class CampeonatoDAO extends ConnectionDAO {

    public boolean insertCampeonato(Campeonato camp) {
        connectToDb();
        boolean sucesso;
        String sql = "INSERT INTO Campeonato (nome, formato, divisao) VALUES (?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, camp.nome);
            pst.setString(2, camp.formato);
            pst.setString(3, camp.divisao);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao inserir campeonato: " + exc.getMessage());
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

    public boolean updateCampeonato(String nome, Campeonato camp) {
        connectToDb();
        boolean sucesso;
        String sql = "UPDATE Campeonato SET formato = ?, divisao = ? WHERE nome = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, camp.formato);
            pst.setString(2, camp.divisao);
            pst.setString(3, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao atualizar campeonato: " + exc.getMessage());
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

    public boolean deleteCampeonato(String nome) {
        connectToDb();
        boolean sucesso;
        String sql = "DELETE FROM Campeonato WHERE nome = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao deletar campeonato: " + exc.getMessage());
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

    public ArrayList<Campeonato> selectCampeonatos() {
        connectToDb();
        ArrayList<Campeonato> campeonatos = new ArrayList<>();
        String sql = "SELECT * FROM Campeonato";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Campeonato c = new Campeonato(
                        rs.getString("nome"),
                        rs.getString("formato"),
                        rs.getString("divisao")
                );
                campeonatos.add(c);
                System.out.println("Campeonato: " + c.nome + " - Divisão: " + c.divisao);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao consultar campeonatos: " + exc.getMessage());
        } finally {
            try {
                con.close(); st.close(); rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return campeonatos;
    }
}
