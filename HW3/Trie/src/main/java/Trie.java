import java.io.*;

public class Trie implements Serializable, MySerializable{
    private static final int CHAR_NUMBER = 256*256;

    @Override
    public void serialize(OutputStream out) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
        objectOutputStream.writeObject(this);
        objectOutputStream.flush();
        objectOutputStream.close();
    }

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

    private class Node {
        private boolean isTerminal;
        private Node next[];
        private int stringNumber;
        private Node() {
            this.isTerminal = false;
            this.next = new Node[CHAR_NUMBER];
            stringNumber = 0;
        }
    }

    private Node head;

    public Trie() {
        head = new Node();
    }

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

    public int size() {
        return head.stringNumber;
    }

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

    private void writeObject(java.io.ObjectOutputStream out)
            throws IOException {

    }


}
