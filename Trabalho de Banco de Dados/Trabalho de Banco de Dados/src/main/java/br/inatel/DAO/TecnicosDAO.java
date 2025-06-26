package br.inatel.DAO;

import br.inatel.Model.Tecnicos;
import java.sql.SQLException;
import java.util.ArrayList;

public class TecnicosDAO extends ConnectionDAO {

    public boolean insertTecnico(Tecnicos tecnico) {
        connectToDb();
        boolean sucesso;
        String sql = "INSERT INTO Tecnicos (Cpf, nome, estilo_de_jogo, nacionalidade) VALUES (?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, tecnico.getCpf());
            pst.setString(2, tecnico.getNome());
            pst.setString(3, tecnico.estilo_de_jogo);
            pst.setString(4, tecnico.nacionalidade);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao inserir técnico: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean updateTecnico(int cpf, Tecnicos tecnico) {
        connectToDb();
        boolean sucesso;
        String sql = "UPDATE Tecnicos SET nome = ?, estilo_de_jogo = ?, nacionalidade = ? WHERE Cpf = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, tecnico.getNome());
            pst.setString(2, tecnico.estilo_de_jogo);
            pst.setString(3, tecnico.nacionalidade);
            pst.setInt(4, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao atualizar técnico: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public boolean deleteTecnico(int cpf) {
        connectToDb();
        boolean sucesso;
        String sql = "DELETE FROM Tecnicos WHERE Cpf = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setInt(1, cpf);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao deletar técnico: " + exc.getMessage());
            sucesso = false;
        } finally {
            try {
                con.close();
                pst.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return sucesso;
    }

    public ArrayList<Tecnicos> selectTecnicos() {
        connectToDb();
        ArrayList<Tecnicos> tecnicos = new ArrayList<>();
        String sql = "SELECT * FROM Tecnicos";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            System.out.println("Lista de técnicos:");
            while (rs.next()) {
                Tecnicos tecnico = new Tecnicos(
                        rs.getInt("Cpf"),
                        rs.getString("nome"),
                        rs.getString("estilo_de_jogo"),
                        rs.getString("nacionalidade")
                );
                System.out.println("CPF: " + tecnico.getCpf() + ", Nome: " + tecnico.getNome());
                System.out.println("-----------------------");
                tecnicos.add(tecnico);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao consultar técnicos: " + exc.getMessage());
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return tecnicos;
    }
}
