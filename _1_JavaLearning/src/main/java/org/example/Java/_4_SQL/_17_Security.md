# 17. Безопасность (DCL - Data Control Language)

DCL управляет доступом к данным.

## Пользователи и Роли
*   **User**: Учетная запись для входа.
*   **Role**: Группа прав, которую можно назначить пользователю.

```sql
CREATE USER 'john' IDENTIFIED BY 'password123';
CREATE ROLE 'manager';
```

## GRANT (Выдача прав)
```sql
-- Дать право на чтение таблицы
GRANT SELECT ON Employees TO 'john';

-- Дать все права на базу
GRANT ALL PRIVILEGES ON DATABASE MyCompany TO 'manager';

-- Назначить роль пользователю
GRANT 'manager' TO 'john';
```

## REVOKE (Отзыв прав)
```sql
REVOKE SELECT ON Employees FROM 'john';
```

## SQL Injection
Самая опасная уязвимость. Возникает, когда пользовательский ввод конкатенируется с SQL-запросом.

**Пример уязвимости**:
```java
String query = "SELECT * FROM Users WHERE Name = '" + userName + "'";
```
Если `userName` будет `' OR '1'='1`, запрос превратится в:
```sql
SELECT * FROM Users WHERE Name = '' OR '1'='1'; -- Вернет всех пользователей!
```

**Защита**:
Всегда используйте **Prepared Statements** (параметризованные запросы).
```java
String query = "SELECT * FROM Users WHERE Name = ?";
stmt.setString(1, userName);
```
В этом случае ввод трактуется как просто строка, а не часть команды.
