package hr.fer.zemris.java.gui.charts;

import java.util.ArrayList;
import java.util.List;

public class BarChart {

    private List<XYValue> values;
    private String xAxis;
    private String yAxis;

    private int minY;
    private int maxY;
    private int gap;

    public BarChart(List<XYValue> values, String xAxis, String yAxis, int minY, int maxY, int gap) {
        this.values = values;
        this.xAxis = xAxis;
        this.yAxis = yAxis;
        if (minY < 0)
            throw new IllegalArgumentException("Minimal y cannot be negative!");
        this.minY = minY;
        if (maxY < minY)
            throw new IllegalArgumentException("Maximal y cannot be smaller than minimal y!");
        this.maxY = maxY;
        if ((maxY - minY) % gap != 0) {
            int y = minY;
            while (y < maxY) {
                y += gap;
            }
            this.maxY = y;
        }
        for (XYValue v : values) {
            if (v.getY() < minY) {
                throw new IllegalArgumentException(String.format("XYValue %s has y value smaller than minimal y.", v));
            }
        }
        this.gap = gap;
    }

    public List<XYValue> getValues() {
        return values;
    }

    public String getxAxis() {
        return xAxis;
    }

    public String getyAxis() {
        return yAxis;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    public int getGap() {
        return gap;
    }
}
