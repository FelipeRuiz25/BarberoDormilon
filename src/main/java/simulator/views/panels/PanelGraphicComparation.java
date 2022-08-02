package simulator.views.panels;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import simulator.views.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelGraphicComparation extends JPanel{

    private JLabel titleForGraphic;
    private JFreeChart pieChart;
    private ChartPanel chartPanel;



    public PanelGraphicComparation(String title, ArrayList<Integer> list) {
        this.titleForGraphic = new JLabel(title);
        this.pieChart = ChartFactory.createPieChart(title,createDataset(list));

        this.chartPanel = new ChartPanel(pieChart);

        pieChart.getPlot().setBackgroundPaint( new Color(177, 175, 175) );

        fill();
    }

    private static PieDataset createDataset(ArrayList<Integer> list) {
        DefaultPieDataset dataset = new DefaultPieDataset( );
        dataset.setValue( "Procesos Atendidos: "+list.get(0) , list.get(0) );
        dataset.setValue( "Procesos ignorados: " +list.get(1), list.get(1) );
        dataset.setValue( "Procesos En Las Sillas: " +list.get(2), list.get(2) );
        return dataset;
    }

    private void fill(){
        this.chartPanel.setPreferredSize(new Dimension(700, 500));
        this.titleForGraphic.setFont(Constants.FONT_LIST);
        add(chartPanel);
    }
}