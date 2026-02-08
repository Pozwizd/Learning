package hw1.Structural.Adapter;


interface PCI {
}

class CPU {
    private String model;

    public CPU(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
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

    public void setCapacity(String capacity) {
        this.capacity = Integer.parseInt(capacity);
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

public class AdapterStart {
    public static void main(String[] args) {
        CPU cpu = new CPU("i9");
        GPU gpu = new GPU("4090");
        SSD ssd = new SSD(480);

        M2ToPCIEAdapter m2toPCI = new M2ToPCIEAdapter(ssd);

        Computer comp = new Computer(cpu, gpu, m2toPCI);
        System.out.println(comp);
    }
}
