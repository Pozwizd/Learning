SELECT sc.user_id, p.product_name, p.price, sc.quantity
FROM shopping_cart sc
JOIN product p ON sc.product_id = p.id;