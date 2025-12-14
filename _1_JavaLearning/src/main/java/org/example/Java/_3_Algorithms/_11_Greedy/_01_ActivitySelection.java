package org.example.Java._3_Algorithms._11_Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Жадный алгоритм: Выбор активностей
 */
public class _01_ActivitySelection {
    
    static class Activity {
        int start;
        int end;
        
        Activity(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public String toString() {
            return "[" + start + ", " + end + "]";
        }
    }
    
    // Выбрать максимальное количество непересекающихся активностей
    public static List<Activity> selectActivities(Activity[] activities) {
        // Сортируем по времени окончания
        Arrays.sort(activities, Comparator.comparingInt(a -> a.end));
        
        List<Activity> selected = new ArrayList<>();
        selected.add(activities[0]); // Берем первую
        
        int lastEnd = activities[0].end;
        
        for (int i = 1; i < activities.length; i++) {
            // Если активность начинается после окончания предыдущей
            if (activities[i].start >= lastEnd) {
                selected.add(activities[i]);
                lastEnd = activities[i].end;
            }
        }
        
        return selected;
    }
    
    public static void main(String[] args) {
        System.out.println("=== Activity Selection (Greedy) ===\n");
        
        Activity[] activities = {
            new Activity(1, 4),
            new Activity(3, 5),
            new Activity(0, 6),
            new Activity(5, 7),
            new Activity(3, 9),
            new Activity(5, 9),
            new Activity(6, 10),
            new Activity(8, 11),
            new Activity(8, 12),
            new Activity(2, 14),
            new Activity(12, 16)
        };
        
        System.out.println("Все активности:");
        for (Activity a : activities) {
            System.out.println(a);
        }
        
        List<Activity> selected = selectActivities(activities);
        
        System.out.println("\nВыбранные активности (макс количество):");
        for (Activity a : selected) {
            System.out.println(a);
        }
        
        System.out.println("\nВсего выбрано: " + selected.size());
        System.out.println("Сложность: O(n log n) из-за сортировки");
    }
}
