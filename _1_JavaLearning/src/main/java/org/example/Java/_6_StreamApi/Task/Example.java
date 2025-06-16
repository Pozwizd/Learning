package org.example.Java._6_StreamApi.Task;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator; // Явно импортируем для ясности, хотя Collections.sort может его найти
import java.util.function.Predicate;


public class Example {
    public static void main(String[] args) {
        // 1. Создание списка студентов
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Ivan", 'm', 22, 3, 8.3));
        students.add(new Student("Nikolay", 'm', 28, 2, 6.4));
        students.add(new Student("Elena", 'f', 19, 1, 8.9));
        students.add(new Student("Petr", 'm', 35, 4, 7));
        students.add(new Student("Mariya", 'f', 23, 3, 9.1));

        // 2. Фильтрация студентов с использованием лямбда-выражения и Predicate
        System.out.println("Студенты с баллом выше 8:");
        // Предикат определяет условие фильтрации
        Predicate<Student> highGradePredicate = (Student s) -> s.avgGrade > 8;
        printFilteredStudents(students, highGradePredicate);

        System.out.println("_____________________________");

        // Можно передать лямбду напрямую
        System.out.println("Студенты мужского пола:");
        printFilteredStudents(students, s -> s.sex == 'm');

        System.out.println("_____________________________");

        // 3. Сортировка студентов с использованием лямбда-выражения и Comparator
        // Компаратор определяет логику сравнения для сортировки (по курсу)
        Comparator<Student> courseComparator = (s1, s2) -> Integer.compare(s1.course, s2.course);
        // Альтернативно, для простых числовых полей: (s1, s2) -> s1.course - s2.course;
        // Но Integer.compare более безопасен от переполнения, хотя для course это маловероятно.

        Collections.sort(students, courseComparator);
        System.out.println("Студенты, отсортированные по курсу:");
        students.forEach(System.out::println); // Еще один способ вывести элементы коллекции

        System.out.println("_____________________________");

        // Сортировка по имени
        Collections.sort(students, (s1, s2) -> s1.name.compareTo(s2.name));
        System.out.println("Студенты, отсортированные по имени:");
        for (Student s : students) {
            System.out.println(s);
        }
    }

    /**
     * Печатает студентов из списка, которые удовлетворяют заданному предикату (условию).
     *
     * @param studentList Список студентов.
     * @param predicate   Условие для фильтрации студентов.
     */
    public static void printFilteredStudents(ArrayList<Student> studentList, Predicate<Student> predicate) {
        for (Student s : studentList) {
            if (predicate.test(s)) { // Метод test() интерфейса Predicate применяет условие
                System.out.println(s);
            }
        }
    }
}

