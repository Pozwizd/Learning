package hw4.OldTask;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Task3 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User("Аліса", "Кузнецова", 30, "Female"));
        users.add(new User("Максим", "Коваль", 40, "Male"));
        users.add(new User("Віталій", "Шевченко", 53, "Male"));
        users.add(new User("Анна", "Чернікова", 18, "Female"));
        users.add(new User("Антон", "Короткинников", 35, "Male"));
        users.add(new User("Алексей", "Стрельников", 18, "Male"));
        users.add(new User("Олеся", "Самарчук", 17, "Male"));

        // Дубликаты
        users.add(new User("Аліса", "Кузнецова", 30, "Female"));
        users.add(new User("Максим", "Коваль", 40, "Male"));

        List<User> distinctUsers = users.stream()
                .distinct()
                .collect(Collectors.toList());

        for (User user : distinctUsers) {
            System.out.println(user.getFirstName() + " " + user.getLastName());
        }
    }
}