package org.example.Java._1_GoFPatterns.Behavioral.Iterator;

import java.util.ArrayList;
import java.util.Iterator;

abstract class Component {
    protected String name;

    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void check();
}

class Processor extends Component {
    public Processor(String name) {
        super(name);
    }

    public void check() {
        System.out.println("Проверка процессора " + name);
    }
}

class Motherboard extends Component {
    public Motherboard(String name) {
        super(name);
    }

    public void check() {
        System.out.println("Проверка материнской платы " + name);
    }
}

class Memory extends Component {
    public Memory(String name) {
        super(name);
    }

    public void check() {
        System.out.println("Проверка оперативной памяти " + name);
    }
}

class HardDrive extends Component {
    public HardDrive(String name) {
        super(name);
    }

    public void check() {
        System.out.println("Проверка жесткого диска " + name);
    }
}

class Computer implements Iterable<Component> {
    private final ArrayList<Component> components;

    public Computer() {
        components = new ArrayList<Component>();
    }

    public void addComponent(Component component) {
        components.add(component);
    }

    public Component getComponent(int index) {
        return components.get(index);
    }

    public int size() {
        return components.size();
    }

    public Iterator<Component> iterator() {
        return new ComputerIterator(this);
    }
}

class ComputerIterator implements Iterator<Component> {
    private final Computer computer;
    private int currentIndex;

    public ComputerIterator(Computer computer) {
        this.computer = computer;
        currentIndex = 0;
    }

    public boolean hasNext() {
        return currentIndex < computer.size();
    }

    public Component next() {
        if (hasNext()) {
            Component component = computer.getComponent(currentIndex);
            currentIndex++;
            return component;
        }
        return null;
    }
}

public class IteratorStart {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.addComponent(new Processor("Intel Core i7"));
        computer.addComponent(new Motherboard("ASUS ROG Maximus XIII"));
        computer.addComponent(new Memory("32 GB DDR4"));
        computer.addComponent(new HardDrive("1 TB SSD"));

        Iterator<Component> iterator = computer.iterator();
        while (iterator.hasNext()) {
            Component component = iterator.next();
            component.check();
        }
    }
}

