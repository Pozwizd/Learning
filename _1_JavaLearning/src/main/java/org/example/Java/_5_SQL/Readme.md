# Структура Знаний SQL (SQL Knowledge Map)

Полная карта знаний по языку SQL, охватывающая синтаксис, функции, проектирование и оптимизацию.

---

## 1. Введение (Introduction)
*   **RDBMS vs NoSQL**: Реляционные (табличные) против нереляционных (документы, графы, ключ-значение).
*   **ACID**: Атомарность, Согласованность, Изоляция, Долговечность (свойства транзакций).
*   **CAP Theorem**: Consistency, Availability, Partition tolerance (для распределенных систем).

## 2. Типы данных (Data Types)
*   **Numeric**: `INT`, `BIGINT`, `DECIMAL(p,s)`, `FLOAT`, `REAL`.
*   **String**: `VARCHAR(n)`, `CHAR(n)`, `TEXT`, `CLOB`.
*   **Date/Time**: `DATE`, `TIME`, `DATETIME`, `TIMESTAMP`, `INTERVAL`.
*   **Boolean**: `BOOLEAN` (TRUE/FALSE).
*   **Binary**: `BLOB`, `VARBINARY`.
*   **Other**: `JSON`, `XML`, `UUID`, `GEOMETRY`.

## 3. Основы синтаксиса и Порядок выполнения (Syntax & Execution Order)
*   **Lexical Structure**: Ключевые слова, идентификаторы, литералы.
*   **Logical Query Processing Order**:
    1.  `FROM` (включая `JOIN`s)
    2.  `WHERE`
    3.  `GROUP BY`
    4.  `HAVING`
    5.  `SELECT` (включая `WINDOW functions`)
    6.  `DISTINCT`
    7.  `ORDER BY`
    8.  `LIMIT` / `OFFSET`

## 4. DDL (Data Definition Language)
Определение структуры данных.
*   `CREATE`: Создание объектов (DATABASE, TABLE, INDEX, VIEW).
*   `ALTER`: Изменение структуры (ADD/DROP COLUMN, MODIFY TYPE).
*   `DROP`: Удаление объектов.
*   `TRUNCATE`: Быстрая очистка таблицы (сброс данных, но не структуры).
*   `RENAME`: Переименование объектов.
*   `COMMENT`: Добавление комментариев к объектам.

## 5. DML (Data Manipulation Language)
Манипуляция данными.
*   `INSERT`: Вставка строк (`VALUES`, `SELECT`).
*   `UPDATE`: Обновление значений.
*   `DELETE`: Удаление строк.
*   `MERGE` (Upsert): Вставка или обновление в зависимости от наличия.

## 6. DQL (Data Query Language)
Выборка данных.
*   `SELECT`: Основная команда выборки.
*   `DISTINCT`: Удаление дубликатов.
*   `ALIAS` (`AS`): Псевдонимы для столбцов и таблиц.
*   `FROM`: Источники данных (таблицы, представления, подзапросы).
*   `WHERE`: Фильтрация строк.
*   `ORDER BY`: Сортировка (`ASC`, `DESC`, `NULLS FIRST/LAST`).
*   `LIMIT` / `OFFSET` / `FETCH NEXT`: Пагинация.

## 7. Фильтрация и Операторы (Filtering & Operators)
*   **Comparison**: `=`, `<>`, `!=`, `<`, `>`, `<=`, `>=`.
*   **Logical**: `AND`, `OR`, `NOT`.
*   **Range**: `BETWEEN ... AND ...`.
*   **Set**: `IN (...)`.
*   **Pattern Matching**:
    *   `LIKE` (`%`, `_`).
    *   `SIMILAR TO` / `REGEX`.
*   **NULL Handling**:
    *   `IS NULL`, `IS NOT NULL`.
    *   `COALESCE(val1, val2)`: Возвращает первое не NULL значение.
    *   `NULLIF(val1, val2)`: Возвращает NULL, если значения равны.

## 8. Агрегация и Группировка (Aggregation & Grouping)
*   **Aggregate Functions**:
    *   `COUNT(*)`, `COUNT(column)`, `COUNT(DISTINCT column)`.
    *   `SUM()`, `AVG()`, `MIN()`, `MAX()`.
    *   `STRING_AGG()` / `GROUP_CONCAT()`: Конкатенация строк.
*   **Grouping**:
    *   `GROUP BY`: Группировка по столбцам.
    *   `HAVING`: Фильтрация групп (после агрегации).
*   **Advanced Grouping**:
    *   `GROUPING SETS`: Несколько группировок в одном запросе.
    *   `ROLLUP`: Иерархические итоги.
    *   `CUBE`: Все возможные комбинации группировок.

## 9. Функции (Functions)
*   **Scalar**:
    *   String: `UPPER`, `LOWER`, `SUBSTRING`, `TRIM`, `REPLACE`, `CONCAT`.
    *   Math: `ABS`, `ROUND`, `CEIL`, `FLOOR`, `POWER`, `MOD`.
    *   Date: `NOW`, `CURRENT_DATE`, `DATE_ADD`, `DATEDIFF`, `EXTRACT`.
    *   Conversion: `CAST`, `CONVERT`, `TO_CHAR`, `TO_DATE`.
