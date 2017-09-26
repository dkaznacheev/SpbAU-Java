package ru.spbau.dkaznacheev;
import org.junit.Test;

public class HashTableTest {
    @Test
    public void size() throws Exception {
        HashTable table = new HashTable(4);
        assert(table.size() == 0);
        table.put("1", "v1");
        assert(table.size() == 1);
        table.put("2", "v2");
        assert(table.size() == 2);
        table.put("3", "v3");
        assert(table.size() == 3);
        table.put("4", "v4");
        assert(table.size() == 4);
    }

    @Test
    public void contains() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        assert(table.contains("1"));
        assert(!table.contains("2"));
    }

    @Test
    public void put() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        assert(table.size() == 1);
        assert(table.contains("1"));
        assert(!table.contains("v1"));
        String result = table.put("1", "v11");
        assert(result.equals("v1"));
        assert(table.size() == 1);
    }

    @Test
    public void get() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        assert(table.get("1").equals("v1"));
        assert(table.get("2").equals("v2"));
        assert(table.get("3") == null);
    }

    @Test
    public void remove() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        String result = table.remove("1");
        assert(result.equals("v1"));
        assert(!table.contains("1"));
        assert(table.contains("2"));
        assert(table.size() == 1);
        result = table.remove("3");
        assert(result == null);
        assert(table.size() == 1);
    }

    @Test
    public void clear() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        table.clear();
        assert(table.size() == 0);
        assert(!table.contains("1"));
        assert(!table.contains("2"));
    }

}