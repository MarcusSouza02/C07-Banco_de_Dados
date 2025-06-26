package br.inatel.DAO;

import br.inatel.Model.Estadios;
import java.sql.SQLException;
import java.util.ArrayList;

public class EstadiosDAO extends ConnectionDAO {

    public boolean insertEstadio(Estadios estadio) {
        connectToDb();
        boolean sucesso;
        String sql = "INSERT INTO Estádios (Localizacao, Capacidade, gramado, nome) VALUES (?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, estadio.localizacao);
            pst.setInt(2, estadio.capacidade);
            pst.setString(3, estadio.gramado);
            pst.setString(4, estadio.nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao inserir estádio: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close(); pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updateEstadio(String localizacao, Estadios estadio) {
        connectToDb();
        boolean sucesso;
        String sql = "UPDATE Estádios SET Capacidade=?, gramado=?, nome=? WHERE Localizacao=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, estadio.capacidade);
            pst.setString(2, estadio.gramado);
            pst.setString(3, estadio.nome);
            pst.setString(4, localizacao);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao atualizar estádio: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close(); pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean deleteEstadio(String localizacao) {
        connectToDb();
        boolean sucesso;
        String sql = "DELETE FROM Estádios WHERE Localizacao=?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, localizacao);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao deletar estádio: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close(); pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Estadios> selectEstadios() {
        connectToDb();
        ArrayList<Estadios> estadios = new ArrayList<>();
        String sql = "SELECT * FROM Estádios";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Estadios est = new Estadios(
                        rs.getString("Localizacao"),
                        rs.getInt("Capacidade"),
                        rs.getString("gramado"),
                        rs.getString("nome")
                );
                estadios.add(est);
                System.out.println("Estádio: " + est.nome + " - Capacidade: " + est.capacidade);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao consultar estádios: " + exc.getMessage());
        } finally {
            try {
                con.close(); st.close(); rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar: " + exc.getMessage());
            }
        }
        return estadios;
    }
}
