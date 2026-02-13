# _1_JavaLearning — Core Java Course

## Пререквизиты

- Java syntax basics (`variables`, `if`, loops, methods)
- Установлены JDK 21+ и Maven 3.9+

## Порядок прохождения

1. [Roadmap](docs/roadmap.md)
2. Лекции: `docs/lectures/`
3. Примеры: `examples/`
4. Задания: `assignments/`
5. Мини-проект: `projects/word-stats`

## Единый способ запуска

### Запуск тестов

```bash
mvn -q -f _1_JavaLearning/pom.xml test
```

### Проверка компиляции main-кода (офлайн)

```bash
./_1_JavaLearning/scripts/check-main-compilation.sh
```

### Проверка assignment-логики без Maven/JUnit

```bash
./_1_JavaLearning/scripts/check-assignments-offline.sh
```

### Запуск примеров

```bash
./_1_JavaLearning/scripts/run-example.sh org.example.course.examples.operators.OperatorPrecedenceExample
./_1_JavaLearning/scripts/run-example.sh org.example.course.examples.collections.ListVsSetExample
./_1_JavaLearning/scripts/run-example.sh org.example.course.examples.streams.StreamPipelineExample
./_1_JavaLearning/scripts/run-example.sh org.example.course.examples.exceptions.SafeDivisionExample
```

## Как сдавать задания

- Выполнить требования в `assignments/*/task.md`
- Реализовать методы в `src/main/java/org/example/course/assignments/*`
- Убедиться, что `mvn -q -f _1_JavaLearning/pom.xml test` проходит

## Обновлённый roadmap обучения

См. [docs/roadmap.md](docs/roadmap.md).
