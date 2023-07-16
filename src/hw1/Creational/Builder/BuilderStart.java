package hw1.Creational.Builder;


enum ComputerType {
    ATX, EATX, MiniATX
}

enum CPU {
    I5, i7, i9
}

enum GPU {
    RTX4090, GTX1080, IntelIntegratedGraphics
}

interface ConstructorComputer {

    void setComputerType(ComputerType computerType);

    void setCPU(CPU cpu);

    void setRam(Ram ram);

    void setGPU(GPU gpu);

    void setHdd(Sdd sdd);
}

class GameComputerBuilder implements ConstructorComputer {
    private ComputerType computerType;
    private CPU cpu;
    private Ram ram;
    private GPU gpu;
    private Sdd sdd;

    @Override
    public void setComputerType(ComputerType computerType) {
        this.computerType = computerType;
    }

    @Override
    public void setCPU(CPU cpu) {
        this.cpu = cpu;
    }

    @Override
    public void setRam(Ram ram) {
        this.ram = ram;
    }

    @Override
    public void setGPU(GPU gpu) {
        this.gpu = gpu;
    }

    @Override
    public void setHdd(Sdd sdd) {
        this.sdd = sdd;
    }

    public GameComputer getResult() {
        return new GameComputer(computerType, cpu, ram, gpu, sdd);
    }

}

class OfficeComputerBuilder implements ConstructorComputer {

    @Override
    public void setComputerType(ComputerType computerType) {

    }

    @Override
    public void setCPU(CPU cpu) {

    }

    @Override
    public void setRam(Ram ram) {

    }

    @Override
    public void setGPU(GPU gpu) {

    }

    @Override
    public void setHdd(Sdd sdd) {

    }
}

class GameComputer {
    private final ComputerType computerType;
    private final CPU cpu;
    private final Ram ram;
    private final GPU gpu;
    private final Sdd sdd;

    public GameComputer(ComputerType computerType, CPU cpu, Ram ram, GPU gpu, Sdd sdd) {
        this.computerType = computerType;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.sdd = sdd;

    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public CPU getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Sdd getSdd() {
        return sdd;
    }

    public GPU getGpu() {
        return gpu;
    }

    @Override
    public String toString() {
        return "GameComputer{" +
                "computerType=" + computerType +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", gpu=" + gpu +
                ", sdd=" + sdd +
                '}';
    }
}

class OfficeComputer {

    private final ComputerType computerType;
    private final CPU cpu;
    private final Ram ram;
    private final GPU gpu;
    private final Sdd sdd;

    public OfficeComputer(ComputerType computerType, CPU cpu, Ram ram, GPU gpu, Sdd sdd) {
        this.computerType = computerType;
        this.cpu = cpu;
        this.ram = ram;
        this.gpu = gpu;
        this.sdd = sdd;

    }

    public ComputerType getComputerType() {
        return computerType;
    }

    public CPU getCpu() {
        return cpu;
    }

    public Ram getRam() {
        return ram;
    }

    public Sdd getSdd() {
        return sdd;
    }

    public GPU getGpu() {
        return gpu;
    }

    @Override
    public String toString() {
        return "GameComputer{" +
                "computerType=" + computerType +
                ", cpu=" + cpu +
                ", ram=" + ram +
                ", gpu=" + gpu +
                ", sdd=" + sdd +
                '}';
    }

}

class Ram {
    private final int MemoryCapacity;
    private final int MemoryFrequency;
    private final String MemoryType;

    public Ram(int MemoryCapacity, int MemoryFrequency, String MemoryType) {
        this.MemoryCapacity = MemoryCapacity;
        this.MemoryFrequency = MemoryFrequency;
        this.MemoryType = MemoryType;
    }

    @Override
    public String toString() {
        return "Ram{" +
                "MemoryCapacity=" + MemoryCapacity +
                ", MemoryFrequency=" + MemoryFrequency +
                ", MemoryType='" + MemoryType + '\'' +
                '}';
    }
}

class Sdd {
    int SSDCapasity;
    String formFactor;

    public Sdd(int SSDCapasity, String formFactor) {
        this.SSDCapasity = SSDCapasity;
        this.formFactor = formFactor;
    }

    public int getSSDCapasity() {
        return SSDCapasity;
    }

    public String getFormFactor() {
        return formFactor;
    }

    @Override
    public String toString() {
        return "Sdd{" +
                "SSDCapasity=" + SSDCapasity +
                ", formFactor='" + formFactor + '\'' +
                '}';
    }
}

class ComputerDirector {

    public void constructGamingComputer(ConstructorComputer builder) {
        builder.setComputerType(ComputerType.ATX);
        builder.setCPU(CPU.i9);
        builder.setRam(new Ram(12000, 5200, "DDR5"));
        builder.setGPU(GPU.RTX4090);
        builder.setHdd(new Sdd(512, "NVME"));
    }

    public void constructOfficeComputer(ConstructorComputer builder) {
        builder.setComputerType(ComputerType.MiniATX);
        builder.setCPU(CPU.I5);
        builder.setRam(new Ram(4096, 3200, "DDR4"));
        builder.setGPU(GPU.IntelIntegratedGraphics);
        builder.setHdd(new Sdd(120, "SATA3"));
    }
}

public class BuilderStart {
    public static void main(String[] args) {
        ComputerDirector director = new ComputerDirector();

        GameComputerBuilder builder = new GameComputerBuilder();

        director.constructGamingComputer(builder);

        GameComputer gameComp = builder.getResult();

        System.out.println(gameComp.getSdd());

    }
}
