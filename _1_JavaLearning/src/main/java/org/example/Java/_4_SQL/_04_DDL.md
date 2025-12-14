# 4. DDL (Data Definition Language)

DDL используется для определения и изменения структуры базы данных. Команды DDL обычно автоматически фиксируют транзакцию (auto-commit).

## CREATE
Создание новых объектов.

### Создание базы данных
```sql
CREATE DATABASE MyCompany;
```

### Создание таблицы
```sql
CREATE TABLE Employees (
    ID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Email VARCHAR(100) UNIQUE,
    HireDate DATE DEFAULT CURRENT_DATE
);
```

### Создание индекса
```sql
CREATE INDEX idx_lastname ON Employees(LastName);
```

### Создание представления (View)
```sql
CREATE VIEW ActiveEmployees AS
SELECT * FROM Employees WHERE HireDate IS NOT NULL;
```

## ALTER
Изменение существующей структуры.

### Добавление столбца
```sql
ALTER TABLE Employees ADD COLUMN PhoneNumber VARCHAR(20);
```

### Удаление столбца
```sql
ALTER TABLE Employees DROP COLUMN PhoneNumber;
```

### Изменение типа данных столбца
```sql
-- PostgreSQL
ALTER TABLE Employees ALTER COLUMN FirstName TYPE VARCHAR(100);

-- MySQL
ALTER TABLE Employees MODIFY COLUMN FirstName VARCHAR(100);
```

### Добавление ограничения (Constraint)
```sql
ALTER TABLE Employees ADD CONSTRAINT check_salary CHECK (Salary > 0);
```

## DROP
Удаление объектов. **Осторожно!** Данные удаляются безвозвратно.

```sql
DROP TABLE Employees;
DROP DATABASE MyCompany;
DROP VIEW ActiveEmployees;
```
*   `DROP TABLE IF EXISTS Employees;` — безопасное удаление.

## TRUNCATE
Быстрая очистка таблицы.
```sql
TRUNCATE TABLE Employees;
```
*   **Отличие от DELETE**: `TRUNCATE` — это DDL операция. Она пересоздает таблицу или сбрасывает указатели страниц данных, что намного быстрее, чем удаление каждой строки по отдельности. Обычно не вызывает триггеры `ON DELETE`. Сбрасывает счетчики автоинкремента (IDENTITY).

## RENAME
Переименование.
```sql
ALTER TABLE Employees RENAME TO Staff;
```

## COMMENT
Добавление комментариев к объектам (полезно для документации внутри БД).
```sql
COMMENT ON TABLE Employees IS 'Таблица всех сотрудников компании';
COMMENT ON COLUMN Employees.Email IS 'Корпоративная почта';
```
