package org.example.Java._8_Concurrency._03_LockFramework;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class _03_ConditionExample {
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();
    private boolean conditionMet = false;

    public void awaitCondition() throws InterruptedException {
        lock.lock();
        try {
            while (!conditionMet) {
                condition.await();
            }
            // Действия, которые нужно выполнить после выполнения условия
        } finally {
            lock.unlock();
        }
    }

    public void signalCondition() {
        lock.lock();
        try {
            conditionMet = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}