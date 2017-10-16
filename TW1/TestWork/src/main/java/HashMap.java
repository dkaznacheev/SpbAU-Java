//impl put get entryset iterator


import java.util.*;

/**
 * Stores elements inside by their hash codes.
 * Automatically resizes if the size exceeds a certain fraction of capacity.
 **/
public class HashMap<K, V> extends AbstractMap<K, V> implements Iterable<Map.Entry<K, V>>{

    /**
     * Iterates over HashMap
     */
    public class HashMapIterator implements Iterator<Map.Entry<K, V>> {
        Node node;

        public HashMapIterator() {
            this.node = historyHead;
        }

        @Override
        public boolean hasNext() {
            return node.historyNext != null;
        }

        @Override
        public Entry<K, V> next() {
            Node result = node;
            node = node.historyNext;
            return result;
        }
    }

    @Override
    public Iterator<Entry<K, V>> iterator() {
        return new HashMapIterator();
    }

    /**
     * Subclass that describes the key-value pair.
     */
    public class Pair {
        public K key;
        public V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Subclass that represents the entry in the table
     */
    class Node implements Entry<K, V> {

        /**
         * The key-value pair inside the node.
         */
        Pair pair;

        /**
         * The next node.
         **/
        Node next;

        /**
         * The node added after this
         **/
        Node historyPrev;

        /**
         * The node added before this
         **/
        Node historyNext;

        Node (K key, V value) {
            this.pair = new Pair(key, value);
            this.next = null;
            this.historyNext = historyHead;
            this.historyPrev = null;
            historyHead = this;
        }

        @Override
        public K getKey() {
            return pair.key;
        }

        @Override
        public V getValue() {
            return pair.value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = pair.value;
            pair.value = v;
            return oldValue;
        }
    }

    public class List {

        /**
         * The first element of the list.
         **/
        private Node head;
        /**
         * Size of the list.
         **/
        private int size;

        public List() {
        }

        public List (K key, V value) {
            head = new Node(key, value);
            head.next = null;
            size = 1;
        }

        public int size() {
            return size;
        }
        /**
         * Returns the value of the node with given key.
         * @param key The key.
         * @return The value, null if there is no such key.
         **/
        public V get (K key) {
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
        public V put (K key, V value) {
            if (head == null) {
                head = new Node(key, value);
                size++;
                return null;
            }
            Node node = head;
            Node tail;
            do {
                if (node.pair.key.equals(key)) {
                    V oldValue = node.pair.value;
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
         * Removes node from history of added nodes
         * @param node node to remove
         */
        private void removeHistoryNode(Node node) {
            node.historyNext.historyPrev = node.historyPrev;
            if (node.historyPrev != null) {
                node.historyPrev.historyNext = node.historyNext;
            }
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
            removeHistoryNode(head);
            size--;
            return result;
        }

        /**
         * Returns the value stored with given key and removes the key-value pair from the list.
         * @param key The key.
         * @return The value stored, null if there was no value with this key.
         **/
        public V remove (K key) {
            if (head == null)
                return null;
            Node node = head;
            Node prev = null;
            do {
                if (node.pair.key.equals(key)) {
                    V oldValue = node.pair.value;
                    removeHistoryNode(node);
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
        public boolean contains (K key) {
            return (get(key) != null);
        }
    }

    /**
     * Last added element to the HashMap
      */
    Node historyHead;

    /**
     * Current number of elements in the table.
     **/
    private int size;

    /**
     * Current capacity of the table.
     **/
    private int capacity;

    /**
     * The storage, consists of Lists, which contain the elements.
     **/
    private ArrayList<List> storage;

    /**
     * The size-to-capacity ratio after which the resizing happens.
     **/
    private final double SIZE_PERCENT_LIMIT = 0.75;

    /**
     * Creates a HashMap with default capacity 4.
     */
    public HashMap() {
        this.capacity = 10;
        storage = new ArrayList<>(capacity);
    }

    public HashMap(int capacity) {
        storage = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    /**
     * Finds the position in the storage array by the key.
     * @param key The given key
     * @return The index in the array.
     **/
    public int getIndex (K key) {
        int hash = key.hashCode();
        return hash & (capacity - 1);
    }

    /**
     * Resizes the table.
     * @param newCapacity New capacity of the table.
     **/
    private void resize (int newCapacity) { //TODO REMAKE
        HashMap newTable = new HashMap(newCapacity);
        for (int i = 0; i < capacity; i++) {
            while (storage.get(i).size() > 0) {
                Pair pair = storage.get(i).popFront();
                newTable.put(pair.key, pair.value);
            }
        }
        this.storage = newTable.storage;
        this.capacity = newTable.capacity;
    }

    /**
     * Returns how many elements is in the table.
     * @return The table size.
     **/
    public int size () {
        return size;
    }

    /**
     * Gets value from HashMap by given key
     * @param o the key
     * @return value, null if not found
     */
    @Override
    public V get(Object o) {
        K key = (K) o;

        int index = getIndex(key);
        return storage.get(index).get(key);

    }

    /**
     * Puts key-value pair into HashMap
     * @param key key
     * @param value value
     * @return previous value with that key, null if there was no such key
     */
    @Override
    public V put(K key, V value) {
        int index = getIndex(key);
        if (!storage.get(index).contains(key))
            size++;
        V result = storage.get(index).put(key, value);
        if (size >= SIZE_PERCENT_LIMIT * capacity)
            resize(2 * capacity);
        return result;
    }

    /**
     * Removes the element with key
     * @param o key
     * @return value of removed element
     */
    @Override
    public V remove(Object o) {
        K key = (K) o;
        int index = getIndex(key);
        if (storage.get(index).contains(key))
            size--;
        return storage.get(index).remove(key);
    }

    /**
     * Checks if there is an element with the given key in the table.
     * @param key The given key.
     * @return Whether there is an element.
     **/
    public boolean contains (K key) {
        int index = getIndex(key);
        return storage.get(index).contains(key);
    }

    /**
     * Clears the table.
     **/
    public void clear() {
        storage.clear();
        size = 0;
    }

    /**
     * Set of all elements in HashMap
     */
    class HashSet extends AbstractSet<Entry<K, V>> {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new HashMapIterator();
        }

        @Override
        public int size() {
            return HashMap.this.size();
        }
    }

    /**
     * Returns set of all entries in HashMap
     * @return set of all entries in HashMap
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet();
    }

    public static void main(String[] args) {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(10, 20);
        hashMap.remove(10);
        System.out.println(hashMap.get(10));
    }

}