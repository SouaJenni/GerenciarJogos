package me.souajenni.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conector {
    private Connection conexao;

    public Conector() throws SQLException {
        String usuario = System.getenv("USUARIO");
        String senha = System.getenv("PASS");
        String url = "jdbc:mysql://localhost:3306/dbjogo";
        conexao = DriverManager.getConnection(url, usuario, senha);
    }

    public Connection getConexao() {
        return conexao;
    }
}
