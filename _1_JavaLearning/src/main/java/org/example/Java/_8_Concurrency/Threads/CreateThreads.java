package org.example.Java._8_Concurrency.Threads;

class CreateThreads {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Hello world! " + Thread.currentThread().getName());

//        MyThread myThread = new MyThread();
//        myThread.start();


        Thread  myThread = new Thread(new MyThreadRunnable());
        myThread.start();

//        Thread.sleep(1000);
        myThread.join();

        Thread  myThread2 = new Thread(new MyThreadRunnable());
        myThread2.start();
        myThread2.join();
        System.out.println("Main thread is dead");
    }
}

//class MyThread extends Thread{
//    @Override
//    public void run(){
//        System.out.println("Hello from MyThread" + Thread.currentThread().getName());
//    }
//}

class MyThreadRunnable implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 10000; i++){
            if ("Thread-0".equals(Thread.currentThread().getName())){
            Thread.yield();
            }
            System.out.println(i + " " + Thread.currentThread().getName());

        }

    }
}