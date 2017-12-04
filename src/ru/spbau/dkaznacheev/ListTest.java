package ru.spbau.dkaznacheev;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {
    @Test
    public void sizeChangesOnAdd() throws Exception {
        List list = new List();
        assertEquals (0, list.size());
        list.put("1", "v1");
        assertEquals (1, list.size());
        list.put("2", "v2");
        assertEquals (2, list.size());
        list.put("2", "v3");
        assertEquals (2, list.size());
    }

    @Test
    public void getTest() throws Exception {
        List list = new List("1", "v1");
        assertTrue(list.get("1").equals("v1"));
        assertNull(list.get("2"));
    }

    @Test
    public void putTest() throws Exception {
        List list = new List();
        list.put("1", "v1");
        assertEquals(1, list.size());
        assertTrue(list.contains("1"));
        assertTrue(!list.contains("v1"));
        String result = list.put("1", "v11");
        assertTrue(result.equals("v1"));
        assertEquals(1, list.size());
    }

    @Test
    public void popFrontTest() throws Exception {
        List list = new List();
        list.put("1", "v1");
        list.put("2", "v2");
        List.Pair pair = list.popFront();
        assertTrue(pair.getKey().equals("1"));
        assertTrue(pair.getValue().equals("v1"));
        assertEquals(1, list.size());
        assertTrue(!list.contains("1"));
        pair = list.popFront();
        assertTrue(pair.getKey().equals("2"));
        assertTrue(pair.getValue().equals("v2"));
        assertEquals(0, list.size());
        pair = list.popFront();
        assertNull(pair);
    }

    @Test
    public void removeTest() throws Exception {
        List list = new List();
        list.put("1", "v1");
        list.put("2", "v2");
        list.put("3", "v3");
        String result = list.remove("2");
        assertTrue(result.equals("v2"));
        assertTrue(!list.contains("2"));
        assertEquals(2, list.size());
        result = list.remove("4");
        assertNull(result);
        assertEquals(2, list.size());
        list.remove("1");
        list.remove("3");
        assertEquals(0, list.size());
        result = list.remove("1");
        assertNull(result);
        assertEquals(0, list.size());
    }

    @Test
    public void containsAdded() throws Exception {
        List list = new List();
        list.put("1", "v1");
        list.put("2", "v2");
        assertTrue(list.contains("1"));
        assertTrue(!list.contains("3"));
    }


}