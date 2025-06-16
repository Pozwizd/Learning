package org.example.Java._6_StreamApi.Task;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task1 {

    static Student student1 = new Student("Roman", 'M', 25, 1, 3.5);
    static Student student2 = new Student("Ivan", 'M', 25, 2, 4.0);
    static Student student3 = new Student("Nikolay", 'M', 25, 3, 5.0);
    static Student student4 = new Student("Alexander", 'M', 25, 4, 5.5);
    static Student student5 = new Student("Vladimir", 'M', 25, 5, 6.0);
    static Student student6 = new Student("Alexander", 'M', 25, 4, 10.5);


    public static void main(String[] args) {

        System.out.println(Stream.of(student1, student2, student3, student4, student5)
                .filter(student -> student.getCourse()>2)
                .sorted(Comparator.comparing(Student::getAvgGrade).thenComparing(Student::getCourse))
        );


        Stream.of(student1, student2, student3, student4, student5, student6)
                .filter(student -> student.getCourse()>2)
                .map(student -> student.getName().toUpperCase()).distinct().forEach(System.out::println);

        Stream.of(student1, student2, student3, student4, student5, student6)
                .filter(student -> student.getName().startsWith("A"))
                .map(Student::getAvgGrade)
                .mapToDouble(getAvgGrade -> getAvgGrade).average()
                .ifPresent(System.out::println);

        System.out.println(Stream.of(student1, student2, student3, student4, student5, student6)
                .filter(student -> student.getName().startsWith("A"))
                .map(Student::getAvgGrade).map(String::valueOf)
                .collect(Collectors.joining(",")));
        ;

    }

}

