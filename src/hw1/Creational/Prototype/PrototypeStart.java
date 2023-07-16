package hw1.Creational.Prototype;

interface CopyComputer {
    Object copyComputer();
}

class Computer implements CopyComputer {

    private String nameProcessor;
    private String nameMotherboard;

    private String videoCard;

    public Computer(String nameProcessor, String nameMotherboard, String videoCard) {
        this.nameProcessor = nameProcessor;
        this.nameMotherboard = nameMotherboard;
        this.videoCard = videoCard;
    }

    public String getNameProcessor() {
        return nameProcessor;
    }

    public void setNameProcessor(String nameProcessor) {
        this.nameProcessor = nameProcessor;
    }

    public String getNameMotherboard() {
        return nameMotherboard;
    }

    public void setNameMotherboard(String nameMotherboard) {
        this.nameMotherboard = nameMotherboard;
    }

    public String getVideoCard() {
        return videoCard;
    }

    public void setVideoCard(String videoCard) {
        this.videoCard = videoCard;
    }

    public Object copy() {
        return new Computer(nameProcessor, nameMotherboard, videoCard);
    }


    @Override
    public Object copyComputer() {
        return new Computer(nameProcessor, nameMotherboard, videoCard);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "nameProcessor='" + nameProcessor + '\'' +
                ", nameMotherboard='" + nameMotherboard + '\'' +
                ", videoCard='" + videoCard + '\'' +
                '}';
    }
}

class ComputerFactory {
    Computer computer;

    public ComputerFactory(Computer computer) {
        this.computer = computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    Computer cloneComputer() {
        return (Computer) computer.copy();
    }
}

public class PrototypeStart {
    public static void main(String[] args) {

        Computer computer = new Computer("Ryzen5", "x370", "GTX1080");

        System.out.println(computer);

        ComputerFactory computerFactory = new ComputerFactory(computer);
        Computer replicaPc = computerFactory.cloneComputer();
        Computer computer1 = computerFactory.cloneComputer();
        System.out.println(replicaPc);
        System.out.println(computer1);

    }
}
