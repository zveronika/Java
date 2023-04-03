package hr.fer.zemris.java.gui.calc.buttons;

import javax.swing.*;

import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalculatorInputException;

import java.awt.*;

public class NumberButton extends CalcButton {

    /**
     *
     */
    public NumberButton(int digit, CalcModel calcModel) {
        super(Integer.toString(digit));
        this.setFont(this.getFont().deriveFont(30f));
        this.addActionListener((e) -> {
            try {
                calcModel.insertDigit(digit);
            } catch (UnsupportedOperationException | IllegalStateException | CalculatorInputException ex) {
                JFrame root = (JFrame) SwingUtilities.getRoot(this);
                JOptionPane.showMessageDialog(root, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


    }
}
