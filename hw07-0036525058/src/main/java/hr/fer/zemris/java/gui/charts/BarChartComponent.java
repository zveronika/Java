package hr.fer.zemris.java.gui.charts;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

public class BarChartComponent extends JComponent {

    private static final int PADDING = 20;

    private BarChart barChart;


    public BarChartComponent(BarChart barChart) {
        this.barChart = barChart;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        int width = getWidth(); //širina prozora
        int height = getHeight(); //visina prozora

        FontMetrics fm = graphics2D.getFontMetrics();
        int font = fm.getAscent();
        int len = fm.stringWidth(Integer.valueOf(barChart.getMaxY()).toString());

        int leftPoint = len + PADDING * 3; //PADDING + len + PADDING + PADDING
        int bottomPoint = font + PADDING * 3; //PADDING + font + PADDING + PADDING

        int maxY = barChart.getMaxY();
        int minY = barChart.getMinY();
        int gap = barChart.getGap();
        int numOfY = (maxY - minY) / gap;
        double stepY = (double) (height - bottomPoint - 2 * PADDING) / numOfY;

        List<XYValue> values = barChart.getValues();
        if (values.size() == 0) return;
        int minX = values.get(0).getX();
        int maxX = minX;
        for (XYValue v : values) {
            minX = Math.min(v.getX(), minX);
            maxX = Math.max(v.getX(), maxX);
            if (v.getX() < 1) {
                JFrame frame = (JFrame) SwingUtilities.getRoot(this);
                JOptionPane.showMessageDialog(frame, "Negative values cannot be displayed!", "Invalid argument", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        int numOfX = maxX - minX + 1;
        double stepX = (double) (width - leftPoint - PADDING - 5) / numOfX;

        AffineTransform origin = graphics2D.getTransform();
        AffineTransform trans = new AffineTransform();
        trans.rotate(-Math.PI / 2);
        graphics2D.transform(trans);
        int nameAxisLen = fm.stringWidth(barChart.getyAxis());
        int positionX = (int) Math.round((double) (-height + bottomPoint) / 2 - (double) nameAxisLen / 2);
        int positionY = PADDING + font;
        graphics2D.drawString(barChart.getyAxis(), positionX, positionY);

        graphics2D.setTransform(origin);
        nameAxisLen = fm.stringWidth(barChart.getxAxis());
        positionX = leftPoint + (width - leftPoint) / 2 - nameAxisLen / 2;
        positionY = height - PADDING;
        graphics2D.drawString(barChart.getxAxis(), positionX, positionY);

        graphics2D.drawLine(leftPoint, (height - bottomPoint + PADDING / 2), leftPoint, PADDING);
        for (int y = minY, i = 0; y <= maxY; y += gap, i++) {
            int yLen = fm.stringWidth(Integer.valueOf(y).toString());
            int posX = PADDING * 2 + font + len - yLen; //PADDING + font + PADDING + len - yLen
            int posY = (int) Math.round(height - bottomPoint + (double) font / 2 - i * stepY);

            graphics2D.drawString(Integer.valueOf(y).toString(), posX, posY);

            posX += yLen;
            posY -= Math.round((double) (font - fm.getDescent()) / 2);
            graphics2D.drawLine(posX, posY, posX + PADDING / 2, posY);
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.drawLine(posX, posY, width - PADDING, posY);
            graphics2D.setColor(getForeground());
        }

        graphics2D.drawLine((leftPoint - PADDING / 2), (height - bottomPoint), (width - PADDING), (height - bottomPoint));

        int posY = height - bottomPoint + PADDING + font;
        for (int x = minX, i = 0; x <= maxX; x++, i++) {
            int xLen = fm.stringWidth(Integer.valueOf(x).toString());
            int posX = (int) (Math.round(leftPoint + i * stepX + stepX / 2 - (double) xLen / 2));
            graphics2D.drawString(Integer.valueOf(x).toString(), posX, posY);

            int pos1 = (int) (Math.round(leftPoint + (i + 1) * stepX));
            int pos2 = height - bottomPoint + PADDING / 2;
            graphics2D.drawLine(pos1, pos2, pos1, pos2);
            graphics2D.setColor(Color.LIGHT_GRAY);
            graphics2D.drawLine(pos1, pos2, pos1, PADDING);
            graphics2D.setColor(getForeground());
        }


        graphics2D.setColor(new Color(244, 119, 72, 255));
        for (XYValue v : values) {
            int x = v.getX();
            int y = v.getY();
            graphics2D.fillRect((int) (Math.round(leftPoint + (x - 1) * stepX)) + 1,
                    (int) (Math.round(height - bottomPoint - stepY * y / gap)),
                    (int) stepX - 2,
                    (int) (Math.round(stepY * y / gap)));
        }
        graphics2D.setColor(getForeground());
    }
}

