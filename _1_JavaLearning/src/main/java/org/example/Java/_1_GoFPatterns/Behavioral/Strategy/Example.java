package org.example.Java._1_GoFPatterns.Behavioral.Strategy;


interface Activity {
    void justDoit();
}

class Coding implements Activity {

    @Override
    public void justDoit() {
        System.out.println("Writing code...");
    }
}

class Reading implements Activity {

    @Override
    public void justDoit() {
        System.out.println("Reading book...");
    }
}

class Sleeping implements Activity {

    @Override
    public void justDoit() {
        System.out.println("Sleeping...");
    }
}

class Training implements Activity {

    @Override
    public void justDoit() {
        System.out.println("Training...");
    }
}

class Developer {
    Activity activity;

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    void executeActivity() {
        activity.justDoit();
    }

    void justDoIt() {
        activity.justDoit();
    }
}

public class Example {
    public static void main(String[] args) {
        Developer developer = new Developer();

        developer.setActivity(new Sleeping());
        developer.executeActivity();

        developer.setActivity(new Training());
        developer.executeActivity();

        developer.setActivity(new Coding());
        developer.executeActivity();

        developer.setActivity(new Reading());
        developer.executeActivity();
    }
}
