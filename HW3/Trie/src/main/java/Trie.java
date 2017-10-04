import java.io.*;

/**
 * Trie stores strings, it can add, remove and check the existence of strings.
 */
public class Trie implements Serializable, MySerializable{
    private static final int CHAR_NUMBER = 256*256;

    /**
     * writes the trie to given output byte stream
     * @param out the output stream
     * @throws IOException
     */
    @Override
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

    /**
     * reads the object from input byte stream
     * @param in the input stream
     * @throws IOException
     */
    @Override
    public void deserialize(InputStream in) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(in);
        try {
            Trie newTrie = (Trie) objectInputStream.readObject();
            this.head = newTrie.head;
        } catch (ClassNotFoundException e) {
            throw new IOException();
        }
    }

    /**
     * Represents the node in a trie structure.
     */
    private class Node {

        /**
         * is that node the end of some string in a trie
         */
        private boolean isTerminal;

        /**
         * an array of next nodes from this node
         */
        private Node next[];

        /**
         * how many strings there are with this prefix
         */
        private int stringNumber;

        private Node() {
            this.isTerminal = false;
            this.next = new Node[CHAR_NUMBER];
            stringNumber = 0;
        }
    }

    /**
     * the starting node of trie
     */
    private Node head;

    public Trie() {
        head = new Node();
    }

    /**
     * adds the string to trie, returns true if this string already existed
     * @param element added string
     * @return if there already was this string
     */
    public boolean add(String element) {
        if (contains(element))
            return true;

        Node node = head;
        for (int i = 0; i < element.length(); i++) {
            int index = (int) element.charAt(i);
            if (node.next[index] == null) {
                node.next[index] = new Node();
            }
            if (i == element.length() - 1) {
                node.next[index].isTerminal = true;
            }
            node.stringNumber++;
            node = node.next[index];
        }
        node.stringNumber++;
        return false;
    }

    /**
     * Returns if there is the string in a trie
     * @param element string to check
     * @return if there was this string
     */
    public boolean contains(String element) {
        Node node = head;
        for (int i = 0; i < element.length(); i++) {
            int index = (int) element.charAt(i);
            if (node.next[index] == null) {
                return false;
            }
            node = node.next[index];
        }
        return node.isTerminal;
    }

    /**
     * Removes the string from the trie, returns true if there was such string
     * @param element string to remove
     * @return if there was such string
     */
    public boolean remove(String element) {
        if (!contains(element))
            return false;
        Node node = head;
        Node previousNode = null;
        int previousIndex = 0;
        for (int i = 0; i < element.length(); i++) {
            int index = (int) element.charAt(i);
            node.stringNumber--;
            if (node != head && node.stringNumber == 0) { //dangerous
                previousNode.next[previousIndex] = null;
                return true;
            }

            previousIndex = index;
            previousNode = node;
            node = node.next[index];
        }
        node.stringNumber--;
        node.isTerminal = false;
        return true;
    }

    /**
     * Returns the size of trie
     * @return size of trie
     */
    public int size() {
        return head.stringNumber;
    }

    /**
     * Returns how many strings start with given prefix
     * @param prefix prefix to check
     * @return how many strings start with given prefix
     */
    public int howManyStartsWithPrefix(String prefix) {
        Node node = head;
        for (int i = 0; i < prefix.length(); i++) {
            int index = (int) prefix.charAt(i);
            if (node.next[index] == null)
                return 0;
            node = node.next[index];
        }
        return node.stringNumber;
    }
}
