package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DictionaryTest<K,V> {

    @Test
    public void isEmptyTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        assertTrue(dic.isEmpty());
    }

    @Test
    public void sizeTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        dic.put(1, "Java");
        assertEquals(1, dic.size());
        dic.put(2, "OPRPP1");
        assertEquals(2, dic.size());
    }

    @Test
    public void clearTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        dic.put(1, "Java");
        dic.put(2, "OPRPP1");
        assertEquals(2, dic.size());
        dic.clear();
        assertEquals(0, dic.size());
    }

    @Test
    public void putTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        dic.put(1, "Java");
        assertEquals(1, dic.size());
        dic.put(1, "OPRPP1");
        assertEquals(1, dic.size());
    }

    @Test
    public void getTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        dic.put(1, "Java");
        assertEquals("Java", dic.get(1));
        assertEquals(1, dic.size());
        assertEquals(null, dic.get(2));
    }

    @Test
    public void removeTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        dic.put(1, "Java");
        assertEquals("Java", dic.remove(1));
        assertEquals(0, dic.size());
        assertEquals(null, dic.remove(1));
    }

    @Test
    public void keyNotNullTest() {
        Dictionary<Integer, String> dic = new Dictionary<>();
        assertThrows(NullPointerException.class, () -> dic.put(null, "Java"));
        assertThrows(NullPointerException.class, () -> dic.get(null));
        assertThrows(NullPointerException.class, () -> dic.remove(null));
    }
}
