package org.example.Java._11_InputOutput;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Task7 {
}

class SerializeUsers {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();

        users.add(new User("Іван", "Петров", 30));
        users.add(new User("Алексей", "Сидоров", 45));

        try {
            FileOutputStream fos = new FileOutputStream("users.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(users);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class DeserializeUsers {

    public static void main(String[] args) {

        File file = new File("users.bin");

        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            List<User> users = (List<User>) ois.readObject();

            for (User user : users) {
                System.out.println(user);
            }

            ois.close();
            fis.close();

            file.delete();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

class User implements Serializable {

    private String name;
    private String surname;
    private int age;

    public User(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                '}';
    }
}
