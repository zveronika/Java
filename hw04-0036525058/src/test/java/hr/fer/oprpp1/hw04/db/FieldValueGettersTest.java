package hr.fer.oprpp1.hw04.db;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FieldValueGettersTest {

    public static StudentRecord record;

    @BeforeEach
    public void createRecord() {
        record = new StudentRecord("0000000001","Akšamović","Marin",2);
    }

    @Test
    public void firstNameTest() {
        assertEquals("Marin",  FieldValueGetters.FIRST_NAME.get(record));
    }

    @Test
    public void lastNameTest() {
        assertEquals("Akšamović",  FieldValueGetters.LAST_NAME.get(record));
    }

    @Test
    public void jmbagTest() {
        assertEquals("0000000001",  FieldValueGetters.JMBAG.get(record));
    }

}
