package org.example.Java._1_GoFPatterns.Behavioral.Mediator;

import java.util.ArrayList;


class Chat {
    private final ArrayList<User> users = new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public void sendMessage(User recipient, Message message) {
        User sender = message.getSender();
        String text = message.getText();

        if (users.contains(recipient) && !recipient.equals(sender)) {
            recipient.receiveMessage(sender, text);
        }
    }
}

class User {
    Chat chat;
    String name;

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
    }

    public void sendMessage(User recipient, String text) {
        Message message = new Message(this, text);
        recipient.chat.sendMessage(recipient, message);
    }

    public void receiveMessage(User sender, String text) {
        System.out.println(this.name + " получил сообщение от " + sender.getName() + ": " + text);
    }

    public String getName() {
        return this.name;
    }
}

class Message {
    private final User sender;
    private final String text;

    public Message(User sender, String text) {
        this.sender = sender;
        this.text = text;
    }

    public User getSender() {
        return sender;

    }

    public String getText() {
        return text;
    }
}

public class MediatorStart {
    public static void main(String[] args) {
        Chat chat = new Chat();

        User user1 = new User(chat, "Артос");
        User user2 = new User(chat, "Партос");
        User user3 = new User(chat, "Ашот");

        chat.addUser(user1);
        chat.addUser(user2);
        chat.addUser(user3);

        user1.sendMessage(user2, "Привет");
        user2.sendMessage(user1, "Привет, а третий хто?");
        user1.sendMessage(user2, "Ща удалю");
        user1.sendMessage(user2, "Путин ще не здох?");
        user2.sendMessage(user1, "Поки ще не");
    }
}
