package ru.spbau.dkaznacheev;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class HashTableTest {
    @Test
    public void emptyHashTableSize() throws Exception {
        HashTable table = new HashTable(4);
        assertEquals(0, table.size());
    }


    @Test
    public void sizeChangesOnAdd() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        assertEquals(1, table.size());
        table.put("2", "v2");
        assertEquals(2, table.size());
        table.put("3", "v3");
        assertEquals(3, table.size());
        table.put("4", "v4");
        assertEquals(4, table.size());
    }

    @Test
    public void containsAdded() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        assertTrue(table.contains("1"));
        assertTrue(!table.contains("2"));
    }

    @Test
    public void containsKey() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        assertEquals(1, table.size());
        assertTrue(table.contains("1"));
        assertTrue(!table.contains("v1"));
        String result = table.put("1", "v11");
        assertTrue(result.equals("v1"));
        assertEquals(1, table.size());
    }


    @Test
    public void getAdded() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        assertTrue(table.get("1").equals("v1"));
        assertTrue(table.get("2").equals("v2"));
    }

    @Test
    public void getNotExisting() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        assertEquals(null, table.get("3"));
    }

    @Test
    public void removeChangesSize() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        table.remove("1");
        assertEquals(1, table.size());
        table.remove("3");
        assertEquals(1, table.size());
    }

    @Test
    public void removedElements() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        String result = table.remove("1");
        assertTrue(result.equals("v1"));
        assertTrue(!table.contains("1"));
        assertTrue(table.contains("2"));
        result = table.remove("3");
        assertNull(result);
    }

    @Test
    public void clearTable() throws Exception {
        HashTable table = new HashTable(4);
        table.put("1", "v1");
        table.put("2", "v2");
        table.clear();
        assert(table.size() == 0);
        assert(!table.contains("1"));
        assert(!table.contains("2"));
    }

}