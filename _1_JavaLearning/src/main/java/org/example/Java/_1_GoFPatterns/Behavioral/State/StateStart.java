package org.example.Java._1_GoFPatterns.Behavioral.State;


interface ComputerState {
    void handleState(Computer context);

    void resultWorking();

}

class OnState implements ComputerState {
    @Override
    public void handleState(Computer context) {
        context.setState(new OffState());
        System.out.println("Выключаем компютер");
    }

    @Override
    public void resultWorking() {
        System.out.println("Производит вычисления, выводит изображение на экран");
    }


}

class OffState implements ComputerState {
    @Override
    public void handleState(Computer context) {
        context.setState(new OnState());
        System.out.println("Включаем компютер");
    }

    @Override
    public void resultWorking() {
        System.out.println("Соберает пыль");
    }
}

class Computer {
    private ComputerState state;

    public Computer() {
        this.state = new OffState();
    }

    public ComputerState getState() {
        return state;
    }

    public void setState(ComputerState state) {
        this.state = state;
    }

    public void handlePowerButton() {
        state.handleState(this);
    }

    public void resultWorking() {
        state.resultWorking();
    }
}

public class StateStart {
    public static void main(String[] args) {
        Computer computer = new Computer();
        computer.handlePowerButton();
        computer.resultWorking();
        computer.handlePowerButton();
        computer.resultWorking();
    }
}
