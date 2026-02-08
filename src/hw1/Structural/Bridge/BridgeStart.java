package hw1.Structural.Bridge;

interface Processor {
    void powerOn();

    void powerOff();
}

interface VideoCard {
    void powerOn();

    void powerOff();
}

class ProcessorImpl implements Processor {
    private final int numberOfCores;
    private final double frequency;

    public ProcessorImpl(int numberOfCores, double frequency) {
        this.numberOfCores = numberOfCores;
        this.frequency = frequency;
    }

    public int getNumberOfCores() {
        return numberOfCores;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public void powerOn() {
        System.out.println("Процессор работает");
    }

    @Override
    public void powerOff() {
        System.out.println("Процессор не работает");
    }
}

class GraphicsCard implements VideoCard {
    private final int capacityMemory;
    private final double frequency;

    public GraphicsCard(int capacityMemory, double frequency) {
        this.capacityMemory = capacityMemory;
        this.frequency = frequency;
    }

    public int getCapacityMemory() {
        return capacityMemory;
    }

    public double getFrequency() {
        return frequency;
    }

    @Override
    public void powerOn() {
        System.out.println("Видеокарта работает");
    }

    @Override
    public void powerOff() {
        System.out.println("Видеокарта не работает");
    }
}

//=====================================================================================================================

abstract class Computer {
    protected Processor processor;
    protected VideoCard videoCard;

    public Computer(Processor processor, VideoCard videoCard) {
        this.processor = processor;
        this.videoCard = videoCard;
    }

    public abstract void powerOn();

    public abstract void powerOff();
}

//=====================================================================================================================

class GamingComputer extends Computer {

    public GamingComputer(Processor processor, VideoCard videoCard) {
        super(processor, videoCard);
    }

    @Override
    public void powerOn() {
        System.out.println("Gaming Computer: Powering on");
        processor.powerOn();
        videoCard.powerOn();
    }

    @Override
    public void powerOff() {
        System.out.println("Gaming Computer: Powering off");
        videoCard.powerOff();
        processor.powerOff();
    }
}

class Workstation extends Computer {

    public Workstation(Processor processor, VideoCard videoCard) {
        super(processor, videoCard);
    }

    @Override
    public void powerOn() {
        System.out.println("Workstation: Powering on");
        processor.powerOn();
        videoCard.powerOn();
    }

    @Override
    public void powerOff() {
        System.out.println("Workstation: Powering off");
        videoCard.powerOff();
        processor.powerOff();
    }
}

class ComputerFactory {
    static Computer createComputer(Processor processor, GraphicsCard videoCard) {
        if (videoCard.getCapacityMemory() >= 8) {
            return new GamingComputer(processor, videoCard);
        } else {
            return new Workstation(processor, videoCard);
        }
    }
}

public class BridgeStart {
    public static void main(String[] args) {
        Processor processor = new ProcessorImpl(4, 2.5);
        GraphicsCard videoCard = new GraphicsCard(8, 1.3);

        Computer computer = ComputerFactory.createComputer(processor, videoCard);

        computer.powerOn();
        computer.powerOff();
    }
}