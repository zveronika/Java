package hr.fer.oprpp1.custom.collections;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class SimpleHashtableDemoTest {

    @Test
    public void testJedan() {
        SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
        // fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        // query collection:
        assertEquals(5, examMarks.get("Kristina"));
        // What is collection's size? Must be four!
        assertEquals(4, examMarks.size());
    }

    @Test
    public void testDva() {
        // create collection:
        SimpleHashtable<String, Integer> examMarks = new SimpleHashtable<>(2);
        // fill data:
        examMarks.put("Ivana", 2);
        examMarks.put("Ante", 2);
        examMarks.put("Jasna", 2);
        examMarks.put("Kristina", 5);
        examMarks.put("Ivana", 5); // overwrites old grade for Ivana
        assertEquals(5, examMarks.get("Ivana"));
        assertEquals(4, examMarks.size());

        Iterator<SimpleHashtable.TableEntry<String, Integer>> iter = examMarks.iterator();
        while (iter.hasNext()) {
            iter.next();
            iter.remove(); // sam iterator kontrolirano uklanja trenutni element
        }
        assertEquals(0, examMarks.size());

    }
}
