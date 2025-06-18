package br.ifsp.db;

import java.sql.*;

public class Db {

    public static Connection conn = null;
    public static Connection getConnection() {
        String url = "jdbc:sqlite:todolist.db";
        try {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
            System.out.println("Erro ao conectar com SQL: " + e.getMessage());
        }
        return conn;
    }

    public static void createTable () {
        if (conn == null) {
            throw new RuntimeException("Conexão não realizada.");
        }

        String sqlCreate = "CREATE TABLE IF NOT EXISTS tasks (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(200) NOT NULL," +
                "status BOOLEAN NOT NULL" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sqlCreate);
            System.out.println("Tabela criada com sucesso!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
