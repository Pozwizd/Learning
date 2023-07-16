package hw2.Task;

/*
Создать класс, который будет хранить в себе данные пользователя,
а именно: персональный номер, имя, фамилия, возраст.
Создать коллекцию таких пользователей и добавить туда 10 элементов.
Отсортировать коллекцию по персональному номеру по возрастанию используя Comparable и Comparator.
Вывести коллекцию до и после сортировки.

 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class User implements Comparable<User> {
    private int id;
    private String firstName;
    private String lastName;
    private int age;

    public User(int id, String firstName, String lastName, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(User o) {
        return Integer.compare(this.id, o.id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

public class Task6 {
    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        users.add(new User(3, "Іван", "Петров", 25));
        users.add(new User(1, "Олександра", "Сидоренко", 30));
        users.add(new User(5, "Микола", "Коваленко", 20));
        users.add(new User(4, "Оксана", "Черненко", 35));
        users.add(new User(2, "Андрій", "Чорний", 28));
        users.add(new User(6, "Катерина", "Даниленко", 22));
        users.add(new User(10, "Тома", "Василенко", 24));
        users.add(new User(8, "Емілія", "Іваненко", 32));
        users.add(new User(7, "Давид", "Ткаченко", 27));
        users.add(new User(9, "Софія", "Кравченко", 29));

        System.out.println("Коллекция до сортировки:");
        for (User user : users) {
            System.out.println(user);
        }

        Collections.sort(users);

        System.out.println("\nКоллекция после сортировки по id:");
        for (User user : users) {
            System.out.println(user);
        }

        //users.sort(Comparator.comparingInt(User::getAge));
        Comparator<User> ageComparator = new Comparator<User>() {
            @Override
            public int compare(User user1, User user2) {
                return Integer.compare(user1.getAge(), user2.getAge());
            }
        };

        users.sort(ageComparator);

        System.out.println("\nКоллекция после сортировки по возрасту:");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
