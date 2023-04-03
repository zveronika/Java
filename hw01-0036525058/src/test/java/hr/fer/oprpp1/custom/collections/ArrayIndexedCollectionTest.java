package hr.fer.oprpp1.custom.collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayIndexedCollectionTest {

    private ArrayIndexedCollection a;

    @BeforeEach
    public void createNewArrayIndexedCollection() {
        a = new ArrayIndexedCollection();
    }

    @Test
    public void defaultConstructorTest() {
        assertEquals(0, a.size());
        assertEquals(16, a.getLength());
        assertTrue(a.isEmpty());
    }

    @Test
    public void initialCapacityBoundariesTest() {
        ArrayIndexedCollection a1 = new ArrayIndexedCollection(5);
        assertEquals(0, a1.size());
        assertEquals(5, a1.getLength());
        assertTrue(a1.isEmpty());

        ArrayIndexedCollection a2 = new ArrayIndexedCollection(1);
        assertEquals(0, a2.size());
        assertEquals(1, a2.getLength());
        assertTrue(a2.isEmpty());

        assertThrows(IllegalArgumentException.class, () -> new ArrayIndexedCollection(0));
    }

    @Test
    public void fromOtherCollectionConstructorTest() {
        a.add(1);
        a.add(2);
        a.add(3);

        ArrayIndexedCollection a2 = new ArrayIndexedCollection(a);
        assertEquals(3, a2.size());
        assertEquals(16, a2.getLength());
        assertFalse(a2.isEmpty());
    }

    @Test
    public void isEmptyTest() {
        assertTrue(a.isEmpty());
    }

    @Test
    public void sizeTest() {
        assertEquals(0, a.size());

        a.add(1);
        a.add(2);
        a.add(3);
        assertEquals(3, a.size());
    }

    @Test
    public void addNullTest() {
        assertThrows(NullPointerException.class, () -> a.add(null));
    }

    @Test
    public void addTest() {
        a.add(1);
        assertEquals(1, a.size());
        assertFalse(a.isEmpty());
    }

    @Test
    public void containsTest() {
        a.add(1);
        assertFalse(a.contains(null));
        assertFalse(a.contains(2));
        assertTrue(a.contains(1));
    }

    @Test
    public void removeValueTest() {
        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        assertTrue(a.remove("Tikvica"));
        assertFalse(a.remove("Luk"));
    }

    @Test
    public void toArrayTest() {
        assertArrayEquals(new Integer[0], a.toArray());

        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        Integer[] ar = new Integer[3];
        assertEquals(ar.length, a.toArray().length);
    }

    @Test
    public void clearTest() {
        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        a.clear();
        assertTrue(a.isEmpty());
    }

    @Test
    public void getAtIndexTest() {
        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        assertEquals("Paprika", a.get(0));
        assertEquals("Tikvica", a.get(2));
    }

    @Test
    public void getAtIndexExceptionTest() {
        assertThrows(IndexOutOfBoundsException.class, () -> a.get(1));
    }

    @Test
    public void insertValueAtIndex() {
        a.insert("Rajcica", 0);
        assertEquals(1, a.size());
    }

    @Test
    public void insertValueAtWrongIndex() {
        assertThrows(IndexOutOfBoundsException.class, () -> a.insert("Rajcica", 2));
    }

    @Test
    public void insertNullAtIndex() {
        assertThrows(NullPointerException.class, () -> a.insert(null, 0));
    }

    @Test
    public void removeAtIndex() {
        ArrayIndexedCollection a = new ArrayIndexedCollection();
        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        a.remove(2);
        assertEquals(2, a.size());
        assertEquals("Rajcica", a.get(1));
    }

    @Test
    public void forEachTest() {
        ArrayIndexedCollection a2 = new ArrayIndexedCollection();
        a.add(1);
        class LocalProcessor extends Processor {
            @Override
            public void process(Object value) {
                if (value != null) a2.add(value);
            }
        }
        a.forEach(new LocalProcessor());
        assertEquals(a2.toString(), a.toString());
    }

    @Test
    public void addAllTest() {
        ArrayIndexedCollection a2 = new ArrayIndexedCollection();
        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        a2.addAll(a);
        assertEquals(3, a2.size());
        assertEquals("Rajcica", a2.get(1));
    }

    @Test
    public void removeIndexTest() {
        a.add("Paprika");
        a.add("Rajcica");
        a.add("Tikvica");
        a.remove(2);
        assertEquals(2, a.size());
        assertEquals("Rajcica", a.get(a.size()-1));
        assertThrows(IndexOutOfBoundsException.class, () -> a.remove(2));
    }
}
