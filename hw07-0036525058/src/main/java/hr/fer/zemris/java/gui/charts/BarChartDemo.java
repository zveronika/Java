package hr.fer.zemris.java.gui.charts;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class BarChartDemo extends JFrame {

    public BarChartDemo() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("BarChartDemo");
        setSize(700, 500);
        initGUI();
        //pack();
    }

    private void initGUI() {
        Container cp = getContentPane();
        cp.setBackground(Color.WHITE);
    }

    public static void main(String[] args) {

        List<String> allLines = new ArrayList<>();
        try {
            allLines = Files.readAllLines(Paths.get(args[0]), StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println("File reading went wrong!");
            System.exit(-1);
        }

        String[] arguments = new String[6];
        int i = 0;
        for(String line : allLines)
            arguments[i++] = line;

        String[] values = arguments[2].replaceAll("\\\s+", " ").split(" ");
        List<XYValue> xyValues = new ArrayList<>();
        for(String value : values)
            xyValues.add(new XYValue(Integer.parseInt(value.split(",")[0]), Integer.parseInt(value.split(",")[1])));

        String xAxis = arguments[0];
        String yAxis = arguments[1];
        int minY = Integer.parseInt(arguments[3]);
        int maxY = Integer.parseInt(arguments[4]);
        int gap = Integer.parseInt(arguments[5]);

        BarChart bc = new BarChart(xyValues, xAxis, yAxis, minY, maxY, gap);
        BarChartComponent bcc = new BarChartComponent(bc);
        BarChartDemo bcd = new BarChartDemo();
        bcd.getContentPane().add(bcc);

        SwingUtilities.invokeLater(()->{
            bcd.setVisible(true);
        });
    }
}
