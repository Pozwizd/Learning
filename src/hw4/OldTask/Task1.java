package hw4.OldTask;

import java.util.Arrays;
import java.util.List;



public class Task1 {
    public static void main(String[] args) {
        List<User> users = Arrays.asList(
                new User("Аліса", "Кузнецова", 30, "Female"),
                new User("Максим", "Коваль", 40, "Male"),
                new User("Віталій", "Шевченко", 53, "Male"),
                new User("Анна", "Чернікова", 18, "Female"),
                new User("Антон", "Короткинников", 35, "Male"),
                new User("Алексей", "Стрельников", 18, "Male"),
                new User("Олеся", "Самарчук", 17, "Male")
        );

        users.stream()
                .sorted((u1, u2) -> u1.getFirstName().compareTo(u2.getFirstName()))
                .filter(user -> user.getAge() >= 25)
                .limit(2)
                .map(user -> user.getFirstName())
                .forEach(user -> System.out.println(user));
    }
}