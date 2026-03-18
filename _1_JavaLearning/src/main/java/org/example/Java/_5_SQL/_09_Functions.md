# 9. Функции (Functions)

## Скалярные функции (Scalar Functions)
Действуют на каждое значение отдельно.

### Строковые (String)
*   `UPPER(s) / LOWER(s)`: Регистр.
*   `LENGTH(s)` / `LEN(s)`: Длина строки.
*   `SUBSTRING(s, start, len)`: Извлечение подстроки.
*   `TRIM(s)`: Удаление пробелов по краям.
*   `REPLACE(s, from, to)`: Замена.
*   `CONCAT(s1, s2)` или `s1 || s2`: Склеивание.

### Математические (Math)
*   `ABS(n)`: Модуль.
*   `ROUND(n, d)`: Округление до `d` знаков.
*   `CEIL(n) / FLOOR(n)`: Округление вверх/вниз.
*   `POWER(n, p)`: Степень.
*   `MOD(n, m)`: Остаток от деления.

### Дата и Время (Date)
*   `NOW()` / `CURRENT_TIMESTAMP`: Текущая дата и время.
*   `EXTRACT(field FROM source)`: Извлечение части (года, месяца).
    ```sql
    SELECT EXTRACT(YEAR FROM OrderDate) FROM Orders;
    ```
*   `DATE_ADD` / `INTERVAL`: Прибавление времени.
    ```sql
    SELECT OrderDate + INTERVAL '1 day' FROM Orders; -- Postgres
    ```

### Преобразование типов (Conversion)
*   `CAST(val AS type)`: Стандарт SQL.
    ```sql
    SELECT CAST('123' AS INT);
    ```
*   `TO_CHAR`, `TO_DATE`, `TO_NUMBER` (Oracle/Postgres).

## Оконные функции (Window Functions)
Мощнейший инструмент аналитики. Позволяют выполнять вычисления по набору строк, связанных с текущей строкой, **не схлопывая** их в одну (как `GROUP BY`).

**Синтаксис**:
```sql
FUNCTION_NAME() OVER (
    PARTITION BY col1       -- Группировка для окна (как GROUP BY)
    ORDER BY col2           -- Сортировка внутри окна
    ROWS/RANGE ...          -- Рамки окна (Frame Clause)
)
```

### Функции ранжирования (Ranking)
*   `ROW_NUMBER()`: Уникальный номер строки в окне (1, 2, 3, 4).
*   `RANK()`: Ранг с пропусками (1, 2, 2, 4).
*   `DENSE_RANK()`: Ранг без пропусков (1, 2, 2, 3).
*   `NTILE(n)`: Делит окно на `n` групп.

```sql
SELECT Name, Salary,
       RANK() OVER (ORDER BY Salary DESC) as Rank
FROM Employees;
```

### Функции смещения (Value / Offset)
*   `LAG(col, n)`: Значение из предыдущей строки (со смещением `n`).
*   `LEAD(col, n)`: Значение из следующей строки.
*   `FIRST_VALUE(col)`: Первое значение в окне.
*   `LAST_VALUE(col)`: Последнее значение в окне.

```sql
SELECT Month, Sales,
       LAG(Sales) OVER (ORDER BY Month) as PrevMonthSales
FROM MonthlySales;
```

### Агрегатные оконные функции
Обычные агрегаты (`SUM`, `AVG`, `COUNT`), используемые с `OVER`. Позволяют считать "нарастающий итог" (Running Total).

```sql
SELECT Date, Amount,
       SUM(Amount) OVER (ORDER BY Date) as RunningTotal
FROM Transactions;
```
