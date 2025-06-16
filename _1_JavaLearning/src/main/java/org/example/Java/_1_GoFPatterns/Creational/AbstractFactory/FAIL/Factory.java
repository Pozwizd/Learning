package org.example.Java._1_GoFPatterns.Creational.AbstractFactory.FAIL;

interface ComputerFactory {
    Computer createComputer();

    CPU createCPU();

    Motherboard createMotherboard();

    VideoCard createVideoCard();
}

interface Computer {
    void run();
}

interface CPU {
    void process();
}

interface Motherboard {
    void connectComponents();
}

interface VideoCard {
    void render();
}

class GamingComputerFactory implements ComputerFactory {
    @Override
    public Computer createComputer() {
        CPU cpu = createCPU();
        Motherboard motherboard = createMotherboard();
        VideoCard videoCard = createVideoCard();
        return new GamingComputer(cpu, motherboard, videoCard);
    }

    @Override
    public CPU createCPU() {
        return new IntelCorei9();
    }

    @Override
    public Motherboard createMotherboard() {
        return new GamingMotherboard();
    }

    @Override
    public VideoCard createVideoCard() {
        return new NvidiaGeForceRTX3080();
    }
}

class OfficeComputerFactory implements ComputerFactory {
    @Override
    public Computer createComputer() {
        CPU cpu = createCPU();
        Motherboard motherboard = createMotherboard();
        VideoCard videoCard = createVideoCard();
        return new OfficeComputer(cpu, motherboard, videoCard);
    }

    @Override
    public CPU createCPU() {
        return new IntelCorei5();
    }

    @Override
    public Motherboard createMotherboard() {
        return new OfficeMotherboard();
    }

    @Override
    public VideoCard createVideoCard() {
        return new IntegratedGraphics();
    }
}


class GamingComputer implements Computer {
    private final CPU cpu;
    private final Motherboard motherboard;
    private final VideoCard videoCard;

    public GamingComputer(CPU cpu, Motherboard motherboard, VideoCard videoCard) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.videoCard = videoCard;
    }

    @Override
    public void run() {
        System.out.println("Запуск 4 сек");
        cpu.process();
        motherboard.connectComponents();
        videoCard.render();
        System.out.println("Ведьмак 3 грузит в 300 к/с");
    }
}

class OfficeComputer implements Computer {
    private final CPU cpu;
    private final Motherboard motherboard;
    private final VideoCard videoCard;

    public OfficeComputer(CPU cpu, Motherboard motherboard, VideoCard videoCard) {
        this.cpu = cpu;
        this.motherboard = motherboard;
        this.videoCard = videoCard;
    }

    @Override
    public void run() {
        System.out.println("Ща ща иди пока за кофе сходи еще пару мин");
        cpu.process();
        motherboard.connectComponents();
        videoCard.render();
        System.out.println("Предустановленный офис уже запускается");
    }
}


class GamingMotherboard implements Motherboard {
    @Override
    public void connectComponents() {
        System.out.println("Без матери никак");
    }
}

class OfficeMotherboard implements Motherboard {
    @Override
    public void connectComponents() {
        System.out.println("Без матери никак");
    }
}

class NvidiaGeForceRTX3080 implements VideoCard {
    @Override
    public void render() {
        System.out.println("Nvidia GeForce RTX 3080 рендерит ультра графиту в 1млрд фпс");
    }
}

class IntegratedGraphics implements VideoCard {
    @Override
    public void render() {
        System.out.println("Интегрированная графика рендерит картинку ворда...");
    }
}

class IntelCorei9 implements CPU {
    @Override
    public void process() {
        System.out.println("Intel Core i9 обрабатывает данные...");
    }
}

class IntelCorei5 implements CPU {
    @Override
    public void process() {
        System.out.println("Intel Core i5 обрабатывает данные...");
    }
}

public class Factory {
    public static void main(String[] args) {

        ComputerFactory computerFactory = new GamingComputerFactory();
        Computer computer = computerFactory.createComputer();
        computer.run();

        computerFactory = new OfficeComputerFactory();
        Computer computer1 = computerFactory.createComputer();
        computer1.run();

    }
}