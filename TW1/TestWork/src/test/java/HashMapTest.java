import org.junit.Test;

import static org.junit.Assert.*;

public class HashMapTest {
    @Test
    public void size() throws Exception { 
        HashMap<String, String > table = new HashMap<>(4);
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
    public void get() throws Exception {
        HashMap<String, String > table = new HashMap<>(4);
        table.put("1", "v1");
        table.put("2", "v2");
        assert(table.get("1").equals("v1"));
        assert(table.get("2").equals("v2"));
        assert(table.get("3") == null);
    }

    @Test
    public void put() throws Exception {
        HashMap<String, String > table = new HashMap<>(4);
        table.put("1", "v1");
        assert(table.size() == 1);
        assert(table.contains("1"));
        assert(!table.contains("v1"));
        String result = table.put("1", "v11");
        assert(result.equals("v1"));
        assert(table.size() == 1);
    }

    @Test
    public void remove() throws Exception {
        HashMap<String, String > table = new HashMap<>(4);
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
    public void contains() throws Exception {
        HashMap<String, String > table = new HashMap<>(4);
        table.put("1", "v1");
        assert(table.contains("1"));
        assert(!table.contains("2"));

    }

    @Test
    public void clear() throws Exception {
        HashMap<String, String> map = new HashMap<>(4);
        map.put("1", "v1");
        map.put("2", "v2");
        map.clear();
        assert(map.size() == 0);
        assert(!map.contains("1"));
        assert(!map.contains("2"));
    }
    @Test
    public void entrySet() throws Exception{
        HashMap<String, String> map = new HashMap<>(4);
        map.put("1", "v1");
        map.put("2", "v2");
        HashMap.HashSet hashSet = (HashMap.HashSet) map.entrySet();
        assert (hashSet.size() == 2);

    }

}