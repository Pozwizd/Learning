package xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringLearningApp {
    public static void main(String[] args) {

        //==============================================================================================================

        // 1. Создаем Spring контейнер, загружая конфигурацию из XML
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        // 2. Получаем бин из контейнера по его id
        MessageService service = context.getBean("messageService", MessageService.class);

        // 3. Используем бин
        System.out.println(service.getMessage());

        //==============================================================================================================

        ApplicationContext context2 = new ClassPathXmlApplicationContext("applicationContext.xml");

        // Получаем из контейнера уже полностью готовый и настроенный объект
        MessagePrinter printer = context2.getBean("messagePrinter", MessagePrinter.class);

        // Вызываем его метод
        printer.printMessage(); // Он, в свою очередь, вызовет метод у внедренного MessageService

    }
}
