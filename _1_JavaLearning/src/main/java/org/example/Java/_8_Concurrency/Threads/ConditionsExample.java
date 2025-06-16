package org.example.Java._8_Concurrency.Threads;


public class ConditionsExample {
    static Account account = new Account();
    public static void main(String[] args) {
        Thread t1 = new Thread(new MyDeposit());
        Thread t2 = new Thread(new MyWithdraw());

        t1.start();
        t2.start();

        System.out.println(account.balance);
    }

    static class MyDeposit extends Thread{
        @Override
        public void run() {
            try {
                account.deposit(700);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class MyWithdraw extends Thread{
        @Override
        public void run() {
            try {
                account.withdraw(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    static class Account{
//        Lock lock = new ReentrantLock();
//        Condition newCondition = lock.newCondition();
        int balance = 0;
        public synchronized void deposit(int amount) throws InterruptedException {
//            lock.lock();
            Thread.sleep(1000);
            balance += amount;
            System.out.println("Deposit" + balance);
//            newCondition.signalAll();
            notifyAll();
//            lock.unlock();
        }
        public synchronized void withdraw(int amount) throws InterruptedException {
//            lock.lock();
            while (balance < amount){
//                newCondition.await();
                wait();
            }
            System.out.println("Deposit before withdraw " + balance);
            balance -= amount;
            System.out.println("Deposit after withdraw " + balance);
//            lock.unlock();
        }

    }
}




