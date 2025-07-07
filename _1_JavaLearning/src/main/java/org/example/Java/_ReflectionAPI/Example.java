package org.example.Java._ReflectionAPI;

import java.lang.reflect.*;
import java.util.Arrays;

/**
 * Исчерпывающий конспект по Java Reflection API.
 *
 * Reflection API — это механизм в Java, который позволяет исследовать и изменять
 * поведение классов, интерфейсов, полей и методов во время выполнения программы.
 * Это мощный инструмент, который часто используется во фреймворках, таких как
 * Spring, Hibernate, и в инструментах для тестирования, например, JUnit.
 *
 * Основные классы в Reflection API находятся в пакете java.lang.reflect:
 * - Class: Представляет классы и интерфейсы в работающем Java-приложении.
 * - Field: Предоставляет информацию о полях класса и динамический доступ к ним.
 * - Method: Предоставляет информацию о методах класса и возможность их вызова.
 * - Constructor: Предоставляет информацию о конструкторах класса и возможность создавать экземпляры.
 * - Modifier: Позволяет декодировать модификаторы доступа (public, private, static и т.д.).
 * - Array: Предоставляет статические методы для динамического создания и доступа к массивам.
 */
public class Example {

    public static void main(String[] args) throws Exception {
        System.out.println("--- 1. Получение объекта Class ---");
        getClassObjects();

        System.out.println("\n--- 2. Анализ класса (поля, методы, конструкторы) ---");
        analyzeClass(Person.class);

        System.out.println("\n--- 3. Создание экземпляра класса ---");
        createInstance();

        System.out.println("\n--- 4. Доступ и изменение полей ---");
        accessAndModifyFields();

        System.out.println("\n--- 5. Вызов методов ---");
        invokeMethods();

        System.out.println("\n--- 6. Работа с приватными членами ---");
        workWithPrivateMembers();

        System.out.println("\n--- 7. Работа с массивами ---");
        workWithArrays();
    }

    /**
     * 1. Получение объекта Class
     *
     * Существует три основных способа получить объект Class для нужного нам класса.
     */
    public static void getClassObjects() {
        // Способ 1: Через литерал .class (самый простой и предпочтительный, если тип известен на этапе компиляции)
        Class<Person> personClass1 = Person.class;
        System.out.println("Через .class: " + personClass1.getName());

        // Способ 2: Через метод getClass() у экземпляра объекта (используется, когда есть объект)
        Person person = new Person("John", 30);
        Class<?> personClass2 = person.getClass();
        System.out.println("Через getClass(): " + personClass2.getName());

        // Способ 3: Через статический метод Class.forName() (используется, когда имя класса известно в виде строки во время выполнения)
        try {
            Class<?> personClass3 = Class.forName("org.example.Java._ReflectionAPI.Person");
            System.out.println("Через Class.forName(): " + personClass3.getName());
        } catch (ClassNotFoundException e) {
            System.err.println("Класс не найден!");
            e.printStackTrace();
        }
    }

    /**
     * 2. Анализ структуры класса
     *
     * С помощью объекта Class можно получить полную информацию о структуре класса.
     */
    public static void analyzeClass(Class<?> clazz) {
        System.out.println("Анализ класса: " + clazz.getCanonicalName());

        // Получение имени класса
        System.out.println("Простое имя: " + clazz.getSimpleName());
        System.out.println("Полное имя: " + clazz.getName());
        System.out.println("Каноническое имя: " + clazz.getCanonicalName());

        // Получение модификаторов класса (public, final и т.д.)
        int modifiers = clazz.getModifiers();
        System.out.println("Модификаторы: " + Modifier.toString(modifiers));

        // Получение суперкласса
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            System.out.println("Суперкласс: " + superclass.getName());
        }

        // Получение реализованных интерфейсов
        Class<?>[] interfaces = clazz.getInterfaces();
        System.out.println("Реализованные интерфейсы: " + Arrays.toString(Arrays.stream(interfaces).map(Class::getName).toArray()));

        System.out.println("\n--- Поля ---");
        // getFields() возвращает только public поля (включая унаследованные)
        Field[] publicFields = clazz.getFields();
        System.out.println("Public поля: " + Arrays.toString(publicFields));

