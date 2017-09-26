package ru.spbau.dkaznacheev;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListTest {
    @Test
    public void size() throws Exception {
        List list = new List();
        assert (list.size() == 0);
        list.put("1", "v1");
        assert (list.size() == 1);
        list.put("2", "v2");
        assert (list.size() == 2);
        list.put("2", "v3");
        assert (list.size() == 2);
    }

    @Test
    public void get() throws Exception {
        List list = new List("1", "v1");
        assert(list.get("1").equals("v1"));
        assert(list.get("2") == null);
    }

    @Test
    public void put() throws Exception {
        List list = new List();
        list.put("1", "v1");
        assert(list.size() == 1);
        assert(list.contains("1"));
        assert(!list.contains("v1"));
        String result = list.put("1", "v11");
        assert(result.equals("v1"));
        assert(list.size() == 1);
    }

    @Test
    public void popFront() throws Exception {
        List list = new List();
        list.put("1", "v1");
        list.put("2", "v2");
        List.Pair pair = list.popFront();
        assert(pair.key.equals("1"));
        assert(pair.value.equals("v1"));
        assert(list.size() == 1);
        assert(!list.contains("1"));
        pair = list.popFront();
        assert(pair.key.equals("2"));
        assert(pair.value.equals("v2"));
        assert(list.size() == 0);
        pair = list.popFront();
        assert(pair == null);
    }

    @Test
    public void remove() throws Exception {
        List list = new List();
        list.put("1", "v1");
        list.put("2", "v2");
        list.put("3", "v3");
        String result = list.remove("2");
        assert(result.equals("v2"));
        assert(!list.contains("2"));
        assert(list.size() == 2);
        result = list.remove("4");
        assert(result == null);
        assert(list.size() == 2);
        list.remove("1");
        list.remove("3");
        assert(list.size() == 0);
        result = list.remove("1");
        assert(result == null);
        assert(list.size() == 0);
    }

    @Test
    public void contains() throws Exception {
        List list = new List();
        list.put("1", "v1");
        list.put("2", "v2");
        assert(list.contains("1"));
        assert(!list.contains("3"));
    }


}