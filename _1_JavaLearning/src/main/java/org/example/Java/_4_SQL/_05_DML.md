# 5. DML (Data Manipulation Language)

DML используется для управления данными внутри таблиц.

## INSERT
Вставка новых строк.

### Полная вставка
```sql
INSERT INTO Employees (ID, Name, Salary) 
VALUES (1, 'John Doe', 50000);
```

### Множественная вставка
```sql
INSERT INTO Employees (ID, Name, Salary) 
VALUES 
(2, 'Jane Smith', 60000),
(3, 'Bob Brown', 55000);
```

### Вставка результата SELECT
Копирование данных из одной таблицы в другую.
```sql
INSERT INTO ArchiveEmployees (ID, Name, Salary)
SELECT ID, Name, Salary FROM Employees WHERE IsActive = FALSE;
```

## UPDATE
Обновление существующих данных. **Всегда проверяйте WHERE!**

```sql
UPDATE Employees 
SET Salary = Salary * 1.10, 
    Position = 'Senior Developer'
WHERE ID = 1;
```
*   Если забыть `WHERE`, обновятся **все** строки в таблице.

### Обновление с использованием другой таблицы (JOIN в UPDATE)
```sql
-- PostgreSQL
UPDATE Employees e
SET DepartmentName = d.Name
FROM Departments d
WHERE e.DepartmentID = d.ID;
```

## DELETE
Удаление строк. **Всегда проверяйте WHERE!**

```sql
DELETE FROM Employees WHERE ID = 1;
```
*   Если забыть `WHERE`, удалятся **все** строки.

### Отличие DELETE от TRUNCATE
*   `DELETE` сканирует таблицу и удаляет строки по одной, записывая каждую операцию в лог транзакций (можно откатить).
*   `TRUNCATE` быстрее, но грубее (DDL).

## MERGE (Upsert)
Вставка или обновление в зависимости от того, существует ли запись.

### PostgreSQL (INSERT ON CONFLICT)
```sql
INSERT INTO Employees (ID, Name, Salary) VALUES (1, 'John', 50000)
ON CONFLICT (ID) 
DO UPDATE SET Name = EXCLUDED.Name, Salary = EXCLUDED.Salary;
```

### MySQL (INSERT ON DUPLICATE KEY UPDATE)
```sql
INSERT INTO Employees (ID, Name, Salary) VALUES (1, 'John', 50000)
ON DUPLICATE KEY UPDATE Name = VALUES(Name), Salary = VALUES(Salary);
```

### Standard SQL MERGE
```sql
MERGE INTO TargetTable AS T
USING SourceTable AS S ON T.ID = S.ID
WHEN MATCHED THEN
    UPDATE SET T.Val = S.Val
WHEN NOT MATCHED THEN
    INSERT (ID, Val) VALUES (S.ID, S.Val);
```
