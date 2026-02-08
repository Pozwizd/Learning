package hw1.Structural.Facade;


interface PCI {

}

interface CPU {
    double getClockFrequency();
}

class GPU implements PCI {
    private String model;

    public GPU(String model) {
        this.model = model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}

class M2ToPCIEAdapter implements PCI {
    private int m2SSD;

    public M2ToPCIEAdapter(SSD ssd) {
        this.m2SSD = ssd.getCapacity();
    }

    public int getM2() {
        return m2SSD;
    }

    public void setM2(int m2SSD) {
        this.m2SSD = m2SSD;
    }
}

class SSD {
    private int capacity;

    public SSD(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}

class Computer {
    private final CPU cpu;
    private final PCI gpuSlot;
    private final PCI m2Slot;

    public Computer(CPU cpu, PCI gpuSlot, PCI m2Slot) {
        this.cpu = cpu;
        this.gpuSlot = gpuSlot;
        this.m2Slot = m2Slot;
    }

    public CPU getCpu() {
        return cpu;
    }

    public PCI getGpuSlot() {
        return gpuSlot;
    }

    public PCI getM2Slot() {
        return m2Slot;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "cpu=" + cpu +
                ", gpuSlot=" + gpuSlot +
                ", m2Slot=" + m2Slot +
                '}';
    }
}

class OverclockedCPU implements CPU {
    private final CPU baseCpu;
    boolean boostMaybe;

    public OverclockedCPU(CPU baseCpu) {
        this.baseCpu = baseCpu;
    }

    @Override
    public double getClockFrequency() {
        double overclockFactor = 1.15;
        return baseCpu.getClockFrequency() * overclockFactor;
    }

}

class CPU2 implements CPU {
    private final String model;
    private double clockFrequency;

    public CPU2(String model, double clockFrequency) {
        this.model = model;
        this.clockFrequency = clockFrequency;
    }


    public double getClockFrequency() {
        return clockFrequency;
    }

    public void setClockFrequency(double clockFrequency) {
        this.clockFrequency = clockFrequency;
    }

    @Override
    public String toString() {
        return "CPU{" +
                "model='" + model + '\'' +
                ", clockFrequency=" + clockFrequency +
                '}';
    }
}

class ComputerFacade {
    private final OverclockedCPU cpu2;
    private final GPU gpuSlot;
    private final PCI m2Slot;

    public ComputerFacade(String cpuModel, double cpuFrequency, String gpuModel, String m2Model, int m2Capacity) {
        SSD ssd = new SSD(m2Capacity);
        CPU2 cpu = new CPU2(cpuModel, cpuFrequency);
        this.cpu2 = new OverclockedCPU(cpu);
        this.gpuSlot = new GPU(gpuModel);
        this.m2Slot = new M2ToPCIEAdapter(ssd);
    }

    public Computer buildComputer() {
        return new Computer(cpu2, gpuSlot, m2Slot);
    }
}

public class FacadeStart {
    public static void main(String[] args) {
        ComputerFacade facade = new ComputerFacade("i9", 5.3,
                "4090", "M.2 SSD", 480);
        Computer computer = facade.buildComputer();
        System.out.println(computer.toString());
    }
}
