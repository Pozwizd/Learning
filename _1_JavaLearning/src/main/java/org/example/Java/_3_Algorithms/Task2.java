package org.example.Java._3_Algorithms;

import java.util.LinkedList;
import java.util.Queue;

public class Task2 {
    private Node root;

    private static class Node {
        char value;
        Node left;
        Node right;

        Node(char value) {
            this.value = value;
        }
    }


    public void add(char value) {
        root = addRecursive(root, value);
    }


    private Node addRecursive(Node current, char value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            return current;
        }

        return current;
    }


    public void traverseInOrder() {
        traverseInOrderRecursive(root);
        System.out.println();
    }

    private void traverseInOrderRecursive(Node node) {
        if (node != null) {
            traverseInOrderRecursive(node.left);
            System.out.print(node.value + " ");
            traverseInOrderRecursive(node.right);
        }
    }


    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(node.value + " ");

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Task2 binaryTree = new Task2();
        binaryTree.add('3');
        binaryTree.add('3');
        binaryTree.add('9');
        binaryTree.add('4');
        binaryTree.add('1');
        binaryTree.add('7');
        binaryTree.add('6');
        binaryTree.add('5');

        System.out.println("Рекурсивный обход в глубину:");
        binaryTree.traverseInOrder();

        System.out.println("Итеративный обход в ширину:");
        binaryTree.traverseLevelOrder();
    }
}