package org.example.Java._2_Collections.Collections.Queue;

import java.util.PriorityQueue;

public class PriorityQueueExample {
    public static void main(String[] args) {
        // Создание пустой очереди с приоритетами
        PriorityQueue<User> pq = new PriorityQueue<>();

        // Добавление пользователей в очередь приоритетов
        pq.offer(new User("John", 30));
        pq.offer(new User("Alice", 25));
        pq.offer(new User("Bob", 35));
        pq.offer(new User("Kate", 20));

        // Вывод на экран элементов очереди в порядке их приоритета
        System.out.println("Элементы очереди: " + pq);

        // Извлечение пользователей из очереди приоритетов
        User first = pq.poll();
        User second = pq.poll();

        // Вывод на экран извлеченных пользователей
        System.out.println("Первый пользователь: " + first);
        System.out.println("Второй пользователь: " + second);

        // Добавление пользователей в очередь приоритетов с использованием компаратора
        PriorityQueue<User> pq2 = new PriorityQueue<>((u1, u2) -> u2.getAge() - u1.getAge());
        pq2.offer(new User("John", 30));
        pq2.offer(new User("Alice", 25));
        pq2.offer(new User("Bob", 35));
        pq2.offer(new User("Kate", 20));

        // Вывод на экран элементов очереди в порядке, определенном компаратором
        System.out.println("Элементы очереди с использованием компаратора: " + pq2);

        // Получение, но не удаление, первого пользователя очереди
        User firstUser = pq2.peek();

        // Вывод на экран первого пользователя очереди
        System.out.println("Первый пользователь очереди с использованием компаратора: " + firstUser);
    }
}

class User implements Comparable<User> {
    private final String name;
    private final int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public int compareTo(User other) {
        return Integer.compare(this.age, other.age);
    }
}