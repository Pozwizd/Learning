package org.example.Java._1_GoFPatterns.Behavioral.chainOfResponsibility.Example1;

/**
 * Класс {@code CheckUpdatesRequestHandler} является конкретным обработчиком
 * в цепочке обязанностей. Он отвечает за проверку наличия обновлений.
 * Если обновления требуются ({@code request.isCheckUpdates()} возвращает {@code true}),
 * он выводит сообщение и передает запрос следующему обработчику в цепочке.
 * В противном случае, он выводит другое сообщение и не передает запрос дальше,
 * завершая цепочку обработки для данного условия.
 */
public class CheckUpdatesRequestHandler implements RequestHandler {
    /**
     * Ссылка на следующий обработчик в цепочке.
     * Если текущий обработчик не может обработать запрос или должен передать его дальше,
     * он использует эту ссылку.
     */
    private RequestHandler next;

    /**
     * Устанавливает следующего обработчика в цепочке.
     *
     * @param handler Следующий обработчик запроса.
     */
    @Override
    public void setNext(RequestHandler handler) {
        this.next = handler;
    }

    /**
     * Обрабатывает запрос, проверяя необходимость обновлений.
     * Если обновления требуются, выводит сообщение и передает запрос следующему обработчику.
     * В противном случае, выводит сообщение об отсутствии обновлений.
     *
     * @param request Объект запроса, который необходимо обработать.
     */
    @Override
    public void handleRequest(Request request) {
        if (request.isCheckUpdates()) {
            System.out.println("Обнову завезли, ура!!");
            // Важно: если есть следующий обработчик, передаем запрос ему.
            // В реальных сценариях здесь может быть проверка на null,
            // чтобы избежать NullPointerException, если цепочка может закончиться здесь.
            if (next != null) {
                next.handleRequest(request);
            }
        } else {
            System.out.println("Обновы не будет, живем по старому");
        }
    }
}