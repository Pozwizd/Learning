package org.example.Java._8_Concurrency.Threads.Task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

import static java.lang.Thread.sleep;


public class Task4 {

    private static CountDownLatch startLatch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {

        List<Car> cars = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Car car = new Car(i);
            cars.add(car);
            threads.add(new Thread(car));
        }

        for (Thread t : threads) {
            t.start();
        }

        startLatch.await();
        System.out.println("Старт гонки!");

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Гонка закончилась!");
        printResults(cars);
    }

    public static void printResults(List<Car> cars) {
        List<Result> results = new ArrayList<>();

        for (Car car : cars) {
            results.add(car.getResult());
        }

        results.sort(Comparator.comparingInt(r -> r.time));

        results.stream().map(r -> r.carId + " - " + r.time).forEach(x -> System.out.println(x));
    }

    static class Car implements Runnable  {

        private int id;
        private int prepareTime;
        private int roadTime;
        private int tunnelTime;
        private int totalTime;

        private Result result;

        private static Random rand = new Random();
        private static Semaphore semaphore = new Semaphore(3);

        public Car(int id) {
            this.id = id;
            prepareTime = rand.nextInt(1000);
            roadTime = rand.nextInt(1000);
            tunnelTime = rand.nextInt(1000);
            totalTime = prepareTime + roadTime * 2 + tunnelTime;
        }

        public Result getResult() {
            return result;
        }

        @Override
        public void run() {
            try {
                prepare();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            startLatch.countDown();

            try {
                startLatch.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                driveRoad1();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            driveTunnel();
            try {
                driveRoad2();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            finish();
        }

        private void prepare() throws InterruptedException {
            sleep(prepareTime);
            System.out.println("Машина " + id + " подготовилась");
        }

        private void driveRoad1() throws InterruptedException {
            sleep(roadTime);
            System.out.println("Машина " + id + " проехала первый участок");
        }

        private void driveTunnel() {
            try {
                semaphore.acquire();
                sleep(tunnelTime);
                System.out.println("Машина " + id + " проехала тоннель");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }

        private void driveRoad2() throws InterruptedException {
            sleep(roadTime);
            System.out.println("Машина " + id + " проехала второй участок");
        }
        private void finish() {
            result = new Result(id, totalTime);
            System.out.println("Машина " + id + " финишировала!");
        }

    }

    static class Result {
        int carId;
        int time;

        public Result(int carId, int time) {
            this.carId = carId;
            this.time = time;
        }
    }

}