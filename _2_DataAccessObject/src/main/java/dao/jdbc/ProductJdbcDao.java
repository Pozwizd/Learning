package dao.jdbc;

import models.Product;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductJdbcDao implements ProductDao {

    private static final String INSERT_PRODUCT =
            "INSERT INTO product (id, product_name, description, price, quantity) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_PRODUCTS = "SELECT * FROM product";
    private static final String UPDATE_PRODUCT =
            "UPDATE product SET product_name = ? , description = ?, price = ?, quantity = ? WHERE id = ?";
    private static final String SELECT_PRODUCT_BY_ID = "SELECT * FROM product WHERE id=?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE id = ?";

    @Override
    public void createProduct(Product product) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_PRODUCT)) {

            stmt.setInt(1, product.getId());
            stmt.setString(2, product.getProductName());
            stmt.setString(3, product.getDescription());
            stmt.setDouble(4, product.getPrice());
            stmt.setInt(5, product.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка добавления продукта", e);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PRODUCTS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                products.add(new Product(
                        rs.getInt("id"),
                        rs.getString("product_name"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity")
                ));
            }
            return products;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения списка продуктов", e);
        }
    }

    @Override
    public void updateProduct(Product product) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_PRODUCT)) {

            stmt.setString(1, product.getProductName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setInt(5, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка обновления продукта", e);
        }
    }

    @Override
    public Product getProductById(int productId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_PRODUCT_BY_ID)) {

            stmt.setInt(1, productId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("id"),
                            rs.getString("product_name"),
                            rs.getString("description"),
                            rs.getDouble("price"),
                            rs.getInt("quantity")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка получения продукта по id=" + productId, e);
        }
    }

    @Override
    public void deleteProduct(int productId) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_PRODUCT)) {

            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка удаления продукта", e);
        }
    }
}
