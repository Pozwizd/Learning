package org.example.Java._1_GoFPatterns.Structural.Proxy;

interface UserService {
    void login(String username, String password);
}

class UserServiceImpl implements UserService {
    @Override
    public void login(String username, String password) {
        System.out.println("Вход в систему под именем пользователя: " + username + " и паролем: " + password);
    }
}

class UserProxy implements UserService {
    private final UserService userService;
    private final String username;
    private final String password;

    public UserProxy(UserService userService, String username, String password) {
        this.userService = userService;
        this.username = username;
        this.password = password;
    }

    @Override
    public void login(String username, String password) {
        if (!this.username.equals(username) || !this.password.equals(password)) {
            System.out.println("Неверные учетные данные");
            return;
        }
        System.out.println("Вход в систему под именем пользователя: " + username + " и паролем: " + password);
    }
}

public class ProxyExample {
    public static void main(String[] args) {
        UserService userService = new UserProxy(new UserServiceImpl(), "admin", "password");
        userService.login("admin", "password");
        userService.login("admin", "admin");
    }
}
