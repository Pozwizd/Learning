# 7. Фильтрация и Операторы (Filtering & Operators)

`WHERE` — это фильтр, который пропускает только те строки, для которых условие истинно (`TRUE`).

## Операторы сравнения (Comparison Operators)
| Оператор | Описание | Пример |
| :--- | :--- | :--- |
| `=` | Равно | `Age = 25` |
| `<>` или `!=` | Не равно | `Status <> 'Active'` |
| `>` | Больше | `Salary > 5000` |
| `<` | Меньше | `Price < 100` |
| `>=` | Больше или равно | `Rating >= 4.5` |
| `<=` | Меньше или равно | `Stock <= 10` |

## Логические операторы (Logical Operators)
| Оператор | Описание | Пример |
| :--- | :--- | :--- |
| `AND` | И (оба условия верны) | `Age > 18 AND City = 'NY'` |
| `OR` | ИЛИ (хотя бы одно условие верно) | `Role = 'Admin' OR Role = 'Manager'` |
| `NOT` | НЕ (инверсия) | `NOT (Status = 'Closed')` |

## Диапазоны (Range)
`BETWEEN` включает границы диапазона.
```sql
SELECT * FROM Orders WHERE OrderDate BETWEEN '2023-01-01' AND '2023-12-31';
-- Эквивалентно: OrderDate >= '2023-01-01' AND OrderDate <= '2023-12-31'
```

## Множества (Set)
`IN` проверяет вхождение значения в список.
```sql
SELECT * FROM Users WHERE Country IN ('USA', 'Canada', 'UK');
-- Эквивалентно: Country = 'USA' OR Country = 'Canada' OR Country = 'UK'
```

## Поиск по шаблону (Pattern Matching)
### LIKE
*   `%`: Любое количество символов (включая 0).
*   `_`: Ровно один любой символ.

```sql
SELECT * FROM Users WHERE Email LIKE '%@gmail.com'; -- Заканчивается на @gmail.com
SELECT * FROM Users WHERE Name LIKE 'A%'; -- Начинается на A
SELECT * FROM Products WHERE SKU LIKE 'AB_12'; -- AB, любой символ, 12
```

### SIMILAR TO / REGEX (PostgreSQL)
Более мощный поиск с регулярными выражениями.
```sql
SELECT * FROM Users WHERE Name ~* '^A.*'; -- Начинается на A (case-insensitive)
```

## Работа с NULL (NULL Handling)
`NULL` — это отсутствие значения. `NULL` не равен ничему, даже самому себе (`NULL = NULL` вернет `NULL` (unknown), а не `TRUE`).

### Проверка на NULL
```sql
SELECT * FROM Users WHERE Phone IS NULL;
SELECT * FROM Users WHERE Phone IS NOT NULL;
```
**Никогда** не пишите `WHERE Phone = NULL`.

### Функции для работы с NULL
*   `COALESCE(val1, val2, ...)`: Возвращает первый не-NULL аргумент. Полезно для значений по умолчанию.
    ```sql
    SELECT Name, COALESCE(Phone, 'No Phone') FROM Users;
    ```
*   `NULLIF(val1, val2)`: Возвращает `NULL`, если `val1` равно `val2`. Иначе возвращает `val1`. Полезно для избежания деления на ноль.
    ```sql
    SELECT Price / NULLIF(Quantity, 0) FROM Products;
    ```
