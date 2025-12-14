# 10. Объединение таблиц (Joins)

`JOIN` позволяет комбинировать данные из двух и более таблиц на основе связанного столбца (обычно PK и FK).

## Типы JOIN

### INNER JOIN
Возвращает только те строки, для которых **есть совпадение** в обеих таблицах.
```sql
SELECT Users.Name, Orders.Date
FROM Users
INNER JOIN Orders ON Users.ID = Orders.UserID;
```
*   Если у юзера нет заказов -> юзер не попадет в выборку.
*   Если заказ без юзера (аномалия) -> заказ не попадет.

### LEFT (OUTER) JOIN
Возвращает **все** строки из ЛЕВОЙ таблицы, и совпавшие из ПРАВОЙ. Если совпадения нет, справа будут `NULL`.
```sql
SELECT Users.Name, Orders.Date
FROM Users
LEFT JOIN Orders ON Users.ID = Orders.UserID;
```
*   Если у юзера нет заказов -> юзер будет, `Orders.Date` будет `NULL`.

### RIGHT (OUTER) JOIN
Зеркально LEFT JOIN. Все строки из ПРАВОЙ таблицы.
```sql
SELECT Users.Name, Orders.Date
FROM Users
RIGHT JOIN Orders ON Users.ID = Orders.UserID;
```

### FULL (OUTER) JOIN
Объединение LEFT и RIGHT. Все строки из обеих таблиц. Где нет совпадения — `NULL`.
```sql
SELECT Users.Name, Orders.Date
FROM Users
FULL JOIN Orders ON Users.ID = Orders.UserID;
```

### CROSS JOIN
Декартово произведение. Каждая строка первой таблицы соединяется с каждой строкой второй.
```sql
SELECT * FROM Colors CROSS JOIN Sizes;
```
*   Если в Colors 3 строки, а в Sizes 4 -> результат 12 строк.
*   Опасно на больших таблицах!

### SELF JOIN
Соединение таблицы с самой собой. Полезно для иерархий (Сотрудник - Менеджер).
```sql
SELECT E.Name AS Employee, M.Name AS Manager
FROM Employees E
LEFT JOIN Employees M ON E.ManagerID = M.ID;
```

## Алгоритмы соединений (Join Algorithms)
База данных сама выбирает алгоритм, но полезно их знать для оптимизации.

1.  **Nested Loop Join**: Цикл в цикле. Берет строку из первой таблицы и ищет соответствие во второй. Эффективен, если одна таблица маленькая, а по второй есть индекс.
2.  **Hash Join**: Строит хеш-таблицу по меньшей таблице, затем сканирует большую и проверяет хеш. Очень быстр для больших таблиц без индексов (для равенства `=`).
3.  **Merge Join**: Требует, чтобы обе таблицы были отсортированы по ключу соединения. Очень быстр, если данные уже отсортированы (или есть индекс).
