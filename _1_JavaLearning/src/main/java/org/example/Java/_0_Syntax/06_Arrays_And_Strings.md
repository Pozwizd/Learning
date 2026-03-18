# 06. Массивы и строки

## Массивы

Массив - это набор элементов одного типа.

Пример:

```java
int[] numbers = {10, 20, 30, 40};
```

## Индексы

У каждого элемента есть индекс.
Индексация начинается с `0`.

```java
int[] numbers = {10, 20, 30};

System.out.println(numbers[0]); // 10
System.out.println(numbers[1]); // 20
System.out.println(numbers[2]); // 30
```

## Создание массива

```java
int[] numbers = new int[5];
```

Это массив на 5 элементов.

После создания можно заполнять:

```java
numbers[0] = 100;
numbers[1] = 200;
```

## Длина массива

```java
System.out.println(numbers.length);
```

`length` у массива - это поле, не метод.

## Перебор массива

```java
int[] numbers = {1, 2, 3, 4, 5};

for (int i = 0; i < numbers.length; i++) {
    System.out.println(numbers[i]);
}
```

Или:

```java
for (int number : numbers) {
    System.out.println(number);
}
```

## Частые ошибки с массивами

- попытка обратиться к несуществующему индексу
- путаница между последним индексом и длиной

Если длина массива `5`, последний индекс будет `4`.

## Строки

Строка в Java - это объект типа `String`.

```java
String name = "Java";
```

## Полезные методы String

```java
String text = "Hello";

System.out.println(text.length());
System.out.println(text.toUpperCase());
System.out.println(text.toLowerCase());
System.out.println(text.charAt(1));
System.out.println(text.contains("ell"));
```

## Склейка строк

```java
String firstName = "Ivan";
String lastName = "Petrenko";

String fullName = firstName + " " + lastName;
```

## Сравнение строк

Строки не стоит сравнивать через `==`.
Обычно используют `.equals()`.

```java
String a = "cat";
String b = "cat";

System.out.println(a.equals(b));
```

## Практика

Сделай программы, которые:

1. находят сумму элементов массива
2. находят максимальный элемент массива
3. выводят строку и ее длину
4. проверяют, содержит ли строка определенное слово
