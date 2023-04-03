package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SimpleHashtableTest {

    @Test
    public void tableEntryTest() {
        SimpleHashtable.TableEntry<String, Integer> entry = new SimpleHashtable.TableEntry<>("Veronika", 1);
        assertEquals(1, entry.getValue());
        entry.setValue(2);
        assertEquals(2, entry.getValue());
    }

    @Test
    public void isEmptyTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        assertTrue(sht.isEmpty());
    }

    @Test
    public void putAndSizeTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        sht.put("Veronika", 1);
        sht.put("Veronika", 2);
        assertEquals(1, sht.size());
        assertFalse(sht.isEmpty());
    }

    @Test
    public void slotTest() {
        //“Ivana” --> hashCode() = 71029095
        // default table length = 16
        // 71029095 % 16 = 7
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        assertEquals(7, sht.slot("Ivana"));
    }

    @Test
    public void getTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        sht.put("Ivana", 1);
        sht.put("Ante", 2);
        sht.put("Ivana", 3);
        assertEquals(3, sht.get("Ivana"));
        assertEquals(null, sht.get("Marko"));
    }

    @Test
    public void containsTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        sht.put("Ivana", 1);
        sht.put("Ante", 2);
        sht.put("Ivana", null);
        try {
            assertTrue(sht.containsKey("Ante"));
            assertFalse(sht.containsKey(null));
            assertTrue(sht.containsValue(2));
            assertTrue(sht.containsValue(null));
        } catch (NullPointerException e){
        }
    }

    @Test
    public void keyNotNullTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        assertThrows(NullPointerException.class, () -> sht.put(null, 1));
    }

    @Test
    public void removeTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        sht.put("Ivana", 1);
        sht.put("Ante", 2);
        assertEquals(1, sht.remove("Ivana"));
        assertEquals(null, sht.remove("Marko"));
    }

    @Test
    public void toArrayTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        sht.put("Ivana", 1);
        SimpleHashtable.TableEntry<String, Integer> expected = new SimpleHashtable.TableEntry<>("Ivana", 1);
        assertEquals("["+expected+"]", sht.toString());
    }

    @Test
    public void clearTest() {
        SimpleHashtable<String, Integer> sht = new SimpleHashtable<>();
        sht.put("Ivana", 1);
        sht.put("Ante", 2);
        assertEquals(2, sht.size());
        sht.clear();
        assertEquals(0, sht.size());
        assertEquals(null, sht.get("Ivana"));
    }
}
