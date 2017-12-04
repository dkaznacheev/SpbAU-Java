package HashTable;

public class Main {
    static void test1() {
        HashTable hashTable = new HashTable();
        hashTable.put("Vasya", "1 group");
        hashTable.put("Petya", "2 group");
        hashTable.put("Igor", "3 group");
        System.out.println(hashTable.get("Vasya"));
        System.out.println(hashTable.get("Petya"));
        System.out.println(hashTable.put("Igor", "4 group"));
        System.out.println(hashTable.get("Igor"));
        System.out.println(hashTable.get("Oleg"));
    }

    static void test2 () {
        List list = new List("ka", "va");
        System.out.println(list.get("ka"));
        System.out.println(list.put("ka", "v1"));
        System.out.println(list.contains("ka"));

        list.put("kb", "vb");
        System.out.println(list.get("kb"));
        list.remove("ka");
        System.out.println(list.get("ka"));
    }

    static void test3 () {
        HashTable table = new HashTable(4);
        table.put("ccc", "0");
        table.put("ddd", "1");
        table.put("aaa", "2");
        table.put("bbb", "3");
        System.out.println(table.get("aaa"));
        System.out.println(table.get("bbb"));
        System.out.println(table.get("ccc"));
        System.out.println(table.get("ddd"));

    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
    }
}
