# 6. DQL (Data Query Language)

DQL — это сердце SQL. Команда `SELECT` используется для извлечения данных.

## Базовый SELECT
```sql
SELECT * FROM Employees; -- Выбрать все столбцы (не рекомендуется в продакшене)
SELECT Name, Email FROM Employees; -- Выбрать конкретные столбцы
```

## DISTINCT
Удаление дубликатов из результирующего набора.
```sql
SELECT DISTINCT Country FROM Customers;
```

## ALIAS (AS)
Псевдонимы для улучшения читаемости.
```sql
SELECT 
    FirstName AS Name, 
    Salary * 12 AS AnnualSalary 
FROM Employees AS e;
```

## FROM
Указывает источник данных.
```sql
FROM Employees
FROM Employees e JOIN Departments d ON e.DeptID = d.ID
FROM (SELECT * FROM OldEmployees) AS subquery
```

## WHERE
Фильтрация строк **до** группировки.
```sql
SELECT * FROM Products 
WHERE Price > 100 AND Category = 'Electronics';
```

## ORDER BY
Сортировка результатов.
*   `ASC` (по умолчанию): По возрастанию.
*   `DESC`: По убыванию.

```sql
SELECT * FROM Employees 
ORDER BY Salary DESC, Name ASC;
```

### Сортировка NULL значений
*   `NULLS FIRST`: NULL значения идут первыми.
*   `NULLS LAST`: NULL значения идут последними.
```sql
ORDER BY Commission DESC NULLS LAST;
```

## LIMIT / OFFSET / FETCH
Пагинация (выборка части строк).

### PostgreSQL / MySQL
```sql
SELECT * FROM Employees LIMIT 10 OFFSET 5;
-- Пропустить 5 строк, взять следующие 10
```

### SQL Standard (Oracle, SQL Server, Postgre 10+)
```sql
SELECT * FROM Employees
ORDER BY ID
OFFSET 5 ROWS
FETCH NEXT 10 ROWS ONLY;
```
Это более стандартизированный синтаксис.
