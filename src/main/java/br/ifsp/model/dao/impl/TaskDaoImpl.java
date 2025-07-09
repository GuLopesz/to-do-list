package br.ifsp.model.dao.impl;

import br.ifsp.model.dao.TaskDao;
import br.ifsp.model.entities.Task;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TaskDaoImpl implements TaskDao {


    private final Connection conn;

    public TaskDaoImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public List<Task> findAll() {
        String sqlSelect = "SELECT id, name, status, taskDate FROM tasks;";
        List<Task> tasks = new ArrayList<Task>();
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sqlSelect)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                String date = rs.getString("taskDate");
                tasks.add(new Task(id, name, status, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public List<Task> findByName(String name) {
        String sqlSelect = "SELECT id, name, status, taskDate FROM tasks WHERE name LIKE ?;";
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
            pstmt.setString(1, "%" + name.trim() + "%");
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                int id = rs.getInt("id");
                name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                String date = rs.getString("taskDate");
                tasks.add(new Task(id, name, status, date));

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;

    }

    @Override
    public List<Task> findById(int id) {
        String sqlSelect = "SELECT id, name, status FROM tasks WHERE id = ?;";
        List<Task> tasks = new ArrayList<>();
        try (PreparedStatement pstmt = conn.prepareStatement(sqlSelect)) {
             pstmt.setInt(1, id);
             ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
                id = rs.getInt("id");
                String name = rs.getString("name");
                boolean status = rs.getBoolean("status");
                String date = rs.getString("taskDate");
                tasks.add(new Task(id, name, status, date));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return tasks;

    }


    @Override
    public void add(Task task) {
        String sqlInsert = "INSERT INTO tasks (name, status) VALUES (?, ?);";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlInsert)) {
            pstmt.setString(1, task.getName());
            pstmt.setBoolean(2, false);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sqlDelete = "DELETE FROM tasks WHERE id = ?;";
        try(PreparedStatement pstmt = conn.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatus(Integer id) {
        String sqlUpdate = "UPDATE tasks SET status = ?, taskDate = ? WHERE id = ?;";
        try(PreparedStatement pstmt = conn.prepareStatement(sqlUpdate)) {
            pstmt.setBoolean(1, true);
            pstmt.setString(2, getCurrentTimestamp());
            pstmt.setInt(3, id);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getCurrentTimestamp(){
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(dateFormatter);
    }
}