*   **Window Functions (Оконные функции)**:
    *   Синтаксис: `FUNC() OVER (PARTITION BY ... ORDER BY ... ROWS/RANGE ...)`
    *   **Ranking**: `ROW_NUMBER()`, `RANK()`, `DENSE_RANK()`, `NTILE()`.
    *   **Value**: `LAG()`, `LEAD()`, `FIRST_VALUE()`, `LAST_VALUE()`.
    *   **Aggregate**: `SUM() OVER (...)`, `AVG() OVER (...)`.

## 10. Объединение таблиц (Joins)
*   `INNER JOIN`: Пересечение.
*   `LEFT (OUTER) JOIN`: Все из левой + совпадения из правой.
*   `RIGHT (OUTER) JOIN`: Все из правой + совпадения из левой.
*   `FULL (OUTER) JOIN`: Объединение обоих (с NULL там, где нет совпадений).
*   `CROSS JOIN`: Декартово произведение (каждый с каждым).
*   `SELF JOIN`: Соединение таблицы с самой собой.
*   `NATURAL JOIN`: Автоматическое соединение по одноименным столбцам (редко используется).

## 11. Операции над множествами (Set Operations)
*   `UNION`: Объединение уникальных строк.
*   `UNION ALL`: Объединение всех строк (включая дубликаты).
*   `INTERSECT`: Пересечение (только общие строки).
*   `EXCEPT` / `MINUS`: Разность (строки из первого, которых нет во втором).

## 12. Подзапросы и CTE (Subqueries & CTE)
*   **Subqueries**:
    *   Scalar: Возвращают одно значение.
    *   Multi-row: Возвращают список (`IN`, `ANY`, `ALL`).
    *   Table-valued: Возвращают таблицу (в `FROM`).
    *   **Correlated**: Зависят от внешней таблицы (выполняются для каждой строки).
    *   `EXISTS` / `NOT EXISTS`: Проверка наличия строк.
*   **CTE (Common Table Expressions)**:
    *   `WITH cte_name AS (...) SELECT ...`
    *   Читаемость и переиспользование кода.
    *   **Recursive CTE**: Для иерархических данных (деревья, графы).

## 13. Ограничения и Целостность (Constraints & Integrity)
*   `PRIMARY KEY`: Уникальный идентификатор (NOT NULL + UNIQUE).
*   `FOREIGN KEY`: Ссылка на другую таблицу (Referential Integrity).
    *   `ON DELETE/UPDATE`: `CASCADE`, `SET NULL`, `RESTRICT`, `NO ACTION`.
*   `UNIQUE`: Уникальность значений.
*   `NOT NULL`: Запрет NULL.
*   `CHECK`: Проверка условия (например, `age > 0`).
*   `DEFAULT`: Значение по умолчанию.

## 14. Транзакции и Конкурентный доступ (Transactions & Concurrency)
*   **Control**:
    *   `BEGIN TRANSACTION` / `START TRANSACTION`.
    *   `COMMIT`: Фиксация изменений.
    *   `ROLLBACK`: Откат изменений.
    *   `SAVEPOINT`: Промежуточная точка сохранения.
*   **Isolation Levels** (уровни изоляции):
    1.  `READ UNCOMMITTED`: "Грязное" чтение.
    2.  `READ COMMITTED`: Защита от грязного чтения.
    3.  `REPEATABLE READ`: Защита от неповторяющегося чтения (фантомы возможны).
    4.  `SERIALIZABLE`: Полная изоляция (последовательное выполнение).
*   **Locks**: Shared (S), Exclusive (X), Deadlocks (взаимные блокировки).

## 15. Объекты базы данных (Database Objects)
*   **Views**: Виртуальные таблицы (хранимые запросы).
    *   Materialized Views: Физически сохраненные результаты запроса (кэш).
*   **Stored Procedures**: Хранимая логика на сервере (PL/SQL, T-SQL).
*   **Functions (UDF)**: Пользовательские функции (Scalar, Table-Valued).
*   **Triggers**: Автоматическое выполнение при событиях (`BEFORE/AFTER INSERT/UPDATE/DELETE`).
*   **Sequences**: Генераторы чисел.
*   **Indexes**: Структуры для ускорения поиска.

## 16. Индексы и Оптимизация (Indexes & Optimization)
*   **Types**:
    *   B-Tree (стандартный).
    *   Hash (точное совпадение).
    *   Bitmap (мало уникальных значений).
    *   GIN / GiST (для полнотекстового поиска, геометрии).
*   **Structure**:
    *   Clustered (данные хранятся в листьях индекса).
    *   Non-Clustered (ссылки на данные).
*   **Optimization**:
    *   `EXPLAIN` / `EXPLAIN ANALYZE`: План выполнения запроса.
    *   Table Scan vs Index Scan vs Index Seek.
    *   Statistics (статистика распределения данных).
    *   SARGable queries (использование индексов в WHERE).

## 17. Безопасность (DCL - Data Control Language)
*   `GRANT`: Выдача прав (`SELECT`, `INSERT`, `EXECUTE`...).
*   `REVOKE`: Отзыв прав.
*   `ROLES`: Группировка прав.

## 18. Проектирование БД (Database Design)
*   **Normalization**:
    *   1NF: Атомарность, PK.
    *   2NF: 1NF + нет частичных зависимостей от PK.
    *   3NF: 2NF + нет транзитивных зависимостей.
    *   BCNF, 4NF, 5NF.
*   **Denormalization**: Намеренное нарушение нормализации для производительности (OLAP).
*   **ER Modeling**: Сущности, Атрибуты, Связи (1:1, 1:M, M:N).
