package hr.fer.oprpp1.hw05.crypto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class UtilTest {
    @Test
    public void hexToByteTest() {
        String keyText = "01aE22";
        byte[] bytes = {1, -82, 34};
        int i = 0;
        for (byte b : Util.hextobyte(keyText)) {
            assertEquals(bytes[i], b);
            i++;
        }
    }

    @Test
    public void byteToHexTest() {
        String keyText = "01ae22";
        byte[] bytes = {1, -82, 34};
        assertEquals(keyText, Util.bytetohex(bytes));
    }
}
