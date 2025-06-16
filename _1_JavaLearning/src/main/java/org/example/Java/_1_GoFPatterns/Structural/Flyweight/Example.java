package org.example.Java._1_GoFPatterns.Structural.Flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

interface Developer {
    void writeCode();
}

class JavaDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Java developer writes JavaCode");
    }
}

class CppDeveloper implements Developer {
    @Override
    public void writeCode() {
        System.out.println("C++ developer writes C++ Code");
    }
}

class DeveloperFactory {
    private static final Map<String, Developer> developers = new HashMap<>();

    public Developer getDeveloperBySpecialty(String specialty) {
        Developer developer = developers.get(specialty);

        if (developer == null) {
            switch (specialty) {
                case "Java":
                    System.out.println("Hiring Java Developer");
                    developer = new JavaDeveloper();
                    break;
                case "C++":
                    System.out.println("Hiring C++ Developer");
                    developer = new CppDeveloper();
                    break;
            }
            developers.put(specialty, developer);
        }
        return developer;
    }
}

class Example {
    public static void main(String[] args) {
        DeveloperFactory developerFactory = new DeveloperFactory();
        List<Developer> developers = new ArrayList<>();
        developers.add(developerFactory.getDeveloperBySpecialty("Java"));
        developers.add(developerFactory.getDeveloperBySpecialty("Java"));
        developers.add(developerFactory.getDeveloperBySpecialty("Java"));
        developers.add(developerFactory.getDeveloperBySpecialty("C++"));
        developers.add(developerFactory.getDeveloperBySpecialty("C++"));
        developers.add(developerFactory.getDeveloperBySpecialty("C++"));

        for (Developer developer : developers) {
            System.out.println(developer);
        }

    }
}