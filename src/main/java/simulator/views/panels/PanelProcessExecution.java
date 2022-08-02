package simulator.views.panels;

import simulator.controllers.Commands;
import simulator.views.Constants;
import simulator.views.components.MyJButton;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.event.ActionListener;

public class PanelProcessExecution extends JPanel {

    private ActionListener listener;
    private JLabel labelNameProcess;
    private JLabel labelTimeAssign;
    private JLabel labelTimeRest;
    private JLabel textFieldNameProcess;
    private JLabel textFieldTimeAssign;
    private JLabel textFieldTimeRest;

    private JProgressBar progressBar;

    public PanelProcessExecution(ActionListener listener) {
        this.listener = listener;
        setBorder(BorderFactory.createTitledBorder(null, "Proceso en ejecucion", TitledBorder.CENTER, TitledBorder.TOP));
        init();
    }

    private void init() {
        this.labelNameProcess = new JLabel(Constants.TEXT_LABEL_NAME_PROCESS);
        this.labelTimeAssign = new JLabel("Tiempo vida: ");
        this.labelTimeRest = new JLabel("Tiempo restante: ");
        this.textFieldNameProcess = new JLabel();
        this.textFieldTimeAssign = new JLabel();
        this.textFieldTimeRest = new JLabel();
        this.progressBar = new JProgressBar(JProgressBar.HORIZONTAL);;
        progressBar.setStringPainted(true);
        this.fill();
    }
    
    public void setProcessActual(String nameProcessActual, int timeAssign, int timeRest) {
        this.textFieldNameProcess.setText(nameProcessActual);
        this.textFieldTimeAssign.setText(String.valueOf(timeAssign));
        setTimeRest(timeRest);
    }

    public void setTimeRest(int timeRest){
        this.textFieldTimeRest.setText(String.valueOf(timeRest));
    }

    public void setEnableComponents(boolean status){
        this.setEnabled(status);
    }

    public void resetComponents(){
        this.textFieldTimeAssign.setText("0");
        this.textFieldTimeRest.setText("0");
        this.textFieldNameProcess.setText("0");
    }

    public void setProgressBarTimeTask(int timeTask){
        progressBar.setMaximum(timeTask);
    }

    public void setProgressBarValue(int value){
        progressBar.setValue(value);
    }

    private void fill() {
        this.setLayout(null);
        this.labelNameProcess.setBounds(20, 40, 120, 20);
        this.labelTimeAssign.setBounds(20, 70, 120, 20);
        this.labelTimeRest.setBounds(20, 100, 120, 20);
        this.textFieldNameProcess.setBounds(170, 40, 120, 25);
        this.textFieldTimeAssign.setBounds(170, 70, 120, 25);
        this.textFieldTimeRest.setBounds(170, 100, 120, 25);
        this.progressBar.setBounds(20, 150, 210, 25);
        add(labelNameProcess);
        add(labelTimeAssign);
        add(labelTimeRest);
        add(textFieldNameProcess);
        add(textFieldTimeAssign);
        add(textFieldTimeRest);
        add(progressBar);
    }

    public void clear() {
        textFieldNameProcess.setText("");
        textFieldTimeAssign.setText("");
        textFieldTimeRest.setText("");
        progressBar.setValue(0);
    }
}
