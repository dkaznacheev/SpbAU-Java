import org.junit.Test;
import ru.spbau.trie.Trie;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void addStringsTest() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assertTrue(trie.contains("abc"));
        assertEquals(5, trie.size());
    }

    @Test
    public void containsStringsTest() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ac");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assertTrue(trie.contains("a"));
        assertFalse(trie.contains("ab"));
        assertTrue(trie.contains("aba"));
        assertTrue(trie.contains("abc"));
        assertTrue(trie.contains("abb"));
        assertFalse(trie.contains("b"));
        assertFalse(trie.contains("abcd"));
    }

    @Test
    public void removeStringsTest() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        trie.remove("ab");
        assertTrue(trie.contains("a"));
        assertFalse(trie.contains("ab"));
        assertTrue(trie.contains("aba"));
        trie.remove("abc");
        assertFalse(trie.contains("abc"));
    }

    @Test
    public void removeChangesSizeTest() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assertTrue(trie.contains("abc"));
        assertEquals(5, trie.size());
        trie.remove("a");
        assertEquals(4, trie.size());
        trie.remove("ab");
        assertEquals(3, trie.size());
        trie.remove("aba");
        assertEquals(2, trie.size());
        trie.remove("abc");
        assertEquals(1, trie.size());
        trie.remove("abb");
        assertEquals(0, trie.size());
        trie.remove("aaaa");
        assertEquals(0, trie.size());

    }

    @Test
    public void howManyStartsWithPrefix() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assertEquals (5, trie.howManyStartsWithPrefix("a"));
        assertEquals (4, trie.howManyStartsWithPrefix("ab"));
        assertEquals (1, trie.howManyStartsWithPrefix("abc"));
        assertEquals (0, trie.howManyStartsWithPrefix("b"));
    }

    @Test
    public void serializationBasicTest() throws Exception{
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        trie.serialize(outStream);
        byte[] byteTrie = outStream.toByteArray();

        ByteArrayInputStream inStream = new ByteArrayInputStream(byteTrie);
        Trie readTrie = new Trie();
        readTrie.deserialize(inStream);

        assertTrue(readTrie.contains("a"));
        assertTrue(readTrie.contains("ab"));
        assertTrue(readTrie.contains("aba"));
        assertTrue(readTrie.contains("abc"));
        assertTrue(readTrie.contains("abb"));
        assertEquals(5, readTrie.size());
    }

    @Test
    public void serializationEmptyTrieTest() throws Exception{
        Trie trie = new Trie();
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        trie.serialize(outStream);
        byte[] byteTrie = outStream.toByteArray();

        ByteArrayInputStream inStream = new ByteArrayInputStream(byteTrie);
        Trie readTrie = new Trie();
        readTrie.add("a");
        readTrie.add("ab");
        readTrie.add("aba");
        readTrie.add("abc");
        readTrie.add("abb");
        readTrie.deserialize(inStream);

        assertFalse(readTrie.contains("a"));
        assertFalse(readTrie.contains("ab"));
        assertFalse(readTrie.contains("aba"));
        assertFalse(readTrie.contains("abc"));
        assertFalse(readTrie.contains("abb"));
        assertEquals(0, readTrie.size());
    }
}