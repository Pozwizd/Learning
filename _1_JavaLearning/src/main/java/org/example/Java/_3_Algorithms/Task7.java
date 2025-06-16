package org.example.Java._3_Algorithms;

import java.util.*;

public class Task7 {
    private final Map<String, Map<String, Integer>> graph;
    private Set<String> visited;
    private Map<String, Integer> distances;
    private Map<String, String> parents;

    public Task7(Map<String, Map<String, Integer>> graph) {
        this.graph = graph;
    }

    private String getMinimum(Map<String, Integer> distances) {
        String minVertex = null;
        for (String vertex : distances.keySet()) {
            if (visited.contains(vertex)) {
                continue;
            }
            if (minVertex == null || distances.get(vertex) < distances.get(minVertex)) {
                minVertex = vertex;
            }
        }
        return minVertex;
    }

    public void run(String startVertex) {

        visited = new HashSet<>();
        distances = new HashMap<>();
        parents = new HashMap<>();
        for (String vertex : graph.keySet()) {
            distances.put(vertex, vertex.equals(startVertex) ? 0 : Integer.MAX_VALUE);
        }


        for (int i = 0; i < graph.size(); i++) {

            String currentVertex = getMinimum(distances);

            visited.add(currentVertex);

            Map<String, Integer> neighbors = graph.get(currentVertex);

            if (neighbors == null) {
                continue;
            }

            for (String neighbor : neighbors.keySet()) {
                int tentativeDistance = distances.get(currentVertex) + neighbors.get(neighbor);
                if (tentativeDistance < distances.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    distances.put(neighbor, tentativeDistance);
                    parents.put(neighbor, currentVertex);
                }
            }
        }
    }

    public List<String> getPath(String endVertex) {
        List<String> path = new ArrayList<>();
        path.add(endVertex);
        while (parents.containsKey(endVertex)) {
            endVertex = parents.get(endVertex);
            path.add(0, endVertex);
        }
        return path;
    }


    public int getDistance(String endVertex) {
        return distances.get(endVertex);
    }


    public static void main(String[] args) {

        Map<String, Map<String, Integer>> graph = new HashMap<>();
        graph.put("Одесса", Map.of("Миколаїв", 132, "Умань", 271));
        graph.put("Миколаїв", Map.of("Херсон", 71, "Кривий Ріг", 204));
        graph.put("Умань", Map.of("Кропивницький", 167, "Київ", 212, "Вінниця", 160));
        graph.put("Кривий Ріг", Map.of("Дніпро", 146));
        graph.put("Кропивницький", Map.of("Кривий Ріг", 119, "Дніпро", 245));
        graph.put("Дніпро", Map.of("Харків", 216));
        graph.put("Харків", Map.of("Суми", 183));
        graph.put("Полтава", Map.of("Харків", 143));
        graph.put("Київ", Map.of("Полтава", 342, "Житомир", 140));
        graph.put("Житомир", Map.of("Рівне", 188));
        graph.put("Вінниця", Map.of("Хмельницький", 122));
        graph.put("Хмельницький", Map.of("Тернопіль", 111));
        graph.put("Тернопіль", Map.of("Львів", 127, "Рівне", 159));
        graph.put("Рівне", Map.of("Львів", 121));


        Task7 dijkstra = new Task7(graph);
        dijkstra.run("Одесса");


        List<String> path = dijkstra.getPath("Суми");
        int distance = dijkstra.getDistance("Суми");

        System.out.println("Path: " + path);
        System.out.println("Distance: " + distance);
    }
}