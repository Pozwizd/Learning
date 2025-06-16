package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility;

abstract class Notifier {
    private final int priority;
    private Notifier nextNotifier;

    public Notifier(int priority) {
        this.priority = priority;
    }

    public void setNextNotifier(Notifier nextNotifier) {
        this.nextNotifier = nextNotifier;
    }

    void notifyManager(String message, int level) {
        if (level >= priority) {
            write(message);
        }
        if (nextNotifier != null) {
            nextNotifier.notifyManager(message, level);
        }
    }

    abstract void write(String message);
}

class Priority {
    static final int ROUTINE = 1;
    static final int IMPORTANT = 2;
    static final int ASAP = 3;
}

class SimpleReportNotifier extends Notifier {
    public SimpleReportNotifier(int priority) {
        super(priority);
    }

    @Override
    void write(String message) {
        System.out.println("Notifying using simple report: " + message);
    }
}

class EmailNotifier extends Notifier {

    public EmailNotifier(int priority) {
        super(priority);
    }

    @Override
    void write(String message) {
        System.out.println("Sending email: " + message);
    }
}

class SMSNotifier extends Notifier {

    public SMSNotifier(int priority) {
        super(priority);
    }

    @Override
    void write(String message) {
        System.out.println("Sending SMS to manager: " + message);
    }
}


public class Example {
    public static void main(String[] args) {
        Notifier repornNotifier = new SimpleReportNotifier(Priority.ROUTINE);
        Notifier emailNotifier = new EmailNotifier(Priority.IMPORTANT);
        Notifier SMSNotifier = new SMSNotifier(Priority.ASAP);

        repornNotifier.setNextNotifier(emailNotifier);
        emailNotifier.setNextNotifier(SMSNotifier);

        repornNotifier.notifyManager("Everything is OK", Priority.ROUTINE);

        repornNotifier.notifyManager("Houston, we've had a problem here", Priority.ASAP);
    }
}
