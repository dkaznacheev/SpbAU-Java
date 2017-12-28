import org.junit.Test;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class MyTreeSetImplTest {
    @Test
    public void addElementsToTreeSet() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        nums.add(3);
        nums.add(4);
        assertTrue(nums.contains(1));
        assertTrue(!nums.contains(2));
        assertTrue(nums.contains(3));
        assertTrue(nums.contains(4));
    }

    @Test
    public void lowerWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        nums.add(3);
        nums.add(4);
        assertEquals((Integer) 4, nums.lower(5));
        assertEquals((Integer) 3, nums.lower(4));
        assertEquals((Integer) 1, nums.lower(2));
        assertEquals(null, nums.lower(1));
    }

    @Test
    public void higherWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        nums.add(3);
        nums.add(4);
        assertEquals((Integer) 1, nums.higher(0));
        assertEquals((Integer) 3, nums.higher(1));
        assertEquals(null, nums.higher(4));
    }

    @Test
    public void floorWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        nums.add(3);
        nums.add(4);
        assertEquals((Integer) 1, nums.floor(1));
        assertEquals((Integer) 1, nums.floor(2));
        assertEquals((Integer) 3, nums.floor(3));
        assertEquals(null, nums.floor(0));
    }

    @Test
    public void ceilingWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        nums.add(3);
        nums.add(4);
        assertEquals((Integer) 4, nums.ceiling(4));
        assertEquals((Integer) 3, nums.ceiling(2));
        assertEquals((Integer) 3, nums.ceiling(3));
        assertEquals(null, nums.ceiling(5));
    }

    @Test
    public void firstWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(4);
        assertEquals((Integer) 4, nums.first());
        nums.add(3);
        assertEquals((Integer) 3, nums.first());
        nums.add(1);
        assertEquals((Integer) 1, nums.first());
        nums.add(2);
        assertEquals((Integer) 1, nums.first());

    }

    @Test
    public void lastWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        assertEquals((Integer) 1, nums.first());
        nums.add(2);
        assertEquals((Integer) 2, nums.first());
        nums.add(4);
        assertEquals((Integer) 4, nums.first());
        nums.add(3);
        assertEquals((Integer) 4, nums.first());

    }

    @Test (expected = NoSuchElementException.class)
    public void emptyFirstThrows() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.first();
    }

    @Test (expected = NoSuchElementException.class)
    public void emptyLastThrows() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.last();
    }

    @Test
    public void customComparator() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer integer, Integer t1) {
                return Math.abs(integer) - Math.abs(t1);
            }
        });
        nums.add(-5);
        nums.add(4);
        nums.add(1);
        nums.add(0);
        assertEquals((Integer) 0, nums.first());
        assertEquals((Integer) (-5), nums.last());
    }

    @Test
    public void sizeChanges() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        assertEquals(0, nums.size());
        nums.add(1);
        assertEquals(1, nums.size());
        nums.add(3);
        assertEquals(2, nums.size());
        nums.add(4);
        assertEquals(3, nums.size());
    }

    @Test
    public void iteratorWorks() throws Exception {
        MyTreeSet<Integer> nums = new MyTreeSetImpl<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);
        nums.add(4);
        int i = 4;
        for (Iterator<Integer> it = nums.descendingIterator(); it.hasNext(); i--) {
            assertEquals((Integer)i, it.next());
        }
    }
}