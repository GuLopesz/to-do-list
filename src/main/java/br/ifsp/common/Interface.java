package br.ifsp.common;

import br.ifsp.db.Db;
import br.ifsp.model.dao.impl.TaskDaoImpl;
import br.ifsp.model.entities.Task;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class Interface {
    public static void menu() {
        Scanner sc = new Scanner(System.in);
        int op;

        Connection connection = Db.getConnection();
        Db.createTable();
        TaskDaoImpl dao = new TaskDaoImpl(connection);
        //dao.add(new Task(10, "m", false));

        do {
            System.out.print("\n\tTO DO LIST\n\n");
            System.out.println("1. Listar Tasks");
            System.out.println("2. Adicionar Task");
            System.out.println("3. Remover Task");
            System.out.println("4. Filtrar Tasks");
            System.out.println("5. Alterar Status");
            System.out.println("6. Buscar por nome");
            System.out.println("7. Buscar por ID");
            System.out.println("8. Sair");
            System.out.print("Escolha uma opção: ");

            op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:{
                    List<Task> tasks = dao.findAll();
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    break;}
                case 2: {
                    System.out.print("Digite o nome da Task: ");
                    String name = sc.nextLine();
                    dao.add(new Task(name));
                    break;}
                case 3:{
                    List<Task> tasks = dao.findAll();
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    System.out.print("Digite o id da Task a ser removida: ");
                    Integer id = sc.nextInt();
                    dao.deleteById(id);
                    break;}
                case 4:{

                    break;}
                case 5:{
                    List<Task> tasks = dao.findAll();
                    for (Task task : tasks) {
                        System.out.println(task);
                    }
                    System.out.print("Digite o id da Task: ");
                    Integer id = sc.nextInt();
                    dao.updateStatus(id);

                    break;}
                case 6:{
                    System.out.print("Digite o nome da Task: ");
                    String name = sc.nextLine();
                    List<Task> byName = dao.findByName(name);
                    System.out.println(byName);
                    break;}
                case 7:{
                    System.out.print("Digite o id da Task: ");
                    Integer id = sc.nextInt();
                    List<Task> byId = dao.findById(id);
                    System.out.println(byId);
                    break;}

                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 8);
    }



}
