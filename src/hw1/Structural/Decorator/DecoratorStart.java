package hw1.Structural.Decorator;

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

    public String getModel() {
        return model;
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
    private final double overclockFactor = 1.15;
    boolean boostMaybe;

    public OverclockedCPU(CPU baseCpu) {
        this.baseCpu = baseCpu;
    }

    @Override
    public double getClockFrequency() {
        return baseCpu.getClockFrequency() * overclockFactor;
    }

}

class сpu implements CPU {
    private final String model;
    private double clockFrequency;

    public сpu(String model, double clockFrequency) {
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


public class DecoratorStart {
    public static void main(String[] args) {
        сpu cpu = new сpu("i9", 5.3);
        OverclockedCPU overclockedCPU = new OverclockedCPU(cpu);
        GPU gpu = new GPU("4090");
        SSD ssd = new SSD(480);
        M2ToPCIEAdapter m2 = new M2ToPCIEAdapter(ssd);

        Computer comp = new Computer(overclockedCPU, gpu, m2);
        System.out.println(comp.getCpu().getClockFrequency());
    }
}




