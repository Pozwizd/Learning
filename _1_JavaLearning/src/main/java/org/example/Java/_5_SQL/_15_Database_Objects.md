# 15. Объекты базы данных (Database Objects)

## Представления (Views)
Виртуальная таблица, основанная на результате SQL-запроса. Данные не хранятся физически (кроме Materialized Views).

**Зачем нужны?**
*   Упрощение сложных запросов.
*   Безопасность (скрыть определенные столбцы).

```sql
CREATE VIEW HighValueOrders AS
SELECT * FROM Orders WHERE Amount > 1000;

SELECT * FROM HighValueOrders;
```

## Хранимые процедуры (Stored Procedures)
Набор SQL-команд, сохраненный в БД. Могут иметь входные/выходные параметры, логику (IF, LOOP), транзакции.

```sql
-- Пример (псевдокод)
CREATE PROCEDURE GiveBonus(IN emp_id INT, IN amount DECIMAL)
BEGIN
    UPDATE Employees SET Salary = Salary + amount WHERE ID = emp_id;
END;

CALL GiveBonus(1, 500);
```

## Пользовательские функции (User-Defined Functions - UDF)
*   **Scalar**: Возвращают одно значение. Можно использовать в `SELECT`.
*   **Table-Valued**: Возвращают таблицу. Можно использовать в `FROM`.

**Отличие от процедур**: Функции обычно должны возвращать значение и не могут управлять транзакциями.

## Триггеры (Triggers)
Процедуры, которые автоматически выполняются при наступлении события (`INSERT`, `UPDATE`, `DELETE`).

*   `BEFORE`: До изменения данных (для валидации или изменения вставляемых данных).
*   `AFTER`: После изменения (для логирования, аудита).
*   `INSTEAD OF`: Вместо операции (обычно для Views).

```sql
CREATE TRIGGER UpdateLastModified
BEFORE UPDATE ON Employees
FOR EACH ROW
EXECUTE FUNCTION set_updated_at();
```

## Последовательности (Sequences)
Генераторы уникальных чисел (обычно для ID).
```sql
CREATE SEQUENCE user_id_seq START 1;
SELECT nextval('user_id_seq');
```
