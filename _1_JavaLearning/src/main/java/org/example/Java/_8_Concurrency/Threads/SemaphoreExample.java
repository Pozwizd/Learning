package org.example.Java._8_Concurrency.Threads;

import java.util.concurrent.Semaphore;

public class SemaphoreExample {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        CheckSemaphore c1 = new CheckSemaphore();
        c1.semaphore = semaphore;
        CheckSemaphore c2 = new CheckSemaphore();
        c2.semaphore = semaphore;
        CheckSemaphore c3 = new CheckSemaphore();
        c3.semaphore = semaphore;
        CheckSemaphore c4 = new CheckSemaphore();
        c4.semaphore = semaphore;
        CheckSemaphore c5 = new CheckSemaphore();
        c5.semaphore = semaphore;
        Thread t1 = new Thread(c1);
        Thread t2 = new Thread(c2);
        Thread t3 = new Thread(c3);
        Thread t4 = new Thread(c4);
        Thread t5 = new Thread(c5);
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

    }
    static class CheckSemaphore extends Thread{
        Semaphore semaphore;

        public void run() {
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(Thread.currentThread().getName() + " " + i);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            semaphore.release();
        }
    }

}

