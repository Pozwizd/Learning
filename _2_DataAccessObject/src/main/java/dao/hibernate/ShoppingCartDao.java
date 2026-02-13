package dao.hibernate;

import entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartDao {


    void addProductToCart(ShoppingCart shoppingCart);

    void removeProductFromCart(ShoppingCart shoppingCart);

    List<ShoppingCart> getUserCartProducts(long userId);

    void clearUserCart(long userId);

}
