package org.example.Java._1_GoFPatterns.Structural.Flyweight;

import java.util.HashMap;
import java.util.Map;

interface Component {
    void printInfo(String extrinsicState);
}

class GraphicsCard implements Component {
    private final String intrinsicState;

    public GraphicsCard(String memorySize, String clockSpeed) {
        this.intrinsicState = "Graphics card with " + memorySize + " memory size and " + clockSpeed + " GHz clock speed";
    }

    @Override
    public void printInfo(String extrinsicState) {
        System.out.println(intrinsicState + " and " + extrinsicState);
    }
}


class ComponentFactory {
    private final Map<String, Component> components = new HashMap<>();


    public Component getComponent(String memorySize, String clockSpeed) {
        String key = memorySize + "-" + clockSpeed;
        Component component = components.get(key);

        if (component == null) {
            component = new GraphicsCard(memorySize, clockSpeed);
            components.put(key, component);
        }

        return component;
    }
}


class ComputerBuilder {
    private final ComponentFactory componentFactory = new ComponentFactory();
    private Component graphicsCard;

    public void buildComputer(String memorySize, String clockSpeed) {

        graphicsCard = componentFactory.getComponent(memorySize, clockSpeed);
        graphicsCard.printInfo("installed in the computer");

    }
}

// Тестовый код
public class FlyWeightStart {
    public static void main(String[] args) {
        ComputerBuilder builder = new ComputerBuilder();
        builder.buildComputer("8GB", "1.5");
        builder.buildComputer("4GB", "1.2");
        builder.buildComputer("8GB", "1.5");
        builder.buildComputer("12GB", "1.8");

    }
}







