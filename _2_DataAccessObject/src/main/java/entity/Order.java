package entity;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "order_list")
    private String orderList;

    @Column(name = "total_price")
    private double totalPrice;

    public Order() {
    }

    public Order(User user, String orderList, double totalPrice) {
        this.user = user;
        this.orderList = orderList;
        this.totalPrice = totalPrice;
    }

    public Order(long id, User user, String orderList, double totalPrice) {
        this.id = id;
        this.user = user;
        this.orderList = orderList;
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
        if (user != null && !user.getOrders().contains(this)) {
            user.getOrders().add(this);
        }
    }

    public String getOrderList() {
        return orderList;
    }

    public void setOrderList(String orderList) {
        this.orderList = orderList;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
