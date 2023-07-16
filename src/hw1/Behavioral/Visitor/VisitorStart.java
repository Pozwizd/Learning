package hw1.Behavioral.Visitor;

import java.util.Arrays;

interface ComputerPart {
    void accept(ComputerPartVisitor visitor);
}

interface ComputerPartVisitor {
    void visit(Keyboard keyboard);

    void visit(Monitor monitor);

    void visit(Mouse mouse);

    void visit(Computer computer);
}

class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

//ret
class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor visitor) {
        visitor.visit(this);
    }
}

class Computer implements ComputerPart {
    ComputerPart[] parts;

    public Computer() {
        this.parts = new ComputerPart[]{new Keyboard(), new Monitor(), new Mouse()};
    }

    @Override
    public void accept(ComputerPartVisitor visitor) {
        for (ComputerPart part : parts) {
            part.accept(visitor);
        }
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "Computer{" +
                "parts=" + Arrays.toString(parts) +
                '}';
    }
}

class ComputerPartRepairVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Repairing keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Repairing monitor.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Repairing mouse.");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("Repairing computer.");
    }
}

class ComputerPartUsageVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("The keyboard was used.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("The monitor was used.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("The mouse was used.");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("The computer was used.");
    }
}

public class VisitorStart {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        ComputerPartVisitor visitor = new ComputerPartUsageVisitor();
        ComputerPartRepairVisitor computerPartRepairVisitor = new ComputerPartRepairVisitor();
        computer.accept(visitor);
        computer.accept(computerPartRepairVisitor);

        System.out.println(computer);
    }
}
