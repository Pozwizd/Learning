package org.example.Java._1_GoFPatterns.Behavioral.TemplateMethod;

class WelcomePage extends WebSiteTemplate {

    @Override
    void showPageContent() {
        System.out.println("Welcome");
    }
}

class NewsPage extends WebSiteTemplate {
    @Override
    void showPageContent() {
        System.out.println("News");
    }
}

abstract class WebSiteTemplate {
    void showPage() {
        System.out.println("Header");
        showPageContent();
        System.out.println("Footer");
    }

    abstract void showPageContent();
}

public class Example {
    public static void main(String[] args) {
        WelcomePage welcomePage = new WelcomePage();
        WebSiteTemplate newsPage = new NewsPage();

        welcomePage.showPage();

        System.out.println("===================================================");

        newsPage.showPage();

    }
}
