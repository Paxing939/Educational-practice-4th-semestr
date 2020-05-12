package com.company;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import javax.swing.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;

public class Diagram extends JPanel {
    private JFreeChart chart;

    Diagram() {
        setVisible(true);
        setPreferredSize(new Dimension(500, 500));

        try {
            JsonReader reader = new JsonReader(new FileReader("data"));
            Gson g = new Gson();
            Block[] blocks = g.fromJson(reader, Block[].class);
            for (var elem : blocks) {
                if (elem.getVal() <= 0) {
                    throw new NumberFormatException("Negative value in your data.txt!");
                }
            }
            PieDataset pieDataset = createDataset(blocks);
            chart = createChart(pieDataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1}");
            plot.setLabelGenerator(gen);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new ChartPanel(chart), BorderLayout.CENTER);
            add(panel);
            panel.validate();
        } catch (IOException | NumberFormatException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        } catch (JsonSyntaxException exc) {
            System.out.println(exc.getMessage());
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }

    private JFreeChart createChart(final PieDataset dataset) {
        chart = ChartFactory.createPieChart("Диаграмма блоков в Minecraft",
                dataset, true, true, false);
        return chart;
    }

    private PieDataset createDataset(Block[] container) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var elem : container) {
            dataset.setValue(elem.getCategory(), elem.getVal());
        }
        return dataset;
    }

}
