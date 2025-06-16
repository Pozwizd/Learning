package org.example.Java._2_Collections.Task;
/*
И снова заказчик решил изменить работу программы.
На этот раз он хочет, чтобы в списке гос номеров автомобилей были и их владельцы.
По гос номеру автомобиля можно будет узнать его владельца.
За одним автомобилем может быть закреплен только один владелец,
но один владелец может быть закреплен за многими автомобилями.
В случае внесения в список гос номера, который уже был внесен ранее, нужно обновлять его владельца.

Напишите программу, которая во время работы будет ожидать ввода в консоли нового гос номера автомобиля,
а также его владельца и после ввода добавлять их в список.
При вводе слова "СПИСОК" будет выводить весь список гос номеров автомобилей с их владельцами и дальше ожидать команды.
При вводе слова “СТОП” будет заканчивать работу.
При вводе слова "АВТОМОБИЛИ" будет выводить весь список гос номеров автомобилей
При вводе слова "ВЛАДЕЛЬЦЫ" будет выводить весь список владельцев автомобилей.
Используйте для решения задачи коллекции. Выберите ту коллекцию,
которая будет удовлетворять требованиям заказчика и при этом будет максимально продуктивной.
*/

/*
    Ключевые моменты
    1. Ключ-значение
    2. О последовательности вывода ничего не сказано.
    3. Максимально продуктивная.


 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class Task4 {

    public static void main(String[] args) {
        Task4 task = new Task4();
        HashMap<String, String> cars = new HashMap<>();

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

    void executeCommand(String command, HashMap<String, String> cars) {
        switch (command) {
            case "СПИСОК":
                for (String car : cars.keySet()) {
                    System.out.println(car + " - " + cars.get(car));
                }
                break;
            case "СТОП":
                System.exit(0);
                break;
            case "АВТОМОБИЛИ":
                for (String car : cars.keySet()) {
                    System.out.println(car);
                }
                break;
            case "ВЛАДЕЛЬЦЫ":
                for (String owner : cars.values()) {
                    System.out.println(owner);
                }
                break;
            default:
                String[] parts = command.split(" ");
                String carNumber = parts[0];
                String ownerName = parts[1];
                if (cars.containsKey(carNumber)) {
                    cars.replace(carNumber, ownerName);
                    System.out.println("Гос. номер автомобиля " + carNumber + " обновлен, новый владелец: " + ownerName);
                } else {
                    cars.put(carNumber, ownerName);
                    System.out.println("Добавлен автомобиль: " + carNumber + ", владелец: " + ownerName);
                }
                break;
        }
    }
}
