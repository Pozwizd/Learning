package org.example.Java._2_Collections.Task;

/*
Заказчик снова изменил требования, теперь ему не важен порядок в списке автомобилей,
но ему важно, чтоб гос номера автомобилей не повторялись.

Напишите программу, которая во время работы будет ожидать ввода в консоли нового гос номера автомобиля и после ввода добавлять его в список.
При вводе слова "СПИСОК" будет выводить весь список гос номеров автомобилей и дальше ожидать команды.
При вводе слова “СТОП” будет заканчивать работу.
Выберите ту коллекцию, которая будет удовлетворять требованиям заказчика и при этом будет максимально продуктивной.
*/

/*
    Ключевые моменты
    1. Не важен порядок
    2. Отсутствие повторов
    3. Максимальная эффективность
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Task3 {

    public static void main(String[] args) {
        Task3 task = new Task3();
        HashSet<String> cars = new HashSet<>();

        while (true) {
            String command = task.readCommand();
            task.executeCommand(command, cars);
        }
    }

    String readCommand() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Введите гос. номер автомобиля или команду: ");
        String input = null;
        try {
            input = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return input;
    }

    void executeCommand(String command, HashSet<String> cars) {
        switch (command) {
            case "СПИСОК":
                for (String car : cars) {
                    System.out.println(car);
                }
                break;
            case "СТОП":
                System.exit(0);
                break;
            default:
                if (cars.contains(command)) {
                    System.out.println("Автомобиль с таким гос. номером уже есть в списке: " + command);
                } else {
                    cars.add(command);
                    System.out.println("Добавлен автомобиль  с гос.номером : " + command);
                }
                break;
        }
    }
}