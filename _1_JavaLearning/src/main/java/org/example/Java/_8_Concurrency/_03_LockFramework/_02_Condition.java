package org.example.Java._8_Concurrency._03_LockFramework;

public class _02_Condition {
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
            synchronized (this) {
                account.deposit(100);
            }
        }
    }

    static class MyWithdraw extends Thread{
        @Override
        public void run() {
            synchronized (this) {
                account.withdraw(100);
            }
        }
    }

    static class Account{
        _01_ReentrantLock lock = new _01_ReentrantLock();
//        Condition newCondition = (Condition) lock.newCondition();
        int balance = 0;
        public void deposit(int amount){
            balance += amount;
        }
        public void withdraw(int amount){
            while (balance < amount){
//                newCondition.wait();
            }
        }

    }
}




