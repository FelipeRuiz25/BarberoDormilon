package simulator.views.panels;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.ui.Layer;
import org.jfree.chart.ui.RectangleAnchor;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import simulator.views.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelGraphic extends JPanel{

    private JLabel titleForGraphic;
    private JFreeChart pieChart;
    private ChartPanel chartPanel;
    private double init;
    private double medium;
    private double end;

    public PanelGraphic(String title, ArrayList<Integer> list){
        this.titleForGraphic = new JLabel(title);
        this.pieChart = ChartFactory.createBarChart(title, Constants.VALUE_X_GRAPHIC, Constants.VALUE_Y_GRAPHIC, createDataset(list), PlotOrientation.VERTICAL, true, true, false);

        this.chartPanel = new ChartPanel(pieChart);

        CategoryPlot plot = pieChart.getCategoryPlot();
        //poner valor inferior y superior
        plot.addRangeMarker(new IntervalMarker(init, end), Layer.BACKGROUND);

        //valor inicial
        Marker start = new ValueMarker(init);
        start.setPaint(Color.green);
        start.setLabel( "Tiempo Minimo" );
        start.setLabelFont(Constants.FONT_LIST);
        start.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        start.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(start);

        //valor promedio
        Marker average = new ValueMarker(medium);
        average.setPaint(Color.CYAN);
        average.setLabel( "Tiempo Promedio" );
        average.setLabelFont(Constants.FONT_LIST);
        average.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        average.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(average);

        //valor final
        Marker endMarker = new ValueMarker(end);
        endMarker.setPaint(Color.red);
        endMarker.setLabel( "Tiempo Maximo" );
        endMarker.setLabelFont(Constants.FONT_LIST);
        endMarker.setLabelAnchor(RectangleAnchor.BOTTOM_RIGHT);
        endMarker.setLabelTextAnchor(TextAnchor.TOP_RIGHT);
        plot.addRangeMarker(endMarker);
        fill();



        pieChart.getPlot().setBackgroundPaint( new Color(177, 175, 175) );

    }

    private void fill(){
        this.chartPanel.setPreferredSize(new Dimension(700, 500));
        this.titleForGraphic.setFont(Constants.FONT_LIST);
        add(chartPanel);
    }

    //a este metodo llegan los datos que se van a poner en la grafica estos son solo para visualizar que sirviera bien
    private CategoryDataset createDataset(ArrayList<Integer> list) {
        init= 1000000000;
        end = 0;
        // create the dataset...
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < list.size(); i++) {
            dataset.addValue(list.get(i),i+"",i+"");
            if (list.get(i)>end){
                end=list.get(i);
            }
            if (list.get(i)<init){
                init = list.get(i);
            }
        }
        medium = (end+init)/2;



        return dataset;
    }


}
