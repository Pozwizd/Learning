# Java Learning Repository

Репозиторий переорганизован как **проходимый учебный трек** для начинающих и junior-разработчиков, которые уже знают базовый синтаксис Java и хотят выйти на уверенный уровень по core Java + практике.

## Для кого этот курс

- Новички после первого знакомства с Java (`if/for/methods`)
- Студенты, которым нужен понятный маршрут «теория → практика → проверка»
- Разработчики с других языков, переходящие на Java

## Цель

Дать последовательный путь от базового ООП и коллекций до Stream API, исключений, алгоритмов и первых мини-проектов с проверяемым прогрессом.

## Структура репозитория

- `_1_JavaLearning` — основной учебный модуль (лекции, примеры, задания, чекпоинты)
- `_2_DataAccessObject` — практикум по DAO/JDBC
- `_3_SpringLearn` — вводный Spring Core
- `spring-boot-parent` — Spring Boot (JPA/R2DBC)
- `JavaAdvanced` — дополнительные эксперименты

> Рекомендуется начинать с `_1_JavaLearning/README.md`.

## Как учиться по репозиторию

1. Идти по модулям из `_1_JavaLearning/docs/roadmap.md`
2. По каждой лекции:
   - прочитать `docs/lectures/*.md`
   - запустить пример из `examples/`
   - выполнить задание из `assignments/`
3. Пройти чекпоинт (`assignments/checkpoints/`)
4. Проверить себя тестами (`mvn -q -f _1_JavaLearning/pom.xml test`)

## Быстрый старт

```bash
./_1_JavaLearning/scripts/check-main-compilation.sh
./_1_JavaLearning/scripts/run-example.sh org.example.course.examples.operators.OperatorPrecedenceExample
./_1_JavaLearning/scripts/check-assignments-offline.sh
mvn -q -f _1_JavaLearning/pom.xml test
```

## CI

На каждый push/PR запускается GitHub Actions workflow: сборка и тесты модуля `_1_JavaLearning`.
