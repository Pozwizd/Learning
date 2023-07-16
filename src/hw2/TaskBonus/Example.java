package hw2.TaskBonus;

public class Example {
    public static void main(String[] args) {

        MyArrayList<Integer> myArrayList = new MyArrayList<>();
        for (int i = 0; i < 20; i++) {
            myArrayList.add(i);
        }
        myArrayList.size();
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i) + " ");
        }
        myArrayList.add(10, 99);
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i) + " ");
        }
        myArrayList.add(99, 98);
        for (int i = 0; i < myArrayList.size(); i++) {
            System.out.println(myArrayList.get(i) + " ");
        }
        System.out.println("================================");

        MyLinkedList<Integer> myLinkedList = new MyLinkedList<>();
        for (int i = 0; i < 20; i++) {
            myLinkedList.add(i);
        }
        myLinkedList.size();
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i) + " ");
        }
        myLinkedList.add(10, 99);
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i) + " ");
        }
        myArrayList.add(99, 98);
        for (int i = 0; i < myLinkedList.size(); i++) {
            System.out.println(myLinkedList.get(i) + " ");
        }
        System.out.println("================================");

        MyHashMap<Integer, String> myHashMap = new MyHashMap<>();
        myHashMap.put(1, "First");
        myHashMap.put(2, "Second");
        myHashMap.put(3, "Third");

        System.out.println(myHashMap.get(1));
        for (int i = 0; i < myHashMap.size(); i++) {
            Integer[] a = myHashMap.keyArray();
            System.out.println((int) a[i]);
        }
    }
}
