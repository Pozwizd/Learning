package org.example.Java._6_StreamApi;

import java.util.*;
import java.util.stream.*;

public class StreamApiExample {

    public static void main(String[] args) {

        // Простой список чисел для работы с Stream API
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Пример 1: Фильтрация чисел (простой фильтр четных чисел)
        System.out.println("Пример 1: Фильтрация четных чисел");
        List<Integer> evenNumbers = numbers.stream()
                .filter(n -> n % 2 == 0)  // Отбираем четные числа
                .collect(Collectors.toList());  // Собираем в новый список
        System.out.println(evenNumbers); // Вывод: [2, 4, 6, 8, 10]

        // Пример 2: Преобразование элементов (умножение каждого числа на 2)
        System.out.println("\nПример 2: Преобразование элементов (умножение на 2)");
        List<Integer> doubledNumbers = numbers.stream()
                .map(n -> n * 2)  // Умножаем каждый элемент на 2
                .collect(Collectors.toList());  // Собираем в новый список
        System.out.println(doubledNumbers);  // Вывод: [2, 4, 6, 8, 10, 12, 14, 16, 18, 20]

        // Пример 3: Сортировка элементов
        System.out.println("\nПример 3: Сортировка элементов");
        List<Integer> sortedNumbers = numbers.stream()
                .sorted()  // Сортируем числа
                .collect(Collectors.toList());  // Собираем в новый список
        System.out.println(sortedNumbers);  // Вывод: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // Пример 4: Суммирование всех чисел
        System.out.println("\nПример 4: Суммирование всех чисел");
        int sum = numbers.stream()
                .mapToInt(Integer::intValue)  // Преобразуем в int
                .sum();  // Суммируем все числа
        System.out.println("Сумма чисел: " + sum);  // Вывод: Сумма чисел: 55

        // Пример 5: Использование reduce для подсчета произведения всех чисел
        System.out.println("\nПример 5: Использование reduce для подсчета произведения");
        Optional<Integer> product = numbers.stream()
                .reduce((a, b) -> a * b);  // Считаем произведение всех чисел
        product.ifPresent(p -> System.out.println("Произведение чисел: " + p));  // Вывод: Произведение чисел: 3628800

        // Пример 6: Группировка элементов по четности
        System.out.println("\nПример 6: Группировка элементов по четности");
        Map<Boolean, List<Integer>> groupedByParity = numbers.stream()
                .collect(Collectors.groupingBy(n -> n % 2 == 0));  // Группируем по четности
        System.out.println(groupedByParity);  // Вывод: {false=[1, 3, 5, 7, 9], true=[2, 4, 6, 8, 10]}

        // Пример 7: Применение промежуточных и конечных операций
        System.out.println("\nПример 7: Применение промежуточных и конечных операций");
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)  // Фильтруем четные числа
                .map(n -> n * 2)  // Умножаем на 2
                .sorted(Comparator.reverseOrder())  // Сортируем в обратном порядке
                .collect(Collectors.toList());  // Собираем в новый список
        System.out.println(result);  // Вывод: [20, 16, 12, 8, 4]

        // Пример 8: Преобразование элементов в коллекцию Set (удаление дубликатов)
        System.out.println("\nПример 8: Преобразование в Set (удаление дубликатов)");
        List<Integer> withDuplicates = Arrays.asList(1, 2, 2, 3, 4, 4, 5, 6, 6);
        Set<Integer> uniqueNumbers = withDuplicates.stream()
                .collect(Collectors.toSet());  // Преобразуем в Set для удаления дубликатов
        System.out.println(uniqueNumbers);  // Вывод: [1, 2, 3, 4, 5, 6]

        // Пример 9: Работа с объектами - фильтрация и преобразование строк
        System.out.println("\nПример 9: Работа с объектами - фильтрация и преобразование строк");
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date", "elderberry", "fig");
        List<String> longWords = words.stream()
                .filter(word -> word.length() > 5)  // Фильтруем слова длиной больше 5 символов
                .map(String::toUpperCase)  // Преобразуем слова в верхний регистр
                .sorted()  // Сортируем по алфавиту
                .collect(Collectors.toList());  // Собираем в новый список
        System.out.println(longWords);  // Вывод: [ELDERBERRY]

        // Пример 10: Применение метода flatMap для работы с вложенными коллекциями
        System.out.println("\nПример 10: Применение flatMap для работы с вложенными коллекциями");
        List<List<Integer>> nestedNumbers = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        List<Integer> flatList = nestedNumbers.stream()
                .flatMap(List::stream)  // Преобразуем вложенные списки в один
                .collect(Collectors.toList());  // Собираем в новый список
        System.out.println(flatList);  // Вывод: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    }
}
