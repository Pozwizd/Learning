# 8. Агрегация и Группировка (Aggregation & Grouping)

Агрегатные функции вычисляют одно значение на основе набора значений (столбца).

## Агрегатные функции
*   `COUNT(*)`: Считает количество строк (включая NULL).
*   `COUNT(column)`: Считает количество **не-NULL** значений в столбце.
*   `SUM(column)`: Сумма значений.
*   `AVG(column)`: Среднее значение.
*   `MIN(column)`: Минимальное значение.
*   `MAX(column)`: Максимальное значение.
*   `STRING_AGG(col, delim)` (Postgres) / `GROUP_CONCAT(col)` (MySQL): Склеивает строки.

```sql
SELECT 
    COUNT(*) AS TotalOrders,
    SUM(TotalAmount) AS Revenue,
    AVG(TotalAmount) AS AverageCheck
FROM Orders;
```

## GROUP BY
Группирует строки, имеющие одинаковые значения в указанных столбцах, в "сводные" строки.
**Правило**: Все столбцы в `SELECT`, которые не обернуты в агрегатные функции, **обязаны** быть в `GROUP BY`.

```sql
SELECT 
    Category, 
    COUNT(*) AS ProductCount, 
    AVG(Price) AS AvgPrice
FROM Products
GROUP BY Category;
```

## HAVING
Фильтрует **группы** (после выполнения `GROUP BY`). `WHERE` фильтрует строки **до** группировки.

```sql
SELECT Category, COUNT(*) AS Cnt
FROM Products
WHERE Price > 100       -- 1. Фильтр строк (дорогие товары)
GROUP BY Category       -- 2. Группировка
HAVING COUNT(*) > 5;    -- 3. Фильтр групп (только категории, где таких товаров > 5)
```

## Продвинутая группировка (Advanced Grouping)
Доступно в PostgreSQL, Oracle, SQL Server.

### GROUPING SETS
Позволяет указать несколько наборов группировки в одном запросе.
```sql
SELECT Brand, Category, SUM(Sales)
FROM Items
GROUP BY GROUPING SETS ((Brand, Category), (Brand), ());
-- Вернет:
-- 1. Сумму по Brand и Category
-- 2. Сумму по Brand (все категории)
-- 3. Общую сумму (Grand Total)
```

### ROLLUP
Создает иерархические итоги (суб-тоталы).
```sql
GROUP BY ROLLUP (Year, Month, Day)
-- Итоги:
-- Year, Month, Day
-- Year, Month
-- Year
-- () - Grand Total
```

### CUBE
Создает итоги для всех возможных комбинаций.
```sql
GROUP BY CUBE (Brand, Color)
-- Итоги:
-- Brand, Color
-- Brand
-- Color
-- ()
```
