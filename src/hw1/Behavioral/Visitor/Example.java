package hw1.Behavioral.Visitor;

interface ProjectElement {
    void beWritten(Developer developer);

}

interface Developer {
    void create(ProjectClass projectClass);

    void create(Database database);

    void create(Test test);
}

class ProjectClass implements ProjectElement {

    @Override
    public void beWritten(Developer developer) {
        developer.create(this);
    }
}

class Database implements ProjectElement {

    @Override
    public void beWritten(Developer developer) {
        developer.create(this);
    }
}

class Test implements ProjectElement {

    @Override
    public void beWritten(Developer developer) {
        developer.create(this);
    }
}

class JuniorDeveloper implements Developer {

    @Override
    public void create(ProjectClass projectClass) {
        System.out.println("Writing poor class");
    }

    @Override
    public void create(Database database) {
        System.out.println("Drop database...");
    }

    @Override
    public void create(Test test) {
        System.out.println("Creating not reliable test...");
    }
}

class SeniorDeveloper implements Developer {

    @Override
    public void create(ProjectClass projectClass) {
        System.out.println("Rewriting class after Junior");
    }

    @Override
    public void create(Database database) {
        System.out.println("Fixing database");
    }

    @Override
    public void create(Test test) {
        System.out.println("Creating reliable test...");
    }
}

class Project implements ProjectElement {
    ProjectElement[] projectElements;

    public Project() {
        this.projectElements = new ProjectElement[]{
                new ProjectClass(),
                new Database(),
                new Test()
        };
    }

    @Override
    public void beWritten(Developer developer) {
        for (ProjectElement element : projectElements) {
            element.beWritten(developer);
        }
    }
}

public class Example {
    public static void main(String[] args) {
        Project project = new Project();

        Developer junior = new JuniorDeveloper();
        Developer senior = new SeniorDeveloper();

        System.out.println("Junior in Action...");
        project.beWritten(junior);

        System.out.println("========================================================================");
        System.out.println("Senior in Action...");
        project.beWritten(senior);

    }
}
