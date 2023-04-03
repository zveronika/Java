package hr.fer.zemris.java.gui.calc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleBinaryOperator;

public class CalcModelImpl implements CalcModel {

    /**
     *
     */
    private DoubleBinaryOperator pendingBinaryOperation;

    /**
     *
     */
    private Double activeOperand;

    /**
     *
     */
    private boolean sign;

    /**
     *
     */
    private boolean freezeValue;

    /**
     *
     */
    private double value;

    /**
     *
     */
    private String digits;

    /**
     *
     */
    private String displayValue;

    /**
     * A list of event listeners.
     */
    private List<CalcValueListener> calcValueListenerList;

    public CalcModelImpl() {
        this.value = 0.0;
        this.sign = true;
        this.digits = "";
        this.displayValue = null;

        this.activeOperand = null;
        this.pendingBinaryOperation = null;

        this.freezeValue = false;

        this.calcValueListenerList = new ArrayList<>();
    }

    @Override
    public void addCalcValueListener(CalcValueListener l) {
        if (l == null)
            throw new NullPointerException("Given CalcValueListener must not be null.");

        this.calcValueListenerList.add(l);
    }

    @Override
    public void removeCalcValueListener(CalcValueListener l) {
        if (l == null)
            throw new NullPointerException("Given CalcValueListener must not be null.");

        this.calcValueListenerList.remove(l);
    }

    @Override
    public double getValue() {
        if (this.sign)
            return value;
        else
            return -1 * this.value;
    }

    @Override
    public void setValue(double value) {
        this.value = value;
        this.digits = Double.toString(value);
        this.freezeValue = true;
        notifyListeners();
    }

    @Override
    public boolean isEditable() {
        return !this.freezeValue;
    }

    @Override
    public void clear() {
        this.value = 0.0;
        this.digits = "";
        this.displayValue = null;
        this.freezeValue = false;
        notifyListeners();
    }

    @Override
    public void clearAll() {
        this.activeOperand = 0.0;
        this.pendingBinaryOperation = null;
        clear();
    }

    @Override
    public void swapSign() throws CalculatorInputException {
        if (!this.isEditable())
            throw new CalculatorInputException("Calculator is not editable");

        this.sign = !this.sign;
        notifyListeners();
    }

    @Override
    public void insertDecimalPoint() throws CalculatorInputException {
        boolean isDecimal = false;
        if (digits.indexOf(".") != -1) {
            isDecimal = true;
        }
        if (!this.isEditable())
            throw new CalculatorInputException("Calculator is not editable");
        if (isDecimal)
            throw new CalculatorInputException("Decimal point already exists.");

        if (digits.length() == 0) {
            value = 0.0;
            digits = "0";
        }
        digits += ".";
        notifyListeners();
    }

    @Override
    public void insertDigit(int digit) throws CalculatorInputException, IllegalArgumentException {
        if (!this.isEditable())
            throw new CalculatorInputException("Calculator is not editable");

        digits += Integer.toString(digit);

        notifyListeners();
    }

    @Override
    public boolean isActiveOperandSet() {
        return this.activeOperand != null;
    }

    @Override
    public double getActiveOperand() throws IllegalStateException {
        if (!this.isActiveOperandSet())
            throw new IllegalStateException("There is no active operand.");

        return this.activeOperand;
    }

    @Override
    public void setActiveOperand(double activeOperand) {
        this.activeOperand = activeOperand;
    }

    @Override
    public void clearActiveOperand() {
        this.activeOperand = null;
    }

    @Override
    public DoubleBinaryOperator getPendingBinaryOperation() {
        return this.pendingBinaryOperation;
    }

    @Override
    public void setPendingBinaryOperation(DoubleBinaryOperator op) {
        this.pendingBinaryOperation = op;
    }

    @Override
    public String toString() {
        if (value == Double.POSITIVE_INFINITY || value == Double.NEGATIVE_INFINITY) {
            this.displayValue = "Infinity";
        } else if (value == Double.NaN) {
            this.displayValue = "NaN";
        }
        return super.toString();
    }

    /**
     *
     */
    public void notifyListeners() {
        calcValueListenerList.forEach((cvl) -> cvl.valueChanged(this));
    }
}
