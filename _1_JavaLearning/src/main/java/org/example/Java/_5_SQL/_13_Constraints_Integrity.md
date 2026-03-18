# 13. Ограничения и Целостность (Constraints & Integrity)

Ограничения (Constraints) — это правила, применяемые к столбцам таблицы для обеспечения точности и надежности данных.

## Типы ограничений

### PRIMARY KEY (Первичный ключ)
Уникально идентифицирует каждую строку в таблице.
*   Должен быть уникальным (`UNIQUE`).
*   Не может быть `NULL`.
*   В таблице может быть только один PK (но он может состоять из нескольких столбцов — составной ключ).

```sql
CREATE TABLE Users (
    ID INT PRIMARY KEY,
    Name VARCHAR(100)
);
```

### FOREIGN KEY (Внешний ключ)
Обеспечивает ссылочную целостность (Referential Integrity). Связывает столбец одной таблицы с PK другой таблицы.

```sql
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    UserID INT,
    FOREIGN KEY (UserID) REFERENCES Users(ID)
);
```

#### Действия при удалении/обновлении (Referential Actions)
Что делать с заказами, если удаляется пользователь?
*   `ON DELETE CASCADE`: Удалить заказы вместе с пользователем.
*   `ON DELETE SET NULL`: Установить `UserID = NULL` в заказах.
*   `ON DELETE RESTRICT` / `NO ACTION`: Запретить удаление пользователя, пока у него есть заказы (по умолчанию).

### UNIQUE
Гарантирует, что все значения в столбце разные.
*   В отличие от PK, допускает один `NULL` (в некоторых СУБД — много `NULL`).
*   Можно создать несколько UNIQUE ограничений на таблицу.

```sql
CREATE TABLE Users (
    Email VARCHAR(100) UNIQUE
);
```

### NOT NULL
Гарантирует, что столбец не может содержать `NULL`.

```sql
CREATE TABLE Users (
    Name VARCHAR(100) NOT NULL
);
```

### CHECK
Проверяет, что значение удовлетворяет определенному условию.

```sql
CREATE TABLE Products (
    Price DECIMAL(10, 2) CHECK (Price > 0),
    Status VARCHAR(10) CHECK (Status IN ('Active', 'Inactive'))
);
```

### DEFAULT
Устанавливает значение по умолчанию, если оно не передано при вставке.

```sql
CREATE TABLE Orders (
    OrderDate DATE DEFAULT CURRENT_DATE
);
```
