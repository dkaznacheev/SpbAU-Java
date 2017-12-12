import org.junit.Test;
import ru.spbau.binarytree.BinaryTree;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BinaryTreeTest {
    @Test
    public void addSameElementTest() throws Exception {
        BinaryTree<Integer> binaryTree = new BinaryTree<>();
        binaryTree.add(0);
        assertFalse(binaryTree.add(10));
        assertEquals (2, binaryTree.size());
        assertTrue(binaryTree.add(10));
        assertEquals (2, binaryTree.size());
    }

    @Test
    public void containsElementsTest() throws Exception {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        binaryTree.add("abc");
        binaryTree.add("aaa");
        assertTrue (binaryTree.contains("abc"));
        assertTrue (binaryTree.contains("aaa"));
        assertFalse (binaryTree.contains("aba"));
        binaryTree.add("abcd");
        assertTrue (binaryTree.contains("abc"));
        assertTrue (binaryTree.contains("aaa"));
        assertFalse (binaryTree.contains("aba"));
        assertTrue (binaryTree.contains("abcd"));
    }

    @Test
    public void sizeChangesOnAddTest() throws Exception {
        BinaryTree<String> binaryTree = new BinaryTree<>();
        assertEquals (0, binaryTree.size());
        binaryTree.add("abc");
        assertEquals (1, binaryTree.size());
        binaryTree.add("abc");
        assertEquals (1, binaryTree.size());
        binaryTree.add("aaa");
        assertEquals (2, binaryTree.size());

    }

}