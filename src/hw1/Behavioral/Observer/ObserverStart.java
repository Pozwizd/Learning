package hw1.Behavioral.Observer;

import java.util.ArrayList;

interface Subject {
    void registerObserver(Observer o, String name);

    void removeObserver(Observer o);

    void notifyObservers();
}

interface Observer {
    void update(String name);
}

class Chat implements Subject {
    private final ArrayList<Observer> observers = new ArrayList<>();
    private String newParticipantName;

    public void registerObserver(Observer o, String name) {
        observers.add(o);
        newParticipantName = name;
        notifyObservers();
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(newParticipantName);
        }
    }
}

class User implements Observer {
    private final Chat chat;

    private final String name;

    public User(Chat chat, String name) {
        this.chat = chat;
        this.name = name;
        chat.registerObserver(this, name);
    }

    public void update(String name) {
        System.out.println("Новый участник " + name + " присоединился к чату!");
    }
}

public class ObserverStart {
    public static void main(String[] args) {
        Chat chat = new Chat();
        User user1 = new User(chat, "Roman");
        User user2 = new User(chat, "Vitaliy");
        User newUser = new User(chat, "Victor");
    }
}
