# Changelog

## 2026-02-13

### Added
- Учебный маршрут: `docs/roadmap.md`, `docs/lectures/*`, `assignments/*`, `projects/word-stats`.
- Автопроверка заданий через JUnit в `src/test/java/org/example/course/assignments/CourseAssignmentsTest.java`.
- Скрипты: `scripts/run-example.sh` (запуск примеров без Maven), `scripts/check-main-compilation.sh` (офлайн-проверка компиляции), `scripts/check-assignments-offline.sh` (офлайн-проверка логики заданий).

### Changed
- Исполняемые примеры перенесены в стандартный Maven layout: `src/main/java/org/example/course/examples/*`.
- Упрощён `pom.xml` модуля: удалён `build-helper`, оставлены минимальные зависимости/плагины.
- Команды в документации унифицированы: запуск модуля через `-f _1_JavaLearning/pom.xml`.

### Compatibility notes
- Структура существующих модулей репозитория не ломалась.
- Изменения в `_1_JavaLearning` в основном аддитивные и совместимы со старым каркасом.
