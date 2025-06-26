package br.inatel.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class ConnectionDAO {
    Connection con;
    PreparedStatement pst;
    Statement st;
    ResultSet rs;
    String database = "Futebol";
    String user = "root";
    String password = "99695169Mv$";
    String url;

    public ConnectionDAO() {
        this.url = "jdbc:mysql://localhost:3306/" + this.database + "?useTimezone=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";
    }

    public void connectToDb() {
        try {
            this.con = DriverManager.getConnection(this.url, this.user, this.password);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException exc) {
            System.out.println("Erro: " + exc.getMessage());
        }

    }
}
