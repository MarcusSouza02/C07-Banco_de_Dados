package br.inatel.DAO;

import br.inatel.Model.Time;
import java.sql.SQLException;
import java.util.ArrayList;

public class TimeDAO extends ConnectionDAO {

    public boolean insertTime(Time time) {
        connectToDb();
        boolean sucesso;
        String sql = "INSERT INTO Time (nome, cor, localizacao, Tecnicos_Cpf, Estádios_Localizacao) VALUES (?, ?, ?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, time.nome);
            pst.setString(2, time.cor);
            pst.setString(3, time.localizacao);
            pst.setInt(4, time.tecnico.getCpf());
            pst.setString(5, time.estadio.localizacao);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao inserir time: " + exc.getMessage());
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

    public boolean updateTime(String nome, Time time) {
        connectToDb();
        boolean sucesso;
        String sql = "UPDATE Time SET cor = ?, localizacao = ?, Tecnicos_Cpf = ?, Estádios_Localizacao = ? WHERE nome = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, time.cor);
            pst.setString(2, time.localizacao);
            pst.setInt(3, time.tecnico.getCpf());
            pst.setString(4, time.estadio.localizacao);
            pst.setString(5, nome); // identifica qual time será atualizado
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao atualizar time: " + exc.getMessage());
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

    public boolean deleteTime(String nome) {
        connectToDb();
        boolean sucesso;
        String sql = "DELETE FROM Time WHERE nome = ?";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao deletar Time: " + exc.getMessage());
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

    public ArrayList<Time> selectTimes() {
        connectToDb();
        ArrayList<Time> times = new ArrayList<>();
        String sql = "SELECT * FROM Time";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Time time = new Time(
                        rs.getString("nome"),
                        rs.getString("cor"),
                        rs.getString("localizacao"),
                        rs.getInt("Tecnicos_Cpf"),
                        rs.getString("Estádios_Localizacao")
                );
                times.add(time);
                System.out.println("Time: " + time.nome + " - Cor: " + time.cor + " - Localização: " + time.localizacao);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao consultar time: " + exc.getMessage());
        } finally {
            try {
                con.close();
                st.close();
                rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return times;
    }
}
