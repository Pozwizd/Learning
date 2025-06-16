package xml;

public class MessagePrinter {

    // Зависимость, которую Spring должен внедрить
    private final MessageService service;

    // Конструктор, через который Spring будет внедрять зависимость
    public MessagePrinter(MessageService service) {
        this.service = service;
    }

    public void printMessage() {
        System.out.println(service.getMessage());
    }
}