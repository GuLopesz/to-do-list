package br.ifsp.model.dao.impl;

import br.ifsp.model.dao.TaskDao;
import br.ifsp.model.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDaoImpl implements TaskDao {


    private final Connection conn;

    public TaskDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Task> findAll() {
        String sqlSelect = "SELECT id, name, status FROM tasks;";
        List<Task> tasks = new ArrayList<Task>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                tasks.add(new Task(id, name, status));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public Task findByLabel(String label) {
        return null;
    }

    @Override
    public Task findById(int id) {
        return null;
    }

    @Override
    public void add(Task task) {
        String sqlInsert = "INSERT INTO tasks (name, status) VALUES (?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, "Estudar");
            pstmt.setBoolean(2, false);
            pstmt.executeUpdate();

            pstmt.setString(1, "Ler");
            pstmt.setBoolean(2, true);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void update(Task task) {

    }
}
