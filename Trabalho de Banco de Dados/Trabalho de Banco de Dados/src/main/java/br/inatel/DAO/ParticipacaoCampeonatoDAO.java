package br.inatel.DAO;

import br.inatel.Model.ParticipacaoCampeonato;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipacaoCampeonatoDAO extends ConnectionDAO {

    public boolean insertParticipacao(ParticipacaoCampeonato p) {
        connectToDb();
        boolean sucesso;
        String sql = "INSERT INTO time_has_Campeonato (time_nome, Tecnicos_Cpf, Campeonato_nome) VALUES (?, ?, ?)";
        try {
            pst = con.prepareStatement(sql);
            pst.setString(1, p.time_nome);
            pst.setInt(2, p.tecnico_cpf);
            pst.setString(3, p.campeonato_nome);
            pst.execute();
            sucesso = true;
        } catch (SQLException exc) {
            System.out.println("Erro ao inserir participação: " + exc.getMessage());
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

    public ArrayList<ParticipacaoCampeonato> selectParticipacoes() {
        connectToDb();
        ArrayList<ParticipacaoCampeonato> participacoes = new ArrayList<>();
        String sql = "SELECT * FROM time_has_Campeonato";
        try {
            st = con.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ParticipacaoCampeonato p = new ParticipacaoCampeonato(
                        rs.getString("time_nome"),
                        rs.getInt("Tecnicos_Cpf"),
                        rs.getString("Campeonato_nome")
                );
                participacoes.add(p);
            }
        } catch (SQLException exc) {
            System.out.println("Erro ao consultar participações: " + exc.getMessage());
        } finally {
            try {
                con.close(); st.close(); rs.close();
            } catch (SQLException exc) {
                System.out.println("Erro ao fechar conexão: " + exc.getMessage());
            }
        }
        return participacoes;
    }
}
