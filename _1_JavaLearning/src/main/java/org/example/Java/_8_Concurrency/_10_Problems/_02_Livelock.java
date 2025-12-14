package org.example.Java._8_Concurrency._10_Problems;

/**
 * Демонстрация проблемы Livelock
 * Потоки продолжают работу, но не могут завершить задачу
 */
public class _02_Livelock {
    static class Spoon {
        private Diner owner;
        
        public Spoon(Diner d) {
            owner = d;
        }
        
        public Diner getOwner() {
            return owner;
        }
        
        public synchronized void setOwner(Diner d) {
            owner = d;
        }
        
        public synchronized void use() {
            System.out.printf("%s использует ложку!%n", owner.name);
        }
    }
    
    static class Diner {
        private String name;
        private boolean isHungry;
        
        public Diner(String n) {
            name = n;
            isHungry = true;
        }
        
        public void eatWith(Spoon spoon, Diner spouse) {
            while (isHungry) {
                // Если ложка у другого
                if (spoon.owner != this) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        continue;
                    }
                    continue;
                }
                
                // Если партнер голоден, отдаем ложку (вежливость)
                if (spouse.isHungry) {
                    System.out.printf("%s: Ты выглядишь голодным, возьми ложку%n", name);
                    spoon.setOwner(spouse);
                    continue;
                }
                
                // Едим
                spoon.use();
                isHungry = false;
                System.out.printf("%s: Я поел!%n", name);
                spoon.setOwner(spouse);
            }
        }
    }
    
    public static void main(String[] args) {
        final Diner husband = new Diner("Муж");
        final Diner wife = new Diner("Жена");
        
        final Spoon s = new Spoon(husband);
        
        new Thread(() -> husband.eatWith(s, wife)).start();
        new Thread(() -> wife.eatWith(s, husband)).start();
        
        // Потоки будут постоянно отдавать ложку друг другу - Livelock!
    }
}
