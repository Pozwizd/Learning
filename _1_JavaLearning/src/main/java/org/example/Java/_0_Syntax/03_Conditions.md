# 03. Условия

## Зачем нужны условия

Условия позволяют программе принимать решения.

Пример:

```java
int age = 20;

if (age >= 18) {
    System.out.println("Доступ разрешен");
}
```

## Конструкция if

```java
if (условие) {
    // код, если условие истинно
}
```

Условие должно иметь тип `boolean`.

## if-else

```java
int temperature = 10;

if (temperature > 20) {
    System.out.println("Тепло");
} else {
    System.out.println("Прохладно");
}
```

## if-else if-else

```java
int score = 85;

if (score >= 90) {
    System.out.println("Отлично");
} else if (score >= 75) {
    System.out.println("Хорошо");
} else if (score >= 60) {
    System.out.println("Удовлетворительно");
} else {
    System.out.println("Нужно повторить тему");
}
```

## Логические операторы в условиях

- `&&` - и
- `||` - или
- `!` - не

Пример:

```java
int age = 22;
boolean hasTicket = true;

if (age >= 18 && hasTicket) {
    System.out.println("Можно войти");
}
```

## switch

`switch` удобен, когда нужно сравнивать одно значение с несколькими вариантами.

```java
int day = 3;

switch (day) {
    case 1:
        System.out.println("Понедельник");
        break;
    case 2:
        System.out.println("Вторник");
        break;
    case 3:
        System.out.println("Среда");
        break;
    default:
        System.out.println("Неизвестный день");
}
```

## Частые ошибки

- писать в `if` не `boolean`, а просто число
- забывать фигурные скобки в сложных блоках
- путать `=` и `==`

`=` присваивает значение.
`==` сравнивает значения.

## Практика

Напиши программу, которая:

1. проверяет, положительное число или нет
2. определяет, четное число или нечетное
3. выводит оценку по баллу через `if-else`
