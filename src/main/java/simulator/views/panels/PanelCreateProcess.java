package simulator.views.panels;

import simulator.views.Constants;
import util.TimeParser;

import javax.swing.*;
import java.awt.event.ActionListener;

public class PanelCreateProcess extends JPanel {

    private JLabel labelNameProcess;
    private JLabel textFieldNameProcess;
    private JLabel labelMaxPriority;
    private JLabel textFieldMaxPriority;

    private JLabel labelMaxTimeProcessLife;
    private JLabel textFieldMaxTimeProcessLife;
    private JLabel labelTimeToNewProcess;
    private JLabel textFieldTimeToNewProcess;

    public PanelCreateProcess(ActionListener listener) {
        setBorder(BorderFactory.createTitledBorder(Constants.TITTLE_PANEL_CREATION_PROCESS));
        init(listener);
    }

    private void init(ActionListener listener) {
        this.labelNameProcess = new JLabel(Constants.TEXT_LABEL_NAME_PROCESS);
        this.textFieldNameProcess = new JLabel("0");
        this.labelMaxPriority = new JLabel(Constants.TEXT_LABEL_MAX_PRIORITY);
        this.labelMaxTimeProcessLife = new JLabel(Constants.TEXT_LABEL_MAX_TIME_PROCESS_LIFE);
        this.labelTimeToNewProcess = new JLabel(Constants.TIME_TO_NEW_PROCESS);
        this.textFieldMaxPriority = new JLabel("0");
        this.textFieldMaxTimeProcessLife = new JLabel("0:00");
        this.textFieldTimeToNewProcess = new JLabel("0:00");
        fill();
    }

    private void fill() {
        this.setLayout(null);
        this.labelTimeToNewProcess.setBounds(20, 20, 200, 30);
        this.textFieldTimeToNewProcess.setBounds(220, 20, 200, 30);

        this.labelNameProcess.setBounds(20, 40, 200, 30);
        this.textFieldNameProcess.setBounds(220, 40, 200, 30);

        this.labelMaxTimeProcessLife.setBounds(20, 60, 200, 30);
        this.textFieldMaxTimeProcessLife.setBounds(220, 60, 200, 30);

        this.labelMaxPriority.setBounds(20, 80, 200, 30);
        this.textFieldMaxPriority.setBounds(220, 80, 200, 30);

        add(labelTimeToNewProcess);
        add(textFieldTimeToNewProcess);
        add(labelNameProcess);
        add(textFieldNameProcess);
        add(labelMaxTimeProcessLife);
        add(textFieldMaxTimeProcessLife);
        add(labelMaxPriority);
        add(textFieldMaxPriority);
    }
    public void addCount() {
        this.textFieldNameProcess.setText(String.valueOf(Integer.parseInt(this.textFieldNameProcess.getText()) + 1));
    }

    public int getNameProcess() {
        return Integer.parseInt(this.textFieldNameProcess.getText());
    }

    public void resetNameProcess() {
        this.textFieldNameProcess.setText(String.valueOf(0));
    }


    public void setNameProcess(String nameProcess){
        textFieldNameProcess.setText(nameProcess);
    }
    public void setMaxPriority(int maxTimeIO){
        textFieldMaxPriority.setText(String.valueOf(maxTimeIO));
    }
    public void setMaxTimeProcessLife(int maxTimeProcessLife){
        textFieldMaxTimeProcessLife.setText(TimeParser.secondsToString(maxTimeProcessLife));
    }
    public void setTimeToNewProcess(int timeToNewProcess){
        textFieldTimeToNewProcess.setText(TimeParser.secondsToString(timeToNewProcess));
    }

    public void setCreatorInfo(int maxTimeIO ,int maxTimeProcessLife){
        setMaxPriority(maxTimeIO);
        setMaxTimeProcessLife(maxTimeProcessLife);
    }
}
