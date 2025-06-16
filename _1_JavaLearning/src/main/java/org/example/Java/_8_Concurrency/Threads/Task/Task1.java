package org.example.Java._8_Concurrency.Threads.Task;

public class Task1 {
    private static final Object monitor = new Object();
    private static int currentThread = 0;

    public static void main(String[] args) {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                printLetter("A", 5);
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                printLetter("B", 5);
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                printLetter("C", 5);
            }
        });

        threadA.start();
        threadB.start();
        threadC.start();
    }

    private static void printLetter(String letter, int count) {
        synchronized (monitor) {
            try {
                for (int i = 0; i < count; i++) {
                    while (!letter.equals(getExpectedLetter())) {
                        monitor.wait();
                    }
                    System.out.print(letter);
                    switchToNextThread();
                    monitor.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static String getExpectedLetter() {
        switch (currentThread) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            default:
                return "";
        }
    }

    private static void switchToNextThread() {
        currentThread = (currentThread + 1) % 3;
    }
}