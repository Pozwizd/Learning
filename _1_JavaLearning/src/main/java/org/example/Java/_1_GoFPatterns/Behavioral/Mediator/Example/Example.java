package org.example.Java._1_GoFPatterns.Behavioral.Mediator.Example;


import java.util.ArrayList;
import java.util.List;

interface User {
    void sendMessage(String message);

    void getMessage(String message);
}

interface Chat {
    void sendMessage(String message, User user);
}

class Admin implements User {
    Chat chat;
    String name;

    public Admin(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println(this.name + " receiving message: " + message + ".");
    }
}

class SimpleUser implements User {
    Chat chat;
    String name;

    public SimpleUser(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void sendMessage(String message) {
        chat.sendMessage(message, this);
    }

    @Override
    public void getMessage(String message) {
        System.out.println(this.name + " receiving message: " + message + ".");
    }
}

class SimpleTextChat implements Chat {
    User admin;
    List<User> users = new ArrayList<>();

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    void addUsertoChat(User user) {
        this.users.add(user);
    }

    @Override
    public void sendMessage(String message, User user) {
        for (User u : users) {
            if (u != user) {
                u.getMessage(message);
            }
        }
        admin.getMessage(message);
    }
}


public class Example {
    public static void main(String[] args) {
        SimpleTextChat chat = new SimpleTextChat();

        User admin = new Admin(chat, "Admin");
        User user1 = new SimpleUser(chat, "User 1");
        User user2 = new SimpleUser(chat, "User 2");

        chat.setAdmin(admin);
        chat.addUsertoChat(user1);
        chat.addUsertoChat(user2);

        user1.sendMessage("FUCK YOU");
        admin.sendMessage("FUCK YOU TOO");

    }
}
