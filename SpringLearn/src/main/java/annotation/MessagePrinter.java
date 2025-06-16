package annotation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component // И это тоже бин
public class MessagePrinter {

    private final MessageService service;

    @Autowired // Говорим Spring: "Найди бин типа MessageService и передай его в этот конструктор"
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(service.getMessage());
    }
}