package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.Random;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        Random random = new Random();

        // Создание таблицы User(ов)
        userService.createUsersTable();

        // Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        for (int i = 0; i < 4; i++) {
            userService.saveUser(UUID.randomUUID().toString(), UUID.randomUUID().toString(), (byte) random.nextInt());
        }
        // Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
        userService.getAllUsers().forEach(System.out::println);

        // Очистка таблицы User(ов)
        userService.cleanUsersTable();

        // Удаление таблицы
        userService.dropUsersTable();
    }
}
