package simulator.views;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import simulator.views.panels.PanelGraphic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ViewGraphics extends JFrame {

    private JLabel titleFrame;
    private JPanel mainPanel;
    private PanelGraphic graphicTimeOfLife;
    private PanelGraphic graphicTimeOfReady;
    private PanelGraphic graphicTimeOfWaiting;

    public ViewGraphics(ArrayList<Integer> listTimeLife,ArrayList<Integer> listTimeBlock,ArrayList<Integer> listTimeReady){
        super(Constants.TITTLE);
        setIconImage(new ImageIcon(Constants.ICON_PATH).getImage());
        //configurar tema de la aplicacion
        FlatCyanLightIJTheme.setup();
        this.titleFrame = new JLabel(Constants.TITTLE_FRAME_GRAPHICS);
        this.mainPanel = new JPanel();
        this.setBackground(Color.WHITE);
        this.mainPanel.setBackground(Color.WHITE);
        this.graphicTimeOfLife  = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_LIFE,listTimeLife);
        this.graphicTimeOfReady = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_READY,listTimeReady);
        this.graphicTimeOfWaiting = new PanelGraphic(Constants.TITTLE_PANEL_GRAPHIC_TIME_WAITING,listTimeBlock);
        this.graphicTimeOfLife.setBackground(Color.WHITE);
        this.graphicTimeOfReady.setBackground(Color.WHITE);
        this.graphicTimeOfWaiting.setBackground(Color.WHITE);
        this.init();
    }

    private void init(){
        setSize(Constants.SIZE_GRAPHICS);
        setResizable(false);
        setLocationRelativeTo(null);
        this.fill();
        setVisible(true);
        SwingUtilities.updateComponentTreeUI(this);
    }

    private void fill(){
        JScrollPane scroll = new JScrollPane();
        scroll.setViewportView(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel,BoxLayout.Y_AXIS));
        this.titleFrame.setFont(Constants.FONT_LIST);
        mainPanel.add(titleFrame);
        mainPanel.add(graphicTimeOfLife);
        mainPanel.add(graphicTimeOfReady);
        mainPanel.add(graphicTimeOfWaiting);
        this.add(scroll,BorderLayout.CENTER);
    }
}
