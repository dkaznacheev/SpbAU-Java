package ru.spbau.dkaznacheev;

public class HashTable {

    private int size;
    private int capacity;
    private List storage[];
    private final double SIZE_PERCENT_LIMIT = 0.75;

    public HashTable () {
        this(4);
    }

    public HashTable (int capacity) {
        this.capacity = capacity;
        storage = new List[capacity];
        for (int i = 0; i < capacity; i++)
            storage[i] = new List();
        size = 0;
    }

    private int getHash(String string) {
        int stringHash = 7;
        for (int i = 0; i < string.length(); i++) {
            stringHash = (stringHash * 31 + string.charAt(i));
        }
        return stringHash;
    }

    public int getIndex (String key) {
        int hash = getHash(key);
        return hash & (capacity - 1);
    }

    private void resize (int newCapacity) {
        HashTable newTable = new HashTable(newCapacity);
        for (int i = 0; i < capacity; i++) {
            while (storage[i].size() > 0) {
                List.Node node = storage[i].popFront();
                newTable.put(node.key, node.value);
            }
        }
        this.storage = newTable.storage;
        this.capacity = newTable.capacity;
    }

    public int size () {
        return size;
    }

    public boolean contains (String key) {
        int index = getIndex(key);
        return storage[index].contains(key);
    }

    public String put (String key, String value) {
        int index = getIndex(key);
        if (!storage[index].contains(key))
            size++;
        String result = storage[index].put(key,value);
        if (size >= SIZE_PERCENT_LIMIT * capacity)
            resize(2 * capacity);
        return result;
    }

    public String get (String key) {
        int index = getIndex(key);
        return storage[index].get(key);
    }

    public String remove (String key) {
        int index = getIndex(key);
        if (!storage[index].contains(key))
            size--;
        return storage[index].remove(key);
    }

    public void clear() {
        for (int i = 0; i < capacity; i++)
            storage[i] = new List();
        size = 0;
    }

}
