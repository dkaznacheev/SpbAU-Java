/**
 * Binary search tree working as a set, capable of adding/checking elements.
 * @param <T> type of the object it stores
 */
public class BinaryTree<T> {

    /**
     * The binary tree subclass representing the node of a tree.
     */
    class Node {
        T value;
        Node left;
        Node right;

        public Node(T value) {
            this.value = value;
        }
    }

    /**
     * Tree's root
     */
    private Node root;

    /**
     * Size of tree
     */
    private int size = 0;

    /**
     * Adds an object to the binary tree.
     * @param value object to add
     * @return true if there was this object already, false otherwise
     */
    public boolean add (T value) {

        if (root == null) {
            root = new Node(value);
            size++;
            return false;
        }

        Node node = root;
        Node previousNode = null;
        boolean isLeftSon = false;

        while (node != null) {
            if (value.equals(node.value)) {
                return true;
            }
            previousNode = node;
            if (value.hashCode() < node.value.hashCode()) {
                node = node.left;
                isLeftSon = true;
            } else {
                node = node.right;
                isLeftSon = false;
            }

        }

        node = new Node(value);
        if (isLeftSon) {
            previousNode.left = node;
        } else {
            previousNode.right = node;
        }
        size++;
        return false;
    }

    /**
     * Checks if there is an object in a binary tree
     * @param value object to find
     * @return if there was this object
     */
    public boolean contains (T value) {
        Node node = root;
        while (node != null) {
            if (value.equals(node.value)) {
                return true;
            }

            if (value.hashCode() < node.value.hashCode()) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    /**
     * Returns the size of binary tree.
     * @return size of tree
     */
    public int size() {
        return size;
    }
}
