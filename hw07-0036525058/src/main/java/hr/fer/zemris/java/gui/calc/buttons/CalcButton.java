package hr.fer.zemris.java.gui.calc.buttons;

import hr.fer.zemris.java.gui.calc.model.CalcModel;

import javax.swing.*;
import java.awt.*;

public class CalcButton extends JButton {

    private static final long serialVersionUID = 1L;

    private static final Color LILAC = new Color(231,209,255);

    public CalcButton(String text) {
        super(text);
        setBackground(LILAC);
    }
}
