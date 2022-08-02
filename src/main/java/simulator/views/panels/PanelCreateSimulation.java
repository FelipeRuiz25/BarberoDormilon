package simulator.views.panels;

import simulator.controllers.Commands;
import simulator.views.Constants;
import simulator.views.components.MyJButton;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelCreateSimulation extends JPanel {

    private JButton btnStartSimulation;
    private JLabel labelMaxPriority;
    private JLabel labelMaxTimeNextProcess;
    private JLabel labelMaxTimeProcessLife;
    private JLabel labelSimulationTime;
    private JSpinner sprMaxProcessTimeLife;
    private JSpinner sprMaxPriority;
    private JSpinner sprMaxTimeNextProcess;
    private JSpinner sprSimulationTime;

    public PanelCreateSimulation(ActionListener listener) {
        setBorder(BorderFactory.createTitledBorder("Crear Simulacion"));
        setLayout(null);
        initComponents(listener);
        fill();
    }

    private void initComponents(ActionListener listener) {
        labelSimulationTime = new JLabel("Tiempo de simulacion");
        sprSimulationTime = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        jLabel2 = new JLabel(Constants.TEXT_LABEL_SEG);

        labelMaxTimeProcessLife = new JLabel("Max. Tiempo de Vida Proceso");
        sprMaxProcessTimeLife = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        jLabel4 = new JLabel(Constants.TEXT_LABEL_SEG);

        labelMaxTimeNextProcess = new JLabel("Max. Tiempo proximo Proceso");
        sprMaxTimeNextProcess = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));
        jLabel6 = new JLabel(Constants.TEXT_LABEL_SEG);

        labelMaxPriority = new JLabel("Max. Prioridad de Proceso");
        sprMaxPriority = new JSpinner(new SpinnerNumberModel(1, 1, 999, 1));

        btnStartSimulation = new MyJButton(listener, Commands.BTN_START_SIMULATION,"Iniciar Simulacion");
    }

    private void fill() {
        int yStart = 20;
        int xStart = 10;

        labelSimulationTime.setBounds(xStart, yStart,240,30);
        sprSimulationTime.setBounds(50,yStart+30,70,30);
        jLabel2.setBounds(130,yStart+30,50,30);
        yStart += 70;
        labelMaxTimeNextProcess.setBounds(xStart, yStart,240,30);
        sprMaxTimeNextProcess.setBounds(50,yStart+30,70,30);
        jLabel6.setBounds(130,yStart+30,50,30);

        yStart += 70;
        labelMaxTimeProcessLife.setBounds(xStart, yStart,240,30);
        sprMaxProcessTimeLife.setBounds(50,yStart+30,70,30);
        jLabel4.setBounds(130,yStart+30,50,30);

        yStart += 70;
        labelMaxPriority.setBounds(xStart, yStart,240,30);
        sprMaxPriority.setBounds(50,yStart+30,70,30);

        btnStartSimulation.setBounds(25, yStart+100, 150,30);

        add(labelSimulationTime);
        add(sprSimulationTime);
        add(jLabel2);

        add(labelMaxTimeNextProcess);
        add(sprMaxTimeNextProcess);
        add(jLabel6);

        add(labelMaxTimeProcessLife);
        add(sprMaxProcessTimeLife);
        add(jLabel4);

        add(labelMaxPriority);
        add(sprMaxPriority);

        add(btnStartSimulation);
    }

    public int getMaxProcessTimeLife(){
        return (int)sprMaxProcessTimeLife.getValue();
    }
    public int getMaxTimeIOOperation(){
        return (int) sprMaxPriority.getValue();
    }
    public int getMaxTimeNextProcess(){
        return (int)sprMaxTimeNextProcess.getValue();
    }
    public int getSimulationTime(){
        return (int)sprSimulationTime.getValue();
    }

    private JLabel jLabel2;
    private JLabel jLabel4;
    private JLabel jLabel6;
}
