package ru.spbau.dkaznacheev;

/**
* Stores elements of java.lang.String inside by their hash codes.
* Automatically resizes if the size exceeds a certain fraction of capacity.
 **/
public class HashTable {
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
    private List storage[];

    /**
     * The size-to-capacity ratio after which the resizing happens.
     **/
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

    /**
     * Returns the hash code of the given string.
     * @param string The string that gets hashed.
     * @return The hash.
     **/
    private int getHash(String string) {
        int stringHash = 7;
        for (int i = 0; i < string.length(); i++) {
            stringHash = (stringHash * 31 + string.charAt(i));
        }
        return stringHash;
    }

    /**
     * Finds the position in the storage array by the key.
     * @param key The given key
     * @return The index in the array.
     **/
    public int getIndex (String key) {
        int hash = getHash(key);
        return hash & (capacity - 1);
    }

    /**
     * Resizes the table.
     * @param newCapacity New capacity of the table.
     **/
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

    /**
     * Returns how many elements is in the table.
     * @return The table size.
     **/
    public int size () {
        return size;
    }

    /**
     * Checks if there is an element with the given key in the table.
     * @param key The given key.
     * @return Whether there is an element.
     **/
    public boolean contains (String key) {
        int index = getIndex(key);
        return storage[index].contains(key);
    }

    /**
     * Changes or adds the value of the given key, returns the previous value.
     * @param key The key
     * @param value The new value
     * @return Previous value, null if there was no value with this key.
     **/
    public String put (String key, String value) {
        int index = getIndex(key);
        if (!storage[index].contains(key))
            size++;
        String result = storage[index].put(key,value);
        if (size >= SIZE_PERCENT_LIMIT * capacity)
            resize(2 * capacity);
        return result;
    }

    /**
     * Returns the value stored with given key.
     * @param key The key.
     * @return The value stored, null if there was no value with this key.
     **/
    public String get (String key) {
        int index = getIndex(key);
        return storage[index].get(key);
    }
    /**
     * Returns the value stored with given key and removes the key-value pair from the table.
     * @param key The key.
     * @return The value stored, null if there was no value with this key.
     **/
    public String remove (String key) {
        int index = getIndex(key);
        if (!storage[index].contains(key))
            size--;
        return storage[index].remove(key);
    }
    /**
     * Clears the table.
     **/
    public void clear() {
        for (int i = 0; i < capacity; i++)
            storage[i] = new List();
        size = 0;
    }

}
