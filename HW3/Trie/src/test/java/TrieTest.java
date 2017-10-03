import org.junit.Test;

import static org.junit.Assert.*;

public class TrieTest {
    @Test
    public void add() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assert (trie.contains("abc"));
        assert (trie.size() == 5);
    }

    @Test
    public void contains() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ac");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assert (trie.contains("a"));
        assert (!trie.contains("ab"));
        assert (trie.contains("aba"));
        assert (trie.contains("abc"));
        assert (trie.contains("abb"));
        assert (!trie.contains("b"));
        assert (!trie.contains("abcd"));
    }

    @Test
    public void remove() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        trie.remove("ab");
        assert (trie.contains("a"));
        assert (!trie.contains("ab"));
        assert (trie.contains("aba"));
        trie.remove("abc");
        assert (!trie.contains("abc"));
    }

    @Test
    public void size() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assert (trie.contains("abc"));
        assert (trie.size() == 5);
        trie.remove("a");
        assert (trie.size() == 4);
        trie.remove("ab");
        assert (trie.size() == 3);
        trie.remove("aba");
        assert (trie.size() == 2);
        trie.remove("abc");
        assert (trie.size() == 1);
        trie.remove("abb");
        assert (trie.size() == 0);
        trie.remove("aaaa");
        assert (trie.size() == 0);

    }

    @Test
    public void howManyStartsWithPrefix() throws Exception {
        Trie trie = new Trie();
        trie.add("a");
        trie.add("ab");
        trie.add("aba");
        trie.add("abc");
        trie.add("abb");
        assert (trie.howManyStartsWithPrefix("a") == 5);
        assert (trie.howManyStartsWithPrefix("ab") == 4);
        assert (trie.howManyStartsWithPrefix("abc") == 1);
        assert (trie.howManyStartsWithPrefix("b") == 0);
    }

}