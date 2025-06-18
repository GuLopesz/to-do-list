package br.ifsp;

import br.ifsp.db.Db;
import br.ifsp.model.dao.impl.TaskDaoImpl;
import br.ifsp.model.entities.Task;

import java.sql.Connection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.print("\t\tTO DO LIST\n\n");

        Connection connection = Db.getConnection();
        Db.createTable();
        TaskDaoImpl dao = new TaskDaoImpl(connection);
        //dao.add(new Task(10, "m", false));
        List<Task> tasks = dao.findAll();
        System.out.println(tasks);

    }
}