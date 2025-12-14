package org.example.Java._8_Concurrency._03_LockFramework;


import java.util.concurrent.locks.ReentrantLock;

public class _01_ReentrantLock {
    public static void main(String[] args) throws InterruptedException {
        String a = "a";
        String b = "b";

        MyThreadLock MyThreadLock = new MyThreadLock();

        Thread t1 = new Thread(MyThreadLock);

        Thread t2 = new Thread(MyThreadLock);

        t1.start();
        t2.start();
        Thread.sleep(1000);
        System.out.println(MyThreadLock.getX());
    }
}

class MyThreadLock extends Thread{
    ReentrantLock lock = new ReentrantLock();
    int x = 0;
    @Override
    public void run(){
        lock.lock();
        for (int i = 0; i < 10000; i++){
            int temp = x;
            temp++;
            x = temp;

        }
        lock.unlock();
    }

    public int getX(){
        return x;
    }
}