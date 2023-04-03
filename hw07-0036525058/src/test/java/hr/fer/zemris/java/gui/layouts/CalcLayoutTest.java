package hr.fer.zemris.java.gui.layouts;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.junit.jupiter.api.Test;

public class CalcLayoutTest {

    @Test
    void illegalPositionTest() {
        CalcLayout cl = new CalcLayout(3);
        JLabel l = new JLabel("label");
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(-1, 1)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(2, -2)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(6, 3)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(4, 8)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(1, 2)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(1, 3)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(1, 4)));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l, new RCPosition(1, 5)));
    }

    @Test
    void takenPositionTest() {
        CalcLayout cl = new CalcLayout(3);
        JLabel l1 = new JLabel("label");
        JLabel l2 = new JLabel("label2");
        cl.addLayoutComponent(l1, new RCPosition(2, 2));
        assertThrows(CalcLayoutException.class,
                ()->cl.addLayoutComponent(l2, new RCPosition(2, 2)));
    }

    @Test
    void preferredSizeTest() {
        JPanel p = new JPanel(new CalcLayout(2));
        JLabel l1 = new JLabel(""); l1.setPreferredSize(new Dimension(10,30));
        JLabel l2 = new JLabel(""); l2.setPreferredSize(new Dimension(20,15));
        p.add(l1, new RCPosition(2, 2));
        p.add(l2, new RCPosition(3, 3));
        Dimension dim = p.getPreferredSize();
        assertEquals(152, dim.width);
        assertEquals(158, dim.height);
    }
    @Test
    void preferredSizeTest2() {
        JPanel p = new JPanel(new CalcLayout(2));
        JLabel l1 = new JLabel("");
        l1.setPreferredSize(new Dimension(108,15));
        JLabel l2 = new JLabel("");
        l2.setPreferredSize(new Dimension(16,30));
        p.add(l1, new RCPosition(1, 1));
        p.add(l2, new RCPosition(3, 3));
        Dimension dim = p.getPreferredSize();
        assertEquals(158, dim.height);
        assertEquals(152, dim.width);
    }

}
