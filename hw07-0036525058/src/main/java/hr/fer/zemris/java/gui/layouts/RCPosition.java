package hr.fer.zemris.java.gui.layouts;

import java.util.Objects;

public class RCPosition {
    /**
     *
     */
    private int row;
    /**
     *
     */
    private int column;

    /**
     *
     */
    public RCPosition(int row, int column) {
        this.row = row;
        this.column = column;
    }

    /**
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    public static RCPosition parse(String text) {
        int index = text.indexOf(",");
        int r = Integer.parseInt(text.substring(0, index));
        int c = Integer.parseInt(text.substring(index + 1));

        return new RCPosition(r, c);
    }

    @Override
    public String toString() {
        return String.format("(%d,%d)", this.getRow(), this.getColumn());
    }

    @Override
    public int hashCode() {
        return Objects.hash(column, row);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RCPosition other = (RCPosition) obj;
        return column == other.column && row == other.row;
    }


}

