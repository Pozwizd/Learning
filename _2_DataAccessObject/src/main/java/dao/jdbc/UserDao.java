package dao.jdbc;

import models.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);

    void updateUser(User user);

    User getUserById(int id);

    List<User> getAllUsers();

    void deleteUser(User user);
}
