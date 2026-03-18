# 05. Методы

## Что такое метод

Метод - это именованный блок кода, который выполняет конкретную задачу.

Он помогает:

- не дублировать код
- делать программу понятнее
- разбивать большую задачу на маленькие

## Пример метода

```java
public static void sayHello() {
    System.out.println("Hello!");
}
```

Вызов:

```java
sayHello();
```

## Структура метода

```java
модификатор static типВозврата имя(параметры) {
    // тело метода
}
```

## Метод без возвращаемого значения

Если метод ничего не возвращает, пишут `void`.

```java
public static void printSquare(int number) {
    System.out.println(number * number);
}
```

## Метод с возвращаемым значением

```java
public static int sum(int a, int b) {
    return a + b;
}
```

Использование:

```java
int result = sum(3, 4);
System.out.println(result);
```

## Параметры и аргументы

- параметры - это переменные в объявлении метода
- аргументы - это реальные значения при вызове

Пример:

```java
public static int multiply(int a, int b) {
    return a * b;
}

int result = multiply(2, 5);
```

Здесь `a` и `b` - параметры, а `2` и `5` - аргументы.

## return

`return` завершает работу метода и может вернуть значение.

```java
public static boolean isPositive(int number) {
    return number > 0;
}
```

## Почему методы важны

Сравни:

```java
System.out.println(2 * 2);
System.out.println(3 * 3);
System.out.println(4 * 4);
```

И:

```java
printSquare(2);
printSquare(3);
printSquare(4);
```

Во втором случае код проще поддерживать.

## Практика

Напиши методы:

1. `printGreeting(String name)`
2. `sum(int a, int b)`
3. `isEven(int number)`
4. `max(int a, int b)`
