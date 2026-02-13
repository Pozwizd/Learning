# Лекция 03: Stream API

## Ключевые концепции

- Pipeline: `source -> intermediate -> terminal`
- `filter`, `map`, `sorted`, `collect`
- Избегание побочных эффектов

## Мини-схема

```text
Коллекция -> filter -> map -> sorted -> collect -> результат
```

## Примеры

- `src/main/java/org/example/course/examples/streams/StreamPipelineExample.java`
- `src/main/java/org/example/course/examples/streams/GroupingExample.java`

## Попробуй сам

1. Добавь фильтр по длине строки.
2. Посчитай среднюю длину слов.
3. Переделай один пример в обычный цикл и сравни читаемость.

## Практика

- `assignments/03-streams/task.md`
