package org.example.Java._1_GoFPatterns.Creational.AbstractFactory;

interface Computer {
    void run();
}

interface Monitor {
    void run();
}

interface ComputerFactory {
    Computer createComputer();

    Monitor createMonitor();
}

class GamingComputer implements Computer {
    @Override
    public void run() {
        System.out.println("Обрабатывает код игры и выводит изображение");
    }
}

class OfficeComputer implements Computer {
    @Override
    public void run() {
        System.out.println("Производит рабочие вычисления");
    }
}

class GamingMonitor implements Monitor {
    @Override
    public void run() {
        System.out.println("Отображает картинку в 240 кадров в секунду");
    }
}

class OfficeMonitor implements Monitor {
    @Override
    public void run() {
        System.out.println("Отображает результаты рабочих вычислений");
    }
}

class GamingComputerFactory implements ComputerFactory {
    @Override
    public Computer createComputer() {
        return new GamingComputer();
    }

    @Override
    public Monitor createMonitor() {
        return new GamingMonitor();
    }
}

class OfficeComputerFactory implements ComputerFactory {
    @Override
    public Computer createComputer() {
        return new OfficeComputer();
    }

    @Override
    public Monitor createMonitor() {
        return new OfficeMonitor();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {

        ComputerFactory gamingFactory = new GamingComputerFactory();

        Computer gamingComputer = gamingFactory.createComputer();
        Monitor gamingMonitor = gamingFactory.createMonitor();

        gamingComputer.run();
        gamingMonitor.run();


        ComputerFactory officeFactory = new OfficeComputerFactory();

        Computer officeComputer = officeFactory.createComputer();
        Monitor officeMonitor = officeFactory.createMonitor();

        officeComputer.run();
        officeMonitor.run();
    }
}