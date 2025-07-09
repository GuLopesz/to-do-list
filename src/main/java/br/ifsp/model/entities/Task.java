package br.ifsp.model.entities;

import br.ifsp.common.Colors;

public class Task {
    private int id;
    private String name;
    private boolean status;
    private String taskDate;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.status = false;

    }

    public Task(Integer id, String name, boolean status, String taskDate) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.taskDate = taskDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    @Override
    public String toString() {
        String color = status ? Colors.GREEN : Colors.RED;
        String statusStr = status ? "Concluída" : "Em andamento";
        String dateStr = (status && taskDate != null) ? " | Concluída em: " + taskDate : "";

        return color + String.format("%-4d | %-15s | %-12s%s", id, name, statusStr, dateStr) + Colors.RESET;
    }
}
