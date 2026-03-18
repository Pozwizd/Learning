package org.example.Java._0_Syntax.newProgram.home_work_answers.lesson_008.task_3.src;

/**
 * Created by Evgeniy on 13.03.2016.
 */
public class Delivery {
    public static void main(String[] args) {
        System.out.println("Способов доставки - " + process(5));
    }

    // Проблема факториала в том, что мы не можем точно уследить за размером переменной
    // Число может не влезть в int. Нужно использовать больше
    public static int process(int num) {
        if (num == 1 || num == 0) {
            return 1;
        } else if (num < 0) {
            return 0;
        } else {
            // Рекурсия
            return num * process(num - 1);
        }
    }
}
