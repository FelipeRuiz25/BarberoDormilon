package simulator.views.panels;

import simulator.controllers.Commands;
import simulator.views.Constants;
import simulator.views.components.MyJButton;
import util.TimeParser;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelSimulationInfo extends JPanel {

    private MyJButton btnFinishSimulation;
    private JLabel timeClock;
    private JLabel textTimeClock;
    private JLabel timeAssign;
    private JLabel textTimeAssign;

    private JLabel chairs;
    private JLabel textChairs;

    private JLabel chairsOccupied;
    private JLabel textChairsOccupied;

    public PanelSimulationInfo(ActionListener listener) {
        this.setBorder(BorderFactory.createTitledBorder(Constants.TITTLE_PANEL_SIMULATION));
        this.btnFinishSimulation = new MyJButton(listener, Commands.BTN_FINISH_SIMULATION, Constants.TEXT_BTN_FINISH_SIMULATION);
        this.timeAssign = new JLabel(Constants.TEXT_LABEL_TIME_ASSIGN);
        this.textTimeAssign = new JLabel("0:00");
        this.timeClock = new JLabel(Constants.TEXT_LABEl_CLOCK);
        this.textTimeClock = new JLabel("0:00");
        this.chairs = new JLabel(Constants.TEXT_LABEL_CHAIRS_NUMBER);
        this.textChairs = new JLabel("numero");
        this.chairsOccupied = new JLabel(Constants.TEXT_LABEL_CHAIRS_OCCUPIED_NUMBER);
        this.textChairsOccupied = new JLabel("0");
        addToolTips();
        fill();
    }

    private void addToolTips(){
        this.btnFinishSimulation.setToolTipText("Presione si desea detener la simulacion");
    }

    private void fill() {
        this.setLayout(null);
        this.btnFinishSimulation.setBounds(260, 25, 110, 35);

        this.timeAssign.setBounds(20, 20, 150, 30);
        this.textTimeAssign.setBounds(150,20,100,30);

        this.timeClock.setBounds(20, 50, 150, 30);
        this.textTimeClock.setBounds(150, 50, 100, 30);

        this.chairs.setBounds(20, 80, 150, 30);
        this.textChairs.setBounds(150, 80, 100, 30);

        this.chairsOccupied.setBounds(210, 80, 150, 30);
        this.textChairsOccupied.setBounds(340, 80, 100, 30);

        add(btnFinishSimulation);
        add(timeAssign);
        add(textTimeAssign);
        add(timeClock);
        add(textTimeClock);
        add(chairs);
        add(textChairs);
        add(chairsOccupied);
        add(textChairsOccupied);
    }

    public void setTimeClock(int time) {
        this.textTimeClock.setText(TimeParser.secondsToString(time));
    }
    public void setSimulationTime(int time){
        this.textTimeAssign.setText(TimeParser.secondsToString(time));
    }

    public void setChairs(int chairs){
        textChairs.setText(String.valueOf(chairs));
    }

    public void setChairsOccupied(int chairsOccupied){
        textChairsOccupied.setText(String.valueOf(chairsOccupied));
    }
    public int getTimeRestUCP() {
        return Integer.parseInt(this.textTimeClock.getText());
    }

    public void resetComponentsUCP() {
        this.textTimeClock.setText("");
    }

    public void setEnableComponents(boolean status){
        this.btnFinishSimulation.setEnabled(!status);
        this.setEnabled(status);
    }
}
