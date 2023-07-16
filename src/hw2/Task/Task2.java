package hw2.Task;

/*
Заказчик из прошлого задания решил обновить требования к программе.
Теперь нужно будет иметь возможность вставлять новые автомобили не только в конец списка, но и в начало или в середину.

Напишите программу, которая во время работы будет ожидать ввода в консоли нового гос номера автомобиля,
затем номер по которому этот автомобиль должен быть в списке и после ввода добавлять его в список.
Если номер указан больше, чем в списке автомобилей, тогда добавить запись в конец списка.
При вводе слова "СПИСОК" будет выводить весь список гос номеров автомобилей и дальше ожидать команды.
При вводе слова “СТОП” будет заканчивать работу. Используйте для решения задачи коллекции.
Выберите ту коллекцию, которая будет удовлетворять требованиям заказчика и при этом будет максимально продуктивной.
 */

/*
    Ключевые моменты
    1. Главный критерий эффективная вставка
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Task2 {

    public static void main(String[] args) {
        Task2 task = new Task2();
        LinkedList<String> cars = new LinkedList<>();

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

    void executeCommand(String command, LinkedList<String> cars) {
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
                String[] parts = command.split(" ");
                String carNumber = parts[0];
                int index = parts.length > 1 ? Integer.parseInt(parts[1]) : cars.size() + 1;
                if (index > cars.size() + 1) {
                    cars.addLast(carNumber);
                    System.out.println("Добавлен автомобиль с гос.номером : " + carNumber);
                } else {
                    cars.add(index - 1, carNumber);
                    System.out.println("Добавлен автомобиль с гос.номером : " + carNumber + " на позицию " + index);
                }
                break;
        }
    }
}