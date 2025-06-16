package org.example.Java._1_GoFPatterns.Behavioral.Memento;

import java.util.Date;

class Project {
    private String version;
    private Date date;

    void setVersionAndDate(String version) {
        this.version = version;
        this.date = new Date();
    }

    Save save() {
        return new Save(version);
    }

    void load(Save save) {
        version = save.getVersion();
        date = save.getDate();
    }

    @Override
    public String toString() {
        return "Project:\n" +
                "\nversion='" + version + '\n' +
                "\ndate=" + date + '\n';
    }
}

class Save {
    private final String version;
    private final Date date;

    public Save(String version) {
        this.version = version;
        this.date = new Date();
    }

    public String getVersion() {
        return version;
    }

    public Date getDate() {
        return date;
    }
}

class GitHubRepo {
    private Save save;

    public Save getSave() {
        return save;
    }

    public void setSave(Save save) {
        this.save = save;
    }
}


public class Example {
    public static void main(String[] args) throws InterruptedException {
        Project project = new Project();
        GitHubRepo gitHub = new GitHubRepo();

        System.out.println("Creating new project. Version 1.0");
        project.setVersionAndDate("Version 1.0");
        System.out.println(project);
        System.out.println("Saving current version to github...");
        gitHub.setSave(project.save());
        System.out.println("Updating project to Version 1.1");
        System.out.println("Writing poor code...");
        Thread.sleep(5000);
        project.setVersionAndDate("Version 1.1");
        System.out.println(project);
        System.out.println("Something  went wrong...");
        System.out.println("Rolling back to Version 1.0");
        project.load(gitHub.getSave());
        System.out.println("Project after roolback:");
        System.out.println(project);
    }
}
