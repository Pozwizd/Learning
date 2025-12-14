package org.example.Java._8_Concurrency._11_BestPractices;

/**
 * Best Practice: Использование неизменяемых (immutable) объектов
 * Immutable объекты потокобезопасны по определению
 */
public class _01_ImmutableObjects {
    
    // Неизменяемый класс
    static final class ImmutablePoint {
        private final int x;
        private final int y;
        
        public ImmutablePoint(int x, int y) {
            this.x = x;
            this.y = y;
        }
        
        public int getX() { return x; }
        public int getY() { return y; }
        
        // Вместо изменения создаем новый объект
        public ImmutablePoint move(int dx, int dy) {
            return new ImmutablePoint(x + dx, y + dy);
        }
        
        @Override
        public String toString() {
            return "Point(" + x + ", " + y + ")";
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        ImmutablePoint point = new ImmutablePoint(0, 0);
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ImmutablePoint newPoint = point.move(1, 0);
                System.out.println("Thread-1: " + newPoint);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                ImmutablePoint newPoint = point.move(0, 1);
                System.out.println("Thread-2: " + newPoint);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        
        System.out.println("Оригинальная точка не изменилась: " + point);
    }
}
