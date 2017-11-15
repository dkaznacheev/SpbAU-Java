import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyTreeSetImpl<E> extends AbstractSet<E> implements MyTreeSet<E> {

    public MyTreeSetImpl() {
        comparator = new Comparator<E>() {
            @Override
            public int compare(E e, E t1) {
                try {
                    Comparable<E> t0 = (Comparable<E>) e;
                    return t0.compareTo(t1);
                } catch (ClassCastException ex) {
                    throw ex;
                }
            }
        };
    }

    public MyTreeSetImpl(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    private static class Node<E> {
        private Node<E> left;
        private Node<E> right;
        private E entry;

        public Node(E entry) {
            this.entry = entry;
        }
    }

    private Comparator<E> comparator;
    private Node<E> root;
    private int size = 0;

    @Override
    public boolean add(E e) {
        if (root == null) {
            root = new Node<>(e);
            size++;
            return true;
        }

        Node<E> node = root;
        while (node != null) {
            int result = comparator.compare(e, node.entry);
            if (result == 0) {
                return false;
            }
            if (result < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        node = new Node<>(e);
        size++;
        return true;
    }

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


    @Override
    public boolean remove(Object e) {
        if (root == null) {
            return false;
        }
        Node<E> node = root;
        while (node != null) {
            int result = comparator.compare((E) e, node.entry);
            if (result == 0) {
                E lower = lower((E) e);
                E higher = higher((E) e);
                if (lower == null && higher == null) {
                    node = null;
                    size--;
                    return true;
                }
                if (lower != null) {
                    remove(lower);
                    node.entry = lower;
                } else {
                    remove(higher);
                    node.entry = higher;
                }
            }
            if (result < 0) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return false;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }

    @Override
    public MyTreeSet<E> descendingSet() {
        return null;
    }

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

    @Override
    public E ceiling(E e) {
        return findCeiling(root, e);
    }

    private E findCeiling(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.entry, e) < 0) {
            return findCeiling(node.left, e);
        }
        E result = findCeiling(node.right, e);
        if (result == null) {
            return node.entry;
        }
        return result;
    }

    @Override
    public E higher(E e) {
        return findHigher(root, e);
    }

    private E findHigher(Node<E> node, E e) {
        if (node == null) {
            return null;
        }
        if (comparator.compare(node.entry, e) <= 0) {
            return findHigher(node.left, e);
        }
        E result = findHigher(node.right, e);
        if (result == null) {
            return node.entry;
        }
        return result;
    }

    @Override
    public Iterator<E> iterator() {
        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
