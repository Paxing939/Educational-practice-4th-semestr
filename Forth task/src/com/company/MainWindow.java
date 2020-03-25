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

public class MainWindow extends JPanel {
    private JFreeChart chart;
    private PieDataset pieDataset;

    MainWindow() {
        setVisible(true);
        setPreferredSize(new Dimension(600, 400));

        try {
            JsonReader reader = new JsonReader(new FileReader("data"));
            Gson g = new Gson();
            Sweet[] sweets = g.fromJson(reader, Sweet[].class);
            for (var elem : sweets) {
                if (elem.getVal() <= 0)
                    throw new NumberFormatException("Negative value in your data.txt!");
            }
            pieDataset = createDataset(sweets);
            chart = createChart(pieDataset);
            PiePlot plot = (PiePlot) chart.getPlot();
            PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator(
                    "{0}: {1} штук/и, {2}");
            plot.setLabelGenerator(gen);
            JPanel panel = new JPanel(new BorderLayout());
            panel.add(new ChartPanel(chart), BorderLayout.CENTER);
            add(panel);
            panel.validate();
        } catch (IOException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        } catch (NumberFormatException exc) {
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
        catch (JsonSyntaxException exc) {
            System.out.println(exc.getMessage());
            JOptionPane.showMessageDialog(null, exc.getMessage());
        }
    }

    private JFreeChart createChart(final PieDataset dataset) {
        chart = ChartFactory.createPieChart("Диаграмма конфет",
                dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        return chart;
    }

    private PieDataset createDataset(Sweet[] container) {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (var elem : container) {
            dataset.setValue(elem.getCategory(), elem.getVal());
        }
        return dataset;
    }

}
