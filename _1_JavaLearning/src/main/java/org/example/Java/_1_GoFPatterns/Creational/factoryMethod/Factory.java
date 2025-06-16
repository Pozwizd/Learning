package org.example.Java._1_GoFPatterns.Creational.factoryMethod;


interface KnifeFactory {
    Knife createKnife();
}

interface Knife {
    void cut();
}

class ArmyKnifeFactory implements KnifeFactory {
    @Override
    public Knife createKnife() {
        return new ArmyKnife();
    }
}

class ArmyKnife implements Knife {
    @Override
    public void cut() {
        System.out.println("Открыть тушенку");
    }
}

class ChefKnifeFactory implements KnifeFactory {
    @Override
    public Knife createKnife() {
        return new ChefKnife();
    }
}

class ChefKnife implements Knife {
    @Override
    public void cut() {
        System.out.println("Резать овощи");
    }
}

class CampingKnifeFactory implements KnifeFactory {
    @Override
    public Knife createKnife() {
        return new CampingKnife();
    }
}

class CampingKnife implements Knife {
    @Override
    public void cut() {
        System.out.println("Резать...");
    }
}

public class Factory {
    public static void main(String[] args) {
        KnifeFactory factory = new ArmyKnifeFactory();
        Knife knife = factory.createKnife();
        knife.cut();
        factory = new ChefKnifeFactory();
        knife = factory.createKnife();
    }
}
