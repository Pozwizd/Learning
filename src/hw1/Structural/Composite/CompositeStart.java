package hw1.Structural.Composite;

import java.util.ArrayList;
import java.util.List;


interface ComputerComponent {
    void showDetails();
}

class CPU implements ComputerComponent {
    private final String brand;
    private final int ClockFrequency;

    public CPU(String brand, int speed) {
        this.brand = brand;
        this.ClockFrequency = speed;
    }

    public String toString() {
        return "CPU{" +
                "brand='" + brand + '\'' +
                ", ClockFrequency=" + ClockFrequency +
                '}';
    }

    public void showDetails() {
        System.out.printf("CPU: %s %d GHz%n", brand, ClockFrequency);
    }
}

class RAM implements ComputerComponent {
    private final String brand;
    private final int capacity;

    public RAM(String brand, int capacity) {
        this.brand = brand;
        this.capacity = capacity;
    }

    public void showDetails() {
        System.out.printf("RAM: %s %d GB memory capacity%n", brand, capacity);
    }
}

class Motherboard implements ComputerComponent {
    private final String brand;
    private final List<ComputerComponent> components = new ArrayList<>();

    public Motherboard(String brand) {
        this.brand = brand;
    }

    public void addComponent(ComputerComponent component) {
        components.add(component);
    }

    public void showDetails() {
        System.out.println("Motherboard: " + brand);
        System.out.println("Components:");
        for (ComputerComponent component : components) {
            component.showDetails();
        }
    }
}

public class CompositeStart {
    public static void main(String[] args) {
        CPU cpu = new CPU("Intel", 5);
        RAM ram = new RAM("GoodRam", 4);
        Motherboard motherboard = new Motherboard("X370");
        motherboard.addComponent(cpu);
        motherboard.addComponent(ram);
        motherboard.showDetails();
    }
}
