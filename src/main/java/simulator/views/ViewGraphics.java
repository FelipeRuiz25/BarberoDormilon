package simulator.views;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import simulator.views.panels.PanelGraphic;
import simulator.views.panels.PanelGraphicComparation;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewGraphics extends JFrame {

    private JLabel titleFrame;
    private JPanel mainPanel;
    //grafica de tiempo de vida de los procesos ejecutados
    private PanelGraphic graphicTimeOfLife;
    //grafica de prioridades de los procesos ejecutados
    private PanelGraphic graphicPriority;
    //grafica de cantidad de procesos atendidos y no atendidos y los que quedaron en las sillas
    private PanelGraphicComparation graphicProcess;


    public ViewGraphics(ArrayList<Integer> listTimeLife, ArrayList<Integer> priority, ArrayList<Integer> process,ArrayList<String> names){
        super(Constants.TITTLE);
        //configurar tema de la aplicacion
        FlatCyanLightIJTheme.setup();
        this.titleFrame = new JLabel(Constants.TITTLE_FRAME_GRAPHICS);
        this.mainPanel = new JPanel();
        this.setBackground(Color.WHITE);
        this.mainPanel.setBackground(Color.WHITE);

        this.graphicTimeOfLife  = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_LIFE,listTimeLife,names);
        this.graphicTimeOfLife.setBackground(Color.WHITE);

        this.graphicPriority = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_PRIORITY,priority,names);
        this.graphicPriority.setBackground(Color.WHITE);

        this.graphicProcess = new PanelGraphicComparation(Constants.TITTLE_PANEL_GRAPHIC_PROCCES_TOTAL,process);
        this.graphicProcess.setBackground(Color.WHITE);

        this.init();

    }

    private void init(){
        setSize(Constants.SIZE_GRAPHICS);
        setResizable(false);
        setLocationRelativeTo(null);
        this.fill();
        setVisible(true);
    }

    private void fill(){
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        this.titleFrame.setFont(Constants.FONT_TITLE);

        mainPanel.add(titleFrame);
        mainPanel.add(graphicTimeOfLife);
        mainPanel.add(graphicPriority);
        mainPanel.add(graphicProcess);

        this.add(scroll,BorderLayout.CENTER);

    }
}
