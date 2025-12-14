# 12. Подзапросы и CTE (Subqueries & CTE)

## Подзапросы (Subqueries)
Запрос внутри другого запроса. Могут быть в `SELECT`, `FROM`, `WHERE`, `HAVING`.

### Скалярный подзапрос (Scalar)
Возвращает ровно одно значение (одна строка, один столбец).
```sql
SELECT Name, Salary, 
       (SELECT AVG(Salary) FROM Employees) AS AvgSalary
FROM Employees;
```

### Многострочный подзапрос (Multi-row)
Возвращает список значений (один столбец, много строк). Используется с `IN`, `ANY`, `ALL`.
```sql
SELECT * FROM Products 
WHERE CategoryID IN (SELECT ID FROM Categories WHERE Name LIKE 'Elec%');
```

### Коррелированный подзапрос (Correlated Subquery)
Подзапрос, который ссылается на столбец из внешнего запроса. Выполняется **для каждой строки** внешнего запроса (может быть медленным).
```sql
SELECT Name, Salary, DepartmentID
FROM Employees e1
WHERE Salary > (
    SELECT AVG(Salary) 
    FROM Employees e2 
    WHERE e2.DepartmentID = e1.DepartmentID -- Ссылка на внешний e1
);
```

### EXISTS / NOT EXISTS
Проверяет, возвращает ли подзапрос хотя бы одну строку. Обычно быстрее, чем `IN`, так как прекращает выполнение при первом совпадении.
```sql
SELECT * FROM Customers c
WHERE EXISTS (
    SELECT 1 FROM Orders o WHERE o.CustomerID = c.ID
);
```

## CTE (Common Table Expressions)
Временный именованный набор данных, существующий только во время выполнения запроса. Делает код чище и читаемее, чем вложенные подзапросы.

```sql
WITH RegionalSales AS (
    SELECT Region, SUM(Amount) AS TotalSales
    FROM Orders
    GROUP BY Region
),
TopRegions AS (
    SELECT Region
    FROM RegionalSales
    WHERE TotalSales > 1000000
)
SELECT * 
FROM TopRegions;
```

### Рекурсивные CTE (Recursive CTE)
Позволяют обходить иерархические структуры (деревья, графы), например, оргструктуру или категории товаров.

Состоит из двух частей:
1.  **Anchor member**: Базовый запрос (начало рекурсии).
2.  **Recursive member**: Запрос, ссылающийся на само CTE.

```sql
WITH RECURSIVE Subordinates AS (
    -- 1. Anchor: Находим начальника (ID = 1)
    SELECT ID, Name, ManagerID, 1 AS Level
    FROM Employees
    WHERE ID = 1
    
    UNION ALL
    
    -- 2. Recursive: Находим подчиненных тех, кто уже в Subordinates
    SELECT e.ID, e.Name, e.ManagerID, s.Level + 1
    FROM Employees e
    INNER JOIN Subordinates s ON e.ManagerID = s.ID
)
SELECT * FROM Subordinates;
```
