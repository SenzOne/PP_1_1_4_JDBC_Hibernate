package jm.task.core.jdbc;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<User> users = new ArrayList<>(List.of(
                new User("Alice", "Smith", (byte) 25),
                new User("John", "Doe", (byte) 30),
                new User("Emma", "Johnson", (byte) 22),
                new User("Michael", "Williams", (byte) 35),
                new User("Sophia", "Brown", (byte) 28)
        ));

        UserServiceImpl service = new UserServiceImpl();
        service.createUsersTable();
        users.forEach(x -> service.saveUser(x.getName(), x.getLastName(), x.getAge()));
        service.getAllUsers().forEach(System.out::println);
        service.removeUserById(2L);
        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
