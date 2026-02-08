package hw1.Creational.Singleton;

class Singleton {
    private static Singleton instance;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void doSomething() {
        System.out.println("Doing something...");
    }
}

public class Example {
    public static void main(String[] args) {
        Singleton One = Singleton.getInstance();
        Singleton Two = Singleton.getInstance();
        System.out.println(Two);
        System.out.println(One);
    }
}
