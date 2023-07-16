package hw2.Task;

/*
  Предположим, что Вы разрабатываете программу для учета автомобилей на парковке.
  По требованию заказчика нужно будет сохранять гос номера автомобиля в виде списка.
  Удалять из списка автомобили не нужно, только добавлять их в конец списка.
  При этом допускается, что гос номера могут повторяться.
  Напишите программу, которая во время работы будет ожидать ввода в консоли нового гос номера автомобиля
  и после ввода добавлять его в список.
  При вводе слова "СПИСОК" будет выводить весь список гос номеров автомобилей и дальше ожидать команды.
  При вводе слова “СТОП” будет заканчивать работу.
  Используйте для решения задачи коллекции. Выберите ту коллекцию,
  которая будет удовлетворять требованиям заказчика и при этом будет максимально продуктивной.

 */


/*
   Ключевые моменты
   1. Номера могут повторятся
   2. Номера должны добавляться только в конец списка
 */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class Task1 {

    public static void main(String[] args) {
        Task1 task = new Task1();
        ArrayList<String> cars = new ArrayList<>();

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

    void executeCommand(String command, List<String> cars) {
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
                    System.out.println("Гос. номер уже добавлен: " + command);
                } else {
                    cars.add(command);
                    System.out.println("Добавлен автомобиль с гос.номером : " + command);
                }
                break;
        }
    }
}