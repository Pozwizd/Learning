package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility;


interface RequestHandler {
    void setNext(RequestHandler handler);

    void handleRequest(Request request);
}

class Request {
    private boolean checkUpdates;
    private boolean restartRequired;

    public Request(boolean checkUpdates, boolean restartRequired) {
        this.checkUpdates = checkUpdates;
        this.restartRequired = restartRequired;
    }

    public boolean isCheckUpdates() {
        return checkUpdates;
    }

    public void setCheckUpdates(boolean checkUpdates) {
        this.checkUpdates = checkUpdates;
    }

    public boolean isRestartRequired() {
        return restartRequired;
    }

    public void setRestartRequired(boolean restartRequired) {
        this.restartRequired = restartRequired;
    }
}

class CheckUpdatesRequestHandler implements RequestHandler {
    Request request;
    private RequestHandler next;

    public void setNext(RequestHandler handler) {
        this.next = handler;
    }

    public void handleRequest(Request request) {
        if (request.isCheckUpdates()) {
            System.out.println("Обнову завезли, ура!!");
            next.handleRequest(request);
        } else {
            System.out.println("Обновы не будет, живем по старому");
        }
    }
}


class InstallUpdatesRequestHandler implements RequestHandler {
    Request request;
    private RequestHandler next;

    public void setNext(RequestHandler handler) {
        this.next = handler;
    }

    public void handleRequest(Request request) {
        if (request.isRestartRequired()) {
            System.out.println("Обновление прошло загружено и установлено, требуется перезагрузка");
            next.handleRequest(request);
        } else {
            System.out.println("Обновление прошло успешно, перезагрузка не требуется");
        }
    }
}

class RestartRequestHandler implements RequestHandler {
    private RequestHandler next;

    public void setNext(RequestHandler handler) {
        this.next = handler;
    }

    public void handleRequest(Request request) {
        System.out.println("Обновление прошло успешно");
    }
}


public class chainOfResponsibilityStart {
    public static void main(String[] args) {
        RequestHandler handler1 = new CheckUpdatesRequestHandler();
        RequestHandler handler2 = new InstallUpdatesRequestHandler();
        RequestHandler handler3 = new RestartRequestHandler();

        handler1.setNext(handler2);
        handler2.setNext(handler3);

        Request request = new Request(true, false);
        handler1.handleRequest(request);

    }
}