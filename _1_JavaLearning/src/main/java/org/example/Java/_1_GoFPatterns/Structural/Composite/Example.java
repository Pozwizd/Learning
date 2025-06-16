package org.example.Java._1_GoFPatterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

interface Developer {
    void writeCode();
}

class JavaDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Writes Java Code");
    }
}

class CppDeveloper implements Developer {

    @Override
    public void writeCode() {
        System.out.println("Writes Cpp Code");
    }
}

class Team {
    private final List<Developer> developers = new ArrayList<Developer>();

    void addDeveloper(Developer developer) {
        developers.add(developer);
    }

    void removeDeveloper(Developer developer) {
        developers.remove(developer);
    }

    void createProject() {
        System.out.println("Team create project");
        for (Developer developer : developers) {
            developer.writeCode();
        }
    }

}


public class Example {
    public static void main(String[] args) {
        Team team = new Team();

        Developer firstJavaDeveloper = new JavaDeveloper();
        Developer secondJavaDeveloper = new JavaDeveloper();
        Developer CppDeveloper = new CppDeveloper();

        team.addDeveloper(firstJavaDeveloper);
        team.addDeveloper(secondJavaDeveloper);
        team.addDeveloper(CppDeveloper);

        team.createProject();
    }
}
