package ru.spbau.dkaznacheev;

public class List {

    class Node {
        String key;
        String value;
        Node next;
        Node (String key, String value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node head;
    private int size;

    public List () {
        size = 0;
        head = null;
    }

    public List (String key, String value) {
        head = new Node(key, value);
        head.next = null;
        size = 1;
    }

    public int size() {
        return size;
    }

    public String get (String key) {
        if (head == null)
            return null;
        Node node = head;
        do {
            if (node.key.equals(key))
                return node.value;
            node = node.next;
        } while (node != null);
        return null;
    }

    public String put (String key, String value) {
        if (head == null) {
            head = new Node(key, value);
            size++;
            return null;
        }
        Node node = head;
        Node tail;
        do {
            if (node.key.equals(key)) {
                String oldValue = node.value;
                node.value = value;
                return oldValue;
            }
            tail = node;
            node = node.next;
        } while (node != null);
        tail.next = new Node(key, value);
        size++;
        return null;
    }

    public Node popFront () {
        if (head == null)
            return null;
        Node result = head;
        head = head.next;
        size--;
        return result;
    }

    public String remove (String key) {
        if (head == null)
            return null;
        Node node = head;
        Node prev = null;
        do {
            if (node.key.equals(key)) {
                String oldValue = node.value;
                if (prev != null) {
                    prev.next = node.next;
                } else {
                    head = node.next;
                }
                size--;
                return oldValue;
            }
            prev = node;
            node = node.next;
        } while (node != null);
        return null;
    }

    public boolean contains (String key) {
        return (get(key) != null);
    }
}
