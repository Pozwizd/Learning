package org.example.Java._1_GoFPatterns.Behavioral.State;

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

    void changeActivity() {
        if (activity instanceof Sleeping) {
            setActivity(new Training());
        } else if (activity instanceof Training) {
            setActivity(new Coding());
        } else if (activity instanceof Coding) {
            setActivity(new Reading());
        } else if (activity instanceof Reading) {
            setActivity(new Sleeping());
        }
    }

    void justDoIt() {
        activity.justDoit();
    }
}

public class Example {
    public static void main(String[] args) {
        Activity activity = new Sleeping();
        Developer developer = new Developer();

        developer.setActivity(activity);

        for (int i = 0; i < 10; i++) {
            developer.justDoIt();
            developer.changeActivity();
        }
    }
}
