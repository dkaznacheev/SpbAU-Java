package ru.spbau.dkaznacheev;

import com.sun.org.apache.bcel.internal.generic.LUSHR;

/**
 * Linked list that stores elements of java.lang.String.
 **/
public class List {

    /**
     * Subclass that describes the key-value pair.
     */
    public static class Pair {

        /**
         * Key of pair
         */
        private String key;

        /**
         * Value of pair
         */
        private String value;

        /**
         * Creates key-value pair
         * @param key key of pair
         * @param value value of pair
         */
        public Pair(String key, String value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Returns key
         * @return key
         */
        public String getKey() {
            return key;
        }

        /**
         * Returns value
         * @return value
         */
        public String getValue() {
            return value;
        }

        /**
         * Sets key
         * @param key the new key
         */
        public void setKey(String key) {
            this.key = key;
        }

        /**
         * Sets value
         * @param value the new value
         */
        public void setValue(String value) {
            this.value = value;
        }

    }

    /**
     * Node Subclass that describes the list's node.
     **/
    private class Node {

        /**
         * The key-value pair inside the node.
         */
        private Pair pair;

        /**
         * The next node.
         **/
        private Node next;

        /**
         * Creates a node with key and value
         * @param key key of node
         * @param value value of node
         */
        private Node (String key, String value) {
            this.pair = new Pair(key, value);
            this.next = null;
        }
    }

    /**
     * The first element of the list.
     **/
    private Node head;

    /**
     * Size of the list.
     **/
    private int size;

    /**
     * Creates empty list
     */
    public List() { }

    /**
     * Creates List with one node
     * @param key key of node
     * @param value value of node
     */
    public List (String key, String value) {
        head = new Node(key, value);
        head.next = null;
        size = 1;
    }

    /**
     * Returns the size of list.
     * @return size of list
     **/
    public int size() {
        return size;
    }

    /**
     * Returns the value of the node with given key.
     * @param key The key.
     * @return The value, null if there is no such key.
     **/
    public String get (String key) {
        if (head == null)
            return null;
        Node node = head;
        do {
            if (node.pair.key.equals(key))
                return node.pair.value;
            node = node.next;
        } while (node != null);
        return null;
    }

    /**
     * Changes or adds the value of the given key, returns the previous value.
     * @param key The key
     * @param value The new value
     * @return Previous value, null if there was no value with this key.
     **/
    public String put (String key, String value) {
        if (head == null) {
            head = new Node(key, value);
            size++;
            return null;
        }
        Node node = head;
        Node tail;
        do {
            if (node.pair.key.equals(key)) {
                String oldValue = node.pair.value;
                node.pair.value = value;
                return oldValue;
            }
            tail = node;
            node = node.next;
        } while (node != null);
        tail.next = new Node(key, value);
        size++;
        return null;
    }

    /**
     * Returns the first key-value pair of the list, and then deletes it.
     * @return First node, or null if the list is empty.
     **/
    public Pair popFront () {
        if (head == null)
            return null;
        Pair result = head.pair;
        head = head.next;
        size--;
        return result;
    }

    /**
     * Returns the value stored with given key and removes the key-value pair from the list.
     * @param key The key.
     * @return The value stored, null if there was no value with this key.
     **/
    public String remove (String key) {
        if (head == null)
            return null;
        Node node = head;
        Node prev = null;
        do {
            if (node.pair.key.equals(key)) {
                String oldValue = node.pair.value;
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

    /**
     * Checks if there is an element with the given key in the list.
     * @param key The given key.
     * @return Whether there is an element.
     **/
    public boolean contains (String key) {
        return (get(key) != null);
    }
}
