package hr.fer.zemris.java.gui.calc;

import java.util.Stack;

import javax.swing.*;

import hr.fer.zemris.java.gui.calc.buttons.CalcButton;
import hr.fer.zemris.java.gui.calc.buttons.NumberButton;
import hr.fer.zemris.java.gui.calc.buttons.OperatorButton;
import hr.fer.zemris.java.gui.calc.model.CalcModel;
import hr.fer.zemris.java.gui.calc.model.CalcModelImpl;
import hr.fer.zemris.java.gui.layouts.CalcLayout;

import java.awt.*;

public class Calculator extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Stack<Double> stack;

    private boolean inv;

    private CalcModel calcModel;




    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Calculator().setVisible(true));
    }

    public Calculator() {
        this.stack = new Stack<>();
        this.calcModel = new CalcModelImpl();
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setTitle("Java Calculator v1.0");

        initGUI();
    }

    private void initGUI() {
        Container cp = getContentPane();
        cp.setLayout(new CalcLayout(3));
        cp.setBackground(Color.LIGHT_GRAY);

        JLabel display = new JLabel();
        display.setBackground(Color.YELLOW);
        display.setOpaque(true);
        cp.add(display, "1,1");
        calcModel.addCalcValueListener(e -> {
            display.setText(calcModel.toString());
        });



        JButton eq = new CalcButton("=");
        cp.add(eq, "1,6");

        OperatorButton divide = new OperatorButton("/");
        cp.add(divide, "2,6");
        OperatorButton multiply = new OperatorButton("*");
        cp.add(multiply, "3,6");
        OperatorButton minus = new OperatorButton("-");
        cp.add(minus, "4,6");
        OperatorButton plus = new OperatorButton("+");
        cp.add(plus, "5,6");

        OperatorButton invert = new OperatorButton("1/x");
        cp.add(invert, "2,1");
        OperatorButton sin = new OperatorButton("sin");
        cp.add(sin, "2,2");
        OperatorButton log = new OperatorButton("log");
        cp.add(log, "3,1");
        OperatorButton cos = new OperatorButton("cos");
        cp.add(cos, "3,2");
        OperatorButton ln = new OperatorButton("ln");
        cp.add(ln, "4,1");
        OperatorButton tan = new OperatorButton("tan");
        cp.add(tan, "4,2");
        OperatorButton xN = new OperatorButton("x^n");
        cp.add(xN, "5,1");
        OperatorButton ctg = new OperatorButton("ctg");
        cp.add(ctg, "5,2");

        CalcButton clear = new CalcButton("clr");
        cp.add(clear, "1,7");
        CalcButton reset = new CalcButton("reset");
        cp.add(reset, "2,7");
        CalcButton push = new CalcButton("push");
        cp.add(push, "3,7");
        CalcButton pop = new CalcButton("pop");
        cp.add(pop, "4,7");
        JCheckBox inv = new JCheckBox("inv");
        cp.add(inv, "5,7");


        NumberButton zero = new NumberButton(0, calcModel);
        cp.add(zero, "5,3");
        NumberButton one = new NumberButton(1, calcModel);
        cp.add(one, "4,3");
        NumberButton two = new NumberButton(2, calcModel);
        cp.add(two, "4,4");
        NumberButton three = new NumberButton(3, calcModel);
        cp.add(three, "4,5");
        NumberButton four = new NumberButton(4, calcModel);
        cp.add(four, "3,3");
        NumberButton five = new NumberButton(5, calcModel);
        cp.add(five, "3,4");
        NumberButton six = new NumberButton(6, calcModel);
        cp.add(six, "3,5");
        NumberButton seven = new NumberButton(7, calcModel);
        cp.add(seven, "2,3");
        NumberButton eight = new NumberButton(8, calcModel);
        cp.add(eight, "2,4");
        NumberButton nine = new NumberButton(9, calcModel);
        cp.add(nine, "2,5");

        CalcButton sign = new CalcButton("+/-");
        cp.add(sign, "5,4");

        CalcButton decimal = new CalcButton(".");
        cp.add(decimal, "5,5");

    }
}
