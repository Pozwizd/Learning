package org.example.Java._8_Concurrency.Threads.Task;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Example4 {
    public static void main(String[] args) {
        List<String> concurrentList = new CopyOnWriteArrayList<>();

        concurrentList.add("Example1");
        concurrentList.add("Example2");
        concurrentList.add("Example3");

        for (String item : concurrentList) {
            System.out.println(item);
        }

        Map<String, String> concurrentMap = new ConcurrentHashMap<>();

        concurrentMap.put("key1", "value1");
        concurrentMap.put("key2", "value2");
        concurrentMap.put("key3", "value3");

        String value = concurrentMap.get("key1");
        System.out.println("Value for key1: " + value);
    }
}
