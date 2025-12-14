# 11. Операции над множествами (Set Operations)

Позволяют комбинировать результаты **нескольких запросов SELECT**.
**Важно**: Количество столбцов и их типы данных в запросах должны совпадать.

## UNION
Объединяет результаты двух запросов, **удаляя дубликаты**.
```sql
SELECT City FROM Customers
UNION
SELECT City FROM Suppliers;
```
*   Если 'London' есть и там, и там -> в результате будет один 'London'.

## UNION ALL
Объединяет результаты, **сохраняя дубликаты**.
```sql
SELECT City FROM Customers
UNION ALL
SELECT City FROM Suppliers;
```
*   Работает быстрее, чем `UNION`, так как не тратит ресурсы на поиск и удаление дублей. Используйте `UNION ALL`, если уверены, что дублей нет или они нужны.

## INTERSECT
Возвращает только те строки, которые есть **в обоих** результатах (пересечение).
```sql
SELECT ProductID FROM Orders2022
INTERSECT
SELECT ProductID FROM Orders2023;
```
*   Вернет продукты, которые заказывали и в 2022, и в 2023.

## EXCEPT (PostgreSQL, SQL Server) / MINUS (Oracle)
Возвращает строки из **первого** запроса, которых **нет во втором** (разность).
```sql
SELECT ProductID FROM Products
EXCEPT
SELECT ProductID FROM Orders;
```
*   Вернет продукты, которые ни разу не заказывали.

## Порядок выполнения
Можно комбинировать несколько операций. Приоритет `INTERSECT` обычно выше, чем `UNION` / `EXCEPT`. Используйте скобки для явного порядка.

```sql
(SELECT A FROM T1 UNION SELECT A FROM T2)
INTERSECT
SELECT A FROM T3;
```
