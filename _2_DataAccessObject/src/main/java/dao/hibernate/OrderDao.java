package dao.hibernate;



import models.Order;

import java.util.List;

public interface OrderDao {

    void createOrder(Order order);

    List<Order> getUserOrders(long userId);

    List<Order> getAllOrders();

}