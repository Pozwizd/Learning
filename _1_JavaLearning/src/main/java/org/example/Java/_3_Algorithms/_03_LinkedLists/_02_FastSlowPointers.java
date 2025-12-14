package org.example.Java._3_Algorithms._03_LinkedLists;

/**
 * Техника Fast & Slow Pointers (Floyd's Cycle Detection)
 */
public class _02_FastSlowPointers {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    // Поиск цикла в списке
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;        // медленный на 1 шаг
            fast = fast.next.next;   // быстрый на 2 шага
            
            if (slow == fast) {
                return true; // встретились - есть цикл
            }
        }
        return false;
    }
    
    // Найти середину списка
    public static ListNode findMiddle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow; // slow будет в середине
    }
    
    // Разворот списка
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode current = head;
        
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        
        return prev; // новая голова
    }
    
    // Вспомогательный метод для создания списка
    private static ListNode createList(int[] values) {
        if (values.length == 0) return null;
        
        ListNode head = new ListNode(values[0]);
        ListNode current = head;
        
        for (int i = 1; i < values.length; i++) {
            current.next = new ListNode(values[i]);
            current = current.next;
        }
        
        return head;
    }
    
    // Вывод списка
    private static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
    
    public static void main(String[] args) {
        System.out.println("=== Fast & Slow Pointers ===\n");
        
        // Создаем список: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = createList(new int[]{1, 2, 3, 4, 5});
        
        System.out.println("Есть цикл? " + hasCycle(head));
        
        ListNode middle = findMiddle(head);
        System.out.println("Середина списка: " + middle.val);
        
        System.out.print("Оригинальный: ");
        printList(head);
        
        ListNode reversed = reverseList(head);
        System.out.print("Развернутый: ");
        printList(reversed);
    }
}
