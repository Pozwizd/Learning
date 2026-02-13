package dao.jdbc;

import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJdbc implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoJdbc.class);

    private static final String INSERT_USER =
            "INSERT INTO users (username, password, email, phone_number) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_ID = "SELECT * FROM users WHERE id=?";
    private static final String UPDATE_USER =
            "UPDATE users SET id = ?, username = ?, password = ?, email = ?, phone_number = ? WHERE id = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM users";
    private static final String DELETE_USER_DETAILS = "DELETE FROM shop.user_details WHERE user_id = ?";
    private static final String DELETE_USER = "DELETE FROM users WHERE id = ?";

    @Override
    public void createUser(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_USER)) {

            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone_number());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Ошибка добавления пользователя: {}", user.getUsername(), e);
            throw new RuntimeException("Ошибка добавления пользователя", e);
        }
    }

    @Override
    public User getUserById(int id) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_USER_BY_ID)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new User(
                            rs.getInt("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("email"),
                            rs.getString("phone_number")
                    );
                }
            }
            return null;
        } catch (SQLException e) {
            LOGGER.error("Ошибка получения пользователя по id={}", id, e);
            throw new RuntimeException("Ошибка получения пользователя", e);
        }
    }

    @Override
    public void updateUser(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_USER)) {

            stmt.setInt(1, user.getId());
            stmt.setString(2, user.getUsername());
            stmt.setString(3, user.getPassword());
            stmt.setString(4, user.getEmail());
            stmt.setString(5, user.getPhone_number());
            stmt.setInt(6, user.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Ошибка обновления пользователя id={}", user.getId(), e);
            throw new RuntimeException("Ошибка обновления пользователя", e);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_USERS);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("phone_number")
                ));
            }
            return users;
        } catch (SQLException e) {
            LOGGER.error("Ошибка получения всех пользователей", e);
            throw new RuntimeException("Ошибка получения всех пользователей", e);
        }
    }

    @Override
    public void deleteUser(User user) {
        try (Connection connection = ConnectionPool.getConnection();
             PreparedStatement deleteDetailsStmt = connection.prepareStatement(DELETE_USER_DETAILS);
             PreparedStatement deleteUserStmt = connection.prepareStatement(DELETE_USER)) {

            deleteDetailsStmt.setInt(1, user.getId());
            deleteDetailsStmt.executeUpdate();

            deleteUserStmt.setInt(1, user.getId());
            deleteUserStmt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error("Ошибка удаления пользователя id={}", user.getId(), e);
            throw new RuntimeException("Ошибка удаления пользователя", e);
        }
    }
}
