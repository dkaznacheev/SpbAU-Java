import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A binary tree implementation of MyTreeSet, supports iterating over elements
 * @param <E> type of value stored in MyTreeSet
 */
public class MyTreeSetImpl<E> extends AbstractSet<E> implements MyTreeSet<E> {

    /**
     * Constructs with default comparator.
     * Throws IllegalArgumentException if elements can't be compared.
     */
    public MyTreeSetImpl() {
        comparator = new Comparator<E>() {
            @Override
            @SuppressWarnings("unchecked")
            public int compare(E e, E t1) {
                try {
                    Comparable<E> t0 = (Comparable<E>) e;
                    return t0.compareTo(t1);
                } catch (ClassCastException ex) {
                    throw new IllegalArgumentException();
                }
            }
        };
    }

    /**
     * Constructs with a comparator.
     * @param comparator comparator to the elements
     */
    public MyTreeSetImpl(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    /**
     * Node in the tree.
     * @param <E> type of value stored in the node
     */
    private static class Node<E> {

        /**
         * Left son of this node
         */
        private Node<E> left;

        /**
         * Left son of this node
         */
        private Node<E> right;

        /**
         * Value of this node
         */
        private E entry;

        public Node(E entry) {
            this.entry = entry;
        }
    }

    /**
     * The set's comparator.
     */
    private Comparator<E> comparator;

    /**
     * The root of the TreeSet.
     */
    private Node<E> root;

    /**
     * Size of the tree.
     */
    private int size = 0;

    /**
     * Adds an element to TreeSet.
     * @param e element to add
     * @return if there was this element in the set already.
     */
    @Override
    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size++;
            return true;
        }

        boolean wentLeft = false;
        Node<E> parent = null;
        Node<E> node = root;
        while (node != null) {
            int result = comparator.compare(e, node.entry);
            if (result == 0) {
                return false;
            }
            parent = node;
            wentLeft = result < 0;
            if (wentLeft) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        if (wentLeft) {
            parent.left = new Node<>(e);
        } else {
            parent.right = new Node<>(e);
        }
        size++;
        return true;
    }

    /**
     * Checks if the element is in the set.
     * @param e element to check
     * @return if there was such an element in the set.
     */
    @SuppressWarnings("unchecked")
    @Override
    public boolean contains(Object e) {
        if (root == null) {
            return false;
        }
        Node<E> node = root;
        while (node != null) {
            int result = comparator.compare((E) e, node.entry);
            if (result == 0) {
                return true;
            }
            if (result < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    /**
     * The set's descending iterator, from highest to lowest element.
     * @return the iterator
     */
    @Override
    public Iterator<E> descendingIterator() {
        return new Iterator<E>() {
            E elem = last();

            @Override
            public boolean hasNext() {
                return lower(elem) != null;
            }

            @Override
            public E next() {
                E res = elem;
                elem = lower(elem);
                return res;
            }
        };
    }

    /**
     * The descending set of this set.
     * @return this set in descending order
     */
    @Override
    public MyTreeSet<E> descendingSet() {
        return new DescendingTreeSet();
    }

    /**
     * The descending set of this set.
     */
    private class DescendingTreeSet extends MyTreeSetImpl<E>{
        MyTreeSetImpl<E> set = MyTreeSetImpl.this;

        @Override
        public boolean add(E e) {
            return set.add(e);
        }

        @Override
        public boolean contains(Object e) {
            return set.contains(e);
        }

        @Override
        public int size() {
            return set.size();
        }

        @Override
        public Iterator<E> descendingIterator() {
            return set.descendingIterator();
        }

        @Override
        public E first() {
            return set.first();
        }

        @Override
        public E last() {
            return set.last();
        }

        @Override
        public E lower(E e) {
            return set.lower(e);
        }

        @Override
        public E floor(E e) {
            return set.floor(e);
        }

        @Override
        public E ceiling(E e) {
            return set.ceiling(e);
        }

        @Override
        public E higher(E e) {
            return set.higher(e);
        }
    }

    /**
     * Returns first element of the set, throws NoSuchElementException if the set is empty.
     * @return first element
     */
    @Override
    public E first() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node<E> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.entry;
    }

    /**
     * Returns last element of the set, throws NoSuchElementException if the set is empty.
     * @return last element
     */
    @Override
    public E last() {
        if (root == null) {
            throw new NoSuchElementException();
        }
        Node<E> node = root;
        while (node.right != null) {
            node = node.right;
        }
        return node.entry;
    }

    /**
     * Returns first element of the set, that is smaller than this, null if there's no such element.
     * @return first smaller element of the set, or null
     */
    @Override
    public E lower(E e) {
        return findLower(root, e);
    }

    private E findLower(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.entry, e) >= 0) {
            return findLower(node.left, e);
        }
        E result = findLower(node.right, e);
        if (result == null) {
            return node.entry;
        }
        return result;
    }

    /**
     * Returns first element of the set, that is smaller or equal than this, null if there's no such element.
     * @return first smaller or equal element of the set, or null
     */
    @Override
    public E floor(E e) {
        return findFloor(root, e);
    }

    private E findFloor(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.entry, e) > 0) {
            return findFloor(node.left, e);
        }
        E result = findFloor(node.right, e);
        if (result == null) {
            return node.entry;
        }
        return result;
    }

    /**
     * Returns first element of the set, that is larger or equal than this, null if there's no such element.
     * @return first larger or equal element of the set, or null
     */
    @Override
    public E ceiling(E e) {
        return findCeiling(root, e);
    }

    private E findCeiling(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.entry, e) < 0) {
            return findCeiling(node.right, e);
        }
        E result = findCeiling(node.left, e);
        if (result == null) {
            return node.entry;
        }
        return result;
    }

    /**
     * Returns first element of the set, that is larger than this, null if there's no such element.
     * @return first larger element of the set, or null
     */
    @Override
    public E higher(E e) {
        return findHigher(root, e);
    }

    private E findHigher(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.entry, e) <= 0) {
            return findHigher(node.right, e);
        }
        E result = findHigher(node.left, e);
        if (result == null) {
            return node.entry;
        }
        return result;
    }

    /**
     * Returns descending iterator of this set.
     * @return descending iterator of this set
     */
    @Override
    public Iterator<E> iterator() {
        return descendingIterator();
    }

    /**
     * Returns the size of the set
     * @return set's size
     */
    @Override
    public int size() {
        return size;
    }
}
