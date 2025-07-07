package br.ifsp.model.entities;

import br.ifsp.common.Colors;

public class Task {
    private int id;
    private String name;
    private boolean status;

    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    public Task(Integer id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
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

    @Override
    public String toString() {
        String color = status ? Colors.GREEN : Colors.RED;
        return color + String.format("%-4d | %-15s | %-12s", id, name,
                (status ? "Concluída" : "Em andamento")) + Colors.RESET;
    }


}
