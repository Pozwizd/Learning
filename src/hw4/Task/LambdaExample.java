package hw4.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

public class LambdaExample {
    public static void main(String[] args) {
        Student student = new Student("Ivan", 'm', 22, 3, 8.3);
        Student student1 = new Student("Nikolay", 'm', 28, 2, 6.4);
        Student student2 = new Student("Elena", 'f', 19, 1, 8.9);
        Student student3 = new Student("Petr", 'm', 35, 4, 7);
        Student student4 = new Student("Mariya", 'f', 23, 3, 9.1);
        ArrayList<Student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);
        students.add(student2);
        students.add(student3);
        students.add(student4);
//        printStudentOverGrade(students);
        System.out.println("_____________________________");
//        testStudentOverGrade(students, new CheckOverGrade());
        System.out.println("_____________________________");
//        testStudentOverGrade(students, new StudentCheck() {
//            @Override
//            public boolean check(Student s) {
//                return s.avgGrade>8;
//            }
//        });
        System.out.println("_____________________________");
        testStudentOverGrade(students, (Student s) -> s.avgGrade > 8);
        System.out.println("_____________________________");


        LambdaExample lambdaExample = new LambdaExample();
        Collections.sort(students, (o1, o2) -> o1.course-o2.course);
        System.out.println(students);



    }

    public static void printStudentOverGrade(ArrayList<Student> students) {
        for (Student s:  students) {
            if (s.avgGrade > 8) {
                System.out.println(s);
            }
        }
    }
    public static void testStudentOverGrade(ArrayList<Student> students, Predicate<Student> sc) {
        for (Student s:  students) {
            if (sc.test(s)) {
                System.out.println(s);
            }
        }
    }
}

//interface StudentCheck {
//    boolean check(Student s);
//}
//class CheckOverGrade implements StudentCheck {
//    @Override
//    public boolean check(Student s) {
//        return s.avgGrade > 8;
//    }
//}
