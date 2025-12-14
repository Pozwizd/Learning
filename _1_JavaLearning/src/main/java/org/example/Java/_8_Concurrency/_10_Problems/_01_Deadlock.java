package org.example.Java._8_Concurrency._10_Problems;

public class _01_Deadlock {
    public static void main(String[] args) {
        String a = "a";
        String b = "b";

        Thread t1 = new Thread() {
            public void run() {
                synchronized (a) {
                    System.out.println("Thread 1: locked a");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    synchronized (b) {
                        System.out.println("Thread 1: locked b");
                    }
                }
            }
        };

        Thread t2 = new Thread() {
            public void run() {
                synchronized (b) {
                    System.out.println("Thread 2: locked b");
                    try {
                        Thread.sleep(100);
                    } catch (Exception e) {
                    }
                    synchronized (a) {
                        System.out.println("Thread 2: locked a");
                    }
                }
            }
        };

        t1.start();
        t2.start();
    }
}
