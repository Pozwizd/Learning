package hw1.Structural.Proxy;

interface Project {
    void run();
}

class RealProjeck implements Project {

    private final String url;

    public RealProjeck(String url) {
        this.url = url;
        load();
    }

    public void load() {
        System.out.println("Loading project from" + url + "...");
    }

    @Override
    public void run() {
        System.out.println("Running project" + url + "...");
    }
}

class ProxyProject implements Project {
    private final String url;
    private RealProjeck realProjeck;

    public ProxyProject(String url) {
        this.url = url;
    }

    @Override
    public void run() {
        if (realProjeck == null) {
            realProjeck = new RealProjeck(url);
        }
        realProjeck.run();
    }
}


public class Example {


    public static void main(String[] args) {
        Project project = new ProxyProject("https://github.com/reworkd/AgentGPT");
        project.run();

    }
}
