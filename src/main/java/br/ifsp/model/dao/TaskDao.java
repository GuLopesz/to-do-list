package br.ifsp.model.dao;
import br.ifsp.model.entities.Task;

import java.util.List;

public interface TaskDao {
    List<Task> findAll();
    List<Task> findByName(String name);
    List<Task> findById(int id);
    void add(Task task);
    void deleteById(Integer id);
    void updateStatus(Integer id);
}