        // getDeclaredFields() возвращает все поля (public, protected, default, private) только этого класса (без унаследованных)
        Field[] allFields = clazz.getDeclaredFields();
        for (Field field : allFields) {
            System.out.printf("  Поле: %s, Тип: %s, Модификаторы: %s\n",
                    field.getName(), field.getType().getSimpleName(), Modifier.toString(field.getModifiers()));
        }

        System.out.println("\n--- Конструкторы ---");
        // getDeclaredConstructors() возвращает все конструкторы этого класса
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.printf("  Конструктор: %s, Параметры: %s\n",
                    constructor.getName(), Arrays.toString(constructor.getParameterTypes()));
        }

        System.out.println("\n--- Методы ---");
        // getDeclaredMethods() возвращает все методы этого класса (без унаследованных)
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            System.out.printf("  Метод: %s, Возвращаемый тип: %s, Параметры: %s\n",
                    method.getName(), method.getReturnType().getSimpleName(), Arrays.toString(method.getParameterTypes()));
        }
    }

    /**
     * 3. Создание экземпляра класса
     *
     * Reflection позволяет создавать объекты динамически.
     */
    public static void createInstance() throws Exception {
        Class<Person> personClass = Person.class;

        // Способ 1: Использование конструктора по умолчанию (устарел с Java 9, используйте getDeclaredConstructor().newInstance())
        // Person person1 = personClass.newInstance(); // Deprecated

        // Способ 2: Получение конкретного конструктора и создание экземпляра
        // Получаем конструктор с параметрами (String, int)
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class, int.class);
        Person person2 = constructor.newInstance("Alice", 25);
        System.out.println("Создан объект через конструктор с параметрами: " + person2);

        // Создание через конструктор по умолчанию
        Constructor<Person> defaultConstructor = personClass.getDeclaredConstructor();
        Person person3 = defaultConstructor.newInstance();
        System.out.println("Создан объект через конструктор по умолчанию: " + person3);
    }

    /**
     * 4. Доступ и изменение полей
     *
     * Можно читать и изменять значения полей объекта, даже приватных.
     */
    public static void accessAndModifyFields() throws Exception {
        Person person = new Person("Bob", 40);
        Class<?> personClass = person.getClass();

        // Доступ к public полю
        Field ageField = personClass.getField("age"); // getField для public полей
        int age = (int) ageField.get(person);
        System.out.printf("Начальный возраст (public поле 'age'): %d\n", age);

        ageField.set(person, 41);
        System.out.printf("Измененный возраст (public поле 'age'): %d\n", person.age);

        // Доступ к private полю
        Field nameField = personClass.getDeclaredField("name"); // getDeclaredField для любого поля
        // Для доступа к private полю нужно разрешение
        nameField.setAccessible(true);

        String name = (String) nameField.get(person);
        System.out.printf("Начальное имя (private поле 'name'): %s\n", name);

        nameField.set(person, "Robert");
        System.out.printf("Измененное имя (private поле 'name'): %s\n", person.getName());

        // Важно: после работы с приватным полем рекомендуется вернуть флаг доступа в исходное состояние
        nameField.setAccessible(false);
    }

    /**
     * 5. Вызов методов
     *
     * Reflection позволяет вызывать любой метод объекта.
     */
    public static void invokeMethods() throws Exception {
        Person person = new Person("Charlie", 35);
        Class<?> personClass = person.getClass();

        // Вызов public метода без параметров
        Method sayHelloMethod = personClass.getMethod("sayHello");
        sayHelloMethod.invoke(person);

        // Вызов public метода с параметрами
        Method setAgeMethod = personClass.getMethod("setAge", int.class);
        setAgeMethod.invoke(person, 36);
        System.out.println("Возраст после вызова setAge: " + person.age);

        // Вызов private метода с параметрами
        Method privateInfoMethod = personClass.getDeclaredMethod("getPrivateInfo", String.class);
        privateInfoMethod.setAccessible(true); // Даем доступ к private методу
        String result = (String) privateInfoMethod.invoke(person, "Секретный ключ");
        System.out.println("Результат вызова private метода: " + result);
        privateInfoMethod.setAccessible(false); // Возвращаем доступ
    }

    /**
     * 6. Работа с приватными членами
     *
     * Как показано выше, setAccessible(true) позволяет обойти проверку доступа
     * для приватных полей, методов и конструкторов. Это мощная, но опасная
     * возможность, нарушающая инкапсуляцию.
     */
    public static void workWithPrivateMembers() throws Exception {
        System.out.println("Демонстрация работы с приватным конструктором.");
        Class<SecretClass> secretClass = SecretClass.class;

        // Получаем приватный конструктор
        Constructor<SecretClass> privateConstructor = secretClass.getDeclaredConstructor(String.class);
        privateConstructor.setAccessible(true); // Делаем его доступным

        // Создаем экземпляр через приватный конструктор
        SecretClass instance = privateConstructor.newInstance("Секретное сообщение");
        instance.showSecret();

        privateConstructor.setAccessible(false);
    }

    /**
     * 7. Работа с массивами
     *
     * Класс java.lang.reflect.Array предоставляет статические методы для
     * динамического создания и доступа к элементам массива.
     */
    public static void workWithArrays() {
        // Создание массива строк размером 10
        Object stringArray = Array.newInstance(String.class, 10);

        // Установка значения элемента
        Array.set(stringArray, 0, "Первый");
        Array.set(stringArray, 1, "Второй");

        // Получение значения элемента
        String firstElement = (String) Array.get(stringArray, 0);
        System.out.println("Первый элемент массива: " + firstElement);

        // Получение типа компонента массива
        Class<?> componentType = stringArray.getClass().getComponentType();
        System.out.println("Тип элементов массива: " + componentType.getName());

        // Получение длины массива
        int length = Array.getLength(stringArray);
        System.out.println("Длина массива: " + length);
    }
}

