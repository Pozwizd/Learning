package org.example.Java._1_GoFPatterns.Behavioral.Observer.Example;

import java.util.ArrayList;
import java.util.List;

interface Observer {
    void handleEvent(List<String> vacancies);
}

interface Observed {
    void addObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

class Subscriber implements Observer {
    String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void handleEvent(List<String> vacancies) {
        System.out.println("Dear " + name + "\nWe have some changes in vacancies:\n" + vacancies + "\n=======================================\n");
    }
}

class JavaDeveloperJobSite implements Observed {
    List<String> vacancies = new ArrayList<>();
    List<Observer> subscribers = new ArrayList<>();

    void addVacancy(String vacancy) {
        this.vacancies.add(vacancy);
        notifyObservers();
    }

    void removeVacancy(String vacancy) {
        this.vacancies.remove(vacancy);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.subscribers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.subscribers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : subscribers) {
            observer.handleEvent(this.vacancies);
        }
    }
}

public class Example {
    public static void main(String[] args) {
        JavaDeveloperJobSite jobSite = new JavaDeveloperJobSite();

        jobSite.addVacancy("First Java Position");
        jobSite.addVacancy("Second Java Position");

        Observer firstSubscriber = new Subscriber("Roman");
        Observer SecondSubscriber = new Subscriber("Vitya");

        jobSite.addObserver(firstSubscriber);
        jobSite.addObserver(SecondSubscriber);

        jobSite.addVacancy("Third Java Position");

        jobSite.removeVacancy("First Java Position");
    }
}
