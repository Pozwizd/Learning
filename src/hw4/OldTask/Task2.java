package hw4.OldTask;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Task2 {
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

        boolean anyUnderage = users.stream()
                .anyMatch(user -> user.getAge() < 18);
        System.out.println("Пользователи младше 18 лет присутствуют: " + anyUnderage);

        boolean allNamesGreaterThanOneChar = users.stream()
                .allMatch(user -> user.getFirstName().length() > 1);
        System.out.println("Пользователи чьи имена больше 1-го символа: " + allNamesGreaterThanOneChar);

        boolean userAboveAge50 = users.stream()
                .noneMatch(user -> user.getAge() > 50);
        System.out.println("Пользователи с возрастом больше 50 лет отсутствуют: " + userAboveAge50);

        users.stream()
                .min((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()))
                .ifPresent(youngestUser ->
                        System.out.println("Самый молодой пользователь: " + youngestUser.getFirstName() + ", Age: " + youngestUser.getAge()));

        User oldestUser = users.stream()
                .max((u1, u2) -> Integer.compare(u1.getAge(), u2.getAge()))
                .orElse(null);
        if (oldestUser != null) {
            System.out.println("Самый старший пользователь: " + oldestUser.getFirstName() + ", Age: " + oldestUser.getAge());
        }

        long countUsersWithAge18 = users.stream()
                .filter(user -> user.getAge() == 18)
                .count();
        System.out.println("Пользователи с возрастома 18 встречаются: " + countUsersWithAge18 + " раза");

        Map<Boolean, List<User>> ageGroupedUsers = users.stream()
                .collect(Collectors.partitioningBy(user -> user.getAge() < 18));
        System.out.println("Несовершеннолетние пользователи: " + ageGroupedUsers.get(true));
        System.out.println("Совершеннолетние пользователи: " + ageGroupedUsers.get(false));
    }
}
