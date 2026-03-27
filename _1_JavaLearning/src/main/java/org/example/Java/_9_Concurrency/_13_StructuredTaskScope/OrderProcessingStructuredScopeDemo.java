package org.example.Java._9_Concurrency._13_StructuredTaskScope;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.StructuredTaskScope;

public class OrderProcessingStructuredScopeDemo {

    public static void main(String[] args) {
        try {
            OrderSummary summary = handleOrder(123L);
            System.out.println("Результат обработки заказа:");
            System.out.println("Заказы: " + summary.orders());
            System.out.println("Статус доставки: " + summary.deliveryStatus());
            System.out.println("Общая стоимость: " + summary.total());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Обработка была прервана");
        } catch (Exception e) {
            System.err.println("Ошибка при обработке заказа: " + e.getMessage());
        }
    }

    static OrderSummary handleOrder(Long orderId) throws Exception {
        try (var scope = StructuredTaskScope.<Object>open()) {
            var ordersTask = scope.fork(() -> getOrders(orderId));
            var statusTask = scope.fork(() -> checkDeliveryStatus(orderId));
            var totalTask = scope.fork(() -> calculateTotal(orderId));

            scope.join();

            return new OrderSummary(
                    cast(ordersTask.get()),
                    cast(statusTask.get()),
                    cast(totalTask.get())
            );
        }
    }

    private static <T> T cast(Object value) {
        return (T) value;
    }

    static List<String> getOrders(Long orderId) throws InterruptedException {
        Thread.sleep(150);
        return List.of("Заказ A-" + orderId, "Заказ B-" + orderId);
    }

    static String checkDeliveryStatus(Long orderId) throws InterruptedException {
        Thread.sleep(250);
        return "В пути";
    }

    static BigDecimal calculateTotal(Long orderId) throws InterruptedException {
        Thread.sleep(100);
        return new BigDecimal("1500.00");
    }

    record OrderSummary(List<String> orders, String deliveryStatus, BigDecimal total) {
    }
}