/**
 * Вспомогательный класс для демонстрации.
 */
class Person implements Comparable<Person> {
    private String name;
    public int age;
    private static final String DEFAULT_NAME = "Unnamed";

    public Person() {
        this.name = DEFAULT_NAME;
        this.age = 0;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void sayHello() {
        System.out.println("Привет, меня зовут " + name);
    }

    private String getPrivateInfo(String secretKey) {
        return "Это приватная информация для " + name + ", полученная с ключом: " + secretKey;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + "'" +
                ", age=" + age +
                '}';
    }

    @Override
    public int compareTo(Person o) {
        return this.name.compareTo(o.name);
    }
}

/**
 * Вспомогательный класс с приватным конструктором.
 */
class SecretClass {
    private String secret;

    private SecretClass(String secret) {
        this.secret = secret;
    }

    public void showSecret() {
        System.out.println("Мой секрет: " + secret);
    }
}

/*
 * --- Преимущества и недостатки Reflection API ---
 *
 * Преимущества:
 * 1. Расширяемость: Позволяет создавать гибкие приложения, которые могут работать с кодом,
 *    неизвестным на этапе компиляции (например, плагины, фреймворки).
 * 2. Инструменты для отладки и тестирования: Можно инспектировать приватные поля и методы,
 *    что полезно для написания тестов или отладчиков.
 * 3. Автоматизация: Используется в ORM (Hibernate), DI-контейнерах (Spring) для
 *    автоматического маппинга, внедрения зависимостей и т.д.
 *
 * Недостатки:
 * 1. Производительность: Операции через Reflection значительно медленнее, чем прямой вызов
 *    методов или доступ к полям. Вызов метода через рефлексию может быть в десятки и сотни
 *    раз медленнее.
 * 2. Нарушение безопасности и инкапсуляции: `setAccessible(true)` позволяет обойти
 *    модификаторы доступа, что может привести к непредсказуемому состоянию объектов и
 *    нарушению принципов ООП.
 * 3. Ошибки времени выполнения: Ошибки, которые компилятор обнаружил бы при прямом доступе
 *    (например, опечатка в имени метода, неверные типы параметров), при использовании
 *    Reflection проявятся только во время выполнения программы в виде исключений.
 * 4. Усложнение кода: Код, использующий Reflection, становится менее читаемым и сложным
 *    для понимания и поддержки.
 *
 * Заключение:
 * Reflection API — это мощный, но специфический инструмент. Его следует использовать
 * с осторожностью и только тогда, когда другие, более простые и безопасные подходы
 * не решают поставленную задачу. В обычном прикладном коде его применение, как правило,
 * не оправдано. Основная сфера его применения — это разработка фреймворков и библиотек.
 */