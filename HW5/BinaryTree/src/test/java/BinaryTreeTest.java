import org.junit.Test;

import static org.junit.Assert.*;

public class BinaryTreeTest {
    @Test
    public void add() throws Exception {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(0);
        binaryTree.add(10);
        assert (binaryTree.size() == 2);
        binaryTree.add(10);
        assert (binaryTree.size() == 2);
    }

    @Test
    public void contains() throws Exception {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        binaryTree.add("abc");
        binaryTree.add("aaa");
        assert (binaryTree.contains("abc"));
        assert (binaryTree.contains("aaa"));
        assert (!binaryTree.contains("aba"));
        binaryTree.add("abcd");
        assert (binaryTree.contains("abc"));
        assert (binaryTree.contains("aaa"));
        assert (!binaryTree.contains("aba"));
        assert (binaryTree.contains("abcd"));
    }

    @Test
    public void size() throws Exception {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        assert (binaryTree.size() == 0);
        binaryTree.add("abc");
        assert (binaryTree.size() == 1);
        binaryTree.add("abc");
        assert (binaryTree.size() == 1);
        binaryTree.add("aaa");
        assert (binaryTree.size() == 2);

    }

}