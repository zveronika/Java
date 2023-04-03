package hr.fer.zemris.java.gui.layouts;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

import javax.swing.plaf.DimensionUIResource;
import javax.swing.plaf.basic.BasicSliderUI.ComponentHandler;

/**
 * Class CalcLayout creates grid of components. This class implements
 * LayoutManager2 interface.
 *
 * @author Veronika Žunar
 */
public class CalcLayout implements LayoutManager2 {

    /**
     * Initial number of rows in grid.
     */
    private static final int ROWS = 5;
    /**
     * Initial number of columns in grid.
     */
    private static final int COLUMNS = 7;
    /**
     * Maximal number of components.
     */
    private static final int MAX_COMPS = 31;
    /**
     * Position of always first, top left element.
     */
    private static final RCPosition START = new RCPosition(1, 1);

    /**
     *
     */
    private int gapSize;

    private Map<RCPosition, Component> layout;

    public CalcLayout() {
        this(0);
    }

    public CalcLayout(int gap) {
        if (gap < 0)
            throw new IllegalArgumentException("Gap size cannot be less than zero!");

        this.gapSize = gap;
        this.layout = new HashMap<RCPosition, Component>(MAX_COMPS);
    }

    @Override
    public void addLayoutComponent(String name, Component comp) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        Objects.requireNonNull(comp);
        for (Map.Entry<RCPosition, Component> entry : layout.entrySet()) {
            if (entry.getValue() == comp) {
                layout.remove(entry.getKey());
            }
        }
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        Function<Component, Dimension> pref = (c) -> c.getPreferredSize();
        return calculate(parent, pref);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        Function<Component, Dimension> min = (c) -> c.getMinimumSize();
        return calculate(parent, min);
    }

    @Override
    public Dimension maximumLayoutSize(Container target) {
        Function<Component, Dimension> max = (c) -> c.getMaximumSize();
        return calculate(target, max);
    }

    public Dimension calculate(Container cont, Function<Component, Dimension> strategy) {
        Component[] comps = cont.getComponents();
        Dimension dim = new Dimension();
        boolean isNull = true;
        for (Component c : comps) {
            Dimension cdim = strategy.apply(c);
            if (cdim == null) continue;
            isNull = false;

            if (c == layout.get(START)) {
                cdim.width -= 4 * gapSize;
                cdim.width = (int) Math.round(cdim.width / 5.0);
            }
            if (cdim.height > dim.height) {
                dim.height = cdim.height;
            }
            if (cdim.width > dim.width) {
                dim.width = cdim.width;
            }
        }
        if (isNull) return null;

        dim.height *= ROWS;
        dim.height += (ROWS - 1) * gapSize;
        dim.width *= COLUMNS;
        dim.width += (COLUMNS - 1) * gapSize;
        return dim;
    }

    @Override
    public void layoutContainer(Container parent) {
        Dimension dim = parent.getSize();
        Insets ins = parent.getInsets();

        Dimension dimension = new Dimension();
        dimension.width = dim.width - (ins.left + ins.right) - (COLUMNS - 1) * gapSize;
        dimension.height = dim.height - (ins.top + ins.bottom) - (ROWS - 1) * gapSize;
        if (dimension.width < 0)
            dimension.width = 0;
        if (dimension.height < 0)
            dimension.height = 0;

        if (dimension.width == 0 || dimension.height == 0) {
            return;
        } else {
            int w = dimension.width / COLUMNS;
            int h = dimension.height / ROWS;

            int mistakeW = dim.width - COLUMNS * w - (COLUMNS - 1) * gapSize;
            int mistakeH = dim.height - ROWS * h - (ROWS - 1) * gapSize;
            int[] widths = checkDistribution(mistakeW, w, COLUMNS);
            int[] heights = checkDistribution(mistakeH, h, ROWS);

            for (Map.Entry<RCPosition, Component> entry : layout.entrySet()) {
                RCPosition pos = entry.getKey();
                Component comp = entry.getValue();
                int entryW, entryH;
                if (pos.equals(START)) {
                    entryW = widths[0] + widths[1] + widths[2] + widths[3] + widths[4] + 4 * gapSize;
                    entryH = heights[0];
                    comp.setBounds(0, 0, entryW, entryH);
                    continue;
                } else {
                    int x = 0, y = 0;
                    for (int i = 0; i < pos.getColumn() - 1; i++) {
                        x += (widths[i] + gapSize);
                    }
                    for (int i = 0; i < pos.getRow() - 1; i++) {
                        y += (heights[i] + gapSize);
                    }
                    comp.setBounds(x, y, widths[pos.getColumn() - 1], heights[pos.getRow() - 1]);
                }

            }
        }

    }

    private int[] checkDistribution(int mistake, int d, int len) {
        int[] dimensions = new int[len];
        for (int i = 0; i < len; i++)
            dimensions[i] = d;
        if (mistake != 0) {
            int abs = Math.abs(mistake);
            int shift = abs / mistake;
            for (int i = 0; i < abs; i++) {
                int index = (i + 1) * len / (abs + 1);
                dimensions[index] += shift;
            }
        }
        return dimensions;
    }

    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        if (comp == null || constraints == null)
            throw new NullPointerException("None of the parameters must not be null.");

        RCPosition position;
        if (constraints instanceof String) {
            try {
                position = RCPosition.parse((String) constraints);
            } catch (Exception e) {
                throw new IllegalArgumentException("Unable to parse string.");
            }
        } else if (constraints instanceof RCPosition) {
            position = (RCPosition) constraints;
        } else {
            throw new IllegalArgumentException("Wrong type of constraint");
        }

        checkLegalPosition(position);
        layout.put(position, comp);
    }

    @Override
    public float getLayoutAlignmentX(Container target) {
        return 0;
    }

    @Override
    public float getLayoutAlignmentY(Container target) {
        return 0;
    }

    @Override
    public void invalidateLayout(Container target) {
    }

    /**
     * Helping method that checks if given arguments of the position are legal
     * otherwise the exception is thrown.
     * <p>
     * Given row argument of the position shouldn't be less than 1 or greater than
     * value of variable ROWS. Same for column argument, shouldn't be less than 1 or
     * greater than value of constant COLUMNS.
     * <p>
     * Also, positions from (1,1) to (1,5) are reserved for only one component. If
     * given position is already taken, this method also throws exception.
     *
     * @param position
     */
    public void checkLegalPosition(RCPosition position) {
        if (layout.containsKey(position))
            throw new CalcLayoutException("This position is already taken by other component.");

        int row = position.getRow();
        if (row < 1 || row > ROWS)
            throw new CalcLayoutException("Illegal row argument.");

        int col = position.getColumn();
        if (col < 1 || col > COLUMNS)
            throw new CalcLayoutException("Illegal column argument.");

        if (row == 1 && col >= 2 && col <= 5)
            throw new CalcLayoutException("Positions (1,2) to (1,5) can not be used.");
    }
}
