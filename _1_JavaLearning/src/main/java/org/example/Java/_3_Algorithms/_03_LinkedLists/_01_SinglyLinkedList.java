package org.example.Java._3_Algorithms._03_LinkedLists;

/**
 * Реализация односвязного списка
 */
public class _01_SinglyLinkedList {
    
    static class ListNode {
        int val;
        ListNode next;
        
        ListNode(int val) {
            this.val = val;
        }
    }
    
    static class LinkedList {
        private ListNode head;
        
        // Добавление в начало - O(1)
        public void addFirst(int val) {
            ListNode newNode = new ListNode(val);
            newNode.next = head;
            head = newNode;
        }
        
        // Добавление в конец - O(n)
        public void addLast(int val) {
            ListNode newNode = new ListNode(val);
            
            if (head == null) {
                head = newNode;
                return;
            }
            
            ListNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        
        // Поиск - O(n)
        public boolean contains(int val) {
            ListNode current = head;
            while (current != null) {
                if (current.val == val) return true;
                current = current.next;
            }
            return false;
        }
        
        // Удаление узла - O(n)
        public void remove(int val) {
            if (head == null) return;
            
            if (head.val == val) {
                head = head.next;
                return;
            }
            
            ListNode current = head;
            while (current.next != null) {
                if (current.next.val == val) {
                    current.next = current.next.next;
                    return;
                }
                current = current.next;
            }
        }
        
        // Вывод списка
        public void display() {
            ListNode current = head;
            while (current != null) {
                System.out.print(current.val + " -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Односвязный список ===\n");
        
        LinkedList list = new LinkedList();
        
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.addFirst(0);
        
        System.out.print("Список: ");
        list.display();
        
        System.out.println("Содержит 2? " + list.contains(2));
        System.out.println("Содержит 5? " + list.contains(5));
        
        list.remove(2);
        System.out.print("После удаления 2: ");
        list.display();
    }
}
