package hw1.Behavioral.TemplateMethod;

abstract class ComputerBuilder {
    protected Computer computer;

    public Computer getComputer() {
        return computer;
    }

    public void buildComputer() {
        createNewComputer();
        addMotherboard();
        addCPU();
        addRAM();
        addStorage();
        addGraphicsCard();
        addPowerSupply();
    }

    public void createNewComputer() {
        computer = new Computer();
    }

    public abstract void addMotherboard();

    public abstract void addCPU();

    public abstract void addRAM();

    public abstract void addStorage();

    public abstract void addGraphicsCard();

    public abstract void addPowerSupply();
}

class GamingComputerBuilder extends ComputerBuilder {

    @Override
    public void addMotherboard() {
        computer.setMotherboard("Intel Z790");
    }

    @Override
    public void addCPU() {
        computer.setCPU("Intel Core i9");
    }

    @Override
    public void addRAM() {
        computer.setRAM("32GB DDR4");
    }

    @Override
    public void addStorage() {
        computer.setStorage("1TB NVMe SSD");
    }

    @Override
    public void addGraphicsCard() {
        computer.setGraphicsCard("RTX 3080");
    }

    @Override
    public void addPowerSupply() {
        computer.setPowerSupply("1000W");
    }

}


class OfficeComputerBuilder extends ComputerBuilder {

    @Override
    public void addMotherboard() {
        computer.setMotherboard("AMD A320");
    }

    @Override
    public void addCPU() {
        computer.setCPU("AMD Ryzen 3 2200G");
    }

    @Override
    public void addRAM() {
        computer.setRAM("16GB DDR4");
    }

    @Override
    public void addStorage() {
        computer.setStorage("256GB SSD");
    }

    @Override
    public void addGraphicsCard() {
        computer.setGraphicsCard("Integrated AMD Radeon Graphics");
    }

    @Override
    public void addPowerSupply() {
        computer.setPowerSupply("500W");
    }

}

class Computer {
    private String motherboard;
    private String cpu;
    private String ram;
    private String storage;
    private String graphicsCard;
    private String powerSupply;

    public String getMotherboard() {
        return motherboard;
    }

    public void setMotherboard(String motherboard) {
        this.motherboard = motherboard;
    }

    public String getCPU() {
        return cpu;
    }

    public void setCPU(String cpu) {
        this.cpu = cpu;
    }

    public String getRAM() {
        return ram;
    }

    public void setRAM(String ram) {
        this.ram = ram;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public void setPowerSupply(String powerSupply) {
        this.powerSupply = powerSupply;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "motherboard='" + motherboard + '\'' +
                ", cpu='" + cpu + '\'' +
                ", ram='" + ram + '\'' +
                ", storage='" + storage + '\'' +
                ", graphicsCard='" + graphicsCard + '\'' +
                ", powerSupply='" + powerSupply + '\'' +
                '}';
    }
}

public class TemplateMethodStart {
    public static void main(String[] args) {
        ComputerBuilder builder = new GamingComputerBuilder();
        builder.buildComputer();
        Computer gamingComputer = builder.getComputer();
        System.out.println(gamingComputer.toString());

        System.out.println("<========================================>");
    }
}
