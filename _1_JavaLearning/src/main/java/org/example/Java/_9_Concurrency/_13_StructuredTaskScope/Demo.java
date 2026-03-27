package org.example.Java._9_Concurrency._13_StructuredTaskScope;

import java.util.concurrent.StructuredTaskScope;


public class Demo {
    static void main(String[] args) throws Exception {
        try (StructuredTaskScope<Object, Void> scope = StructuredTaskScope.open(
                StructuredTaskScope.Joiner.awaitAllSuccessfulOrThrow())) {

            StructuredTaskScope.Subtask<String> userTask = scope.fork(Demo::loadUser);
            StructuredTaskScope.Subtask<String> ordersTask = scope.fork(Demo::loadOrders);
            StructuredTaskScope.Subtask<String> notificationsTask = scope.fork(Demo::loadNotifications);

            scope.join();

            String user = userTask.get();
            String orders = ordersTask.get();
            String notifications = notificationsTask.get();

            System.out.println(user);
            System.out.println(orders);
            System.out.println(notifications);
            System.out.println("Done");
        }
    }

    static String loadUser() {
        return "User";
    }

    static String loadOrders() {
        return "Orders";
    }

    static String loadNotifications() {
        return "Notifications";
    }
}