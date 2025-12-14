package org.example.Java._8_Concurrency._02_Synchronization;

class Synchronized {
    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread();

        Thread  t1 = new Thread(myThread);
        t1.start();

        Thread  t2 = new Thread(myThread);
        t2.start();
        Thread.sleep(1000);
        System.out.println(myThread.getX());
    }
}

class MyThread extends Thread{
    int x = 0;
    @Override
    public synchronized void run(){
        for (int i = 0; i < 10000; i++){
            int temp = x;
            temp++;
            x = temp;

        }
    }

    public int getX(){
        return x;
    }
}

