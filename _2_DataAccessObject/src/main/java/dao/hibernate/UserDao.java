package dao.hibernate;

import entity.User;

import java.util.List;

public interface UserDao {

    void createUser(User user);

    void updateUser(User user);

    User getUserById(long id);

    List<User> getAllUsers();

    void deleteUser(User user);
}
