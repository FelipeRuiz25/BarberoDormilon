package simulator.views;

import com.formdev.flatlaf.intellijthemes.FlatCyanLightIJTheme;
import simulator.controllers.Commands;
import simulator.views.components.ModifiedFlowLayout;
import simulator.views.components.MyJButton;
import simulator.views.panels.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GuiManager extends JFrame {

    private ActionListener listener;
    private PanelSimulationInfo panelSimulationInfo;
    private PanelCreateProcess panelCreateProcess;
    private PanelProcessExecution panelProcessExecution;
    private PanelCreateSimulation panelCreateSimulation;

    private JList<String> readyQueue;
    private JList<String> ignoredList;
    private JList<String> attendedList;
    private JLabel labelReadyQueue;
    private JLabel labelIgnoredList;
    private JLabel labelAttendedList;
    private MyJButton btnOpenGraphs;

    public GuiManager(ActionListener listener) {
        super(Constants.TITTLE);
        setIconImage(new ImageIcon(Constants.ICON_PATH).getImage());
        //configurar tema de la aplicacion
        FlatCyanLightIJTheme.setup();
        this.listener = listener;
        this.panelSimulationInfo = new PanelSimulationInfo(listener);
        this.panelCreateProcess = new PanelCreateProcess(listener);
        this.panelProcessExecution = new PanelProcessExecution(listener);
        this.panelCreateSimulation = new PanelCreateSimulation(listener);
        this.readyQueue = new JList<>();
        this.ignoredList = new JList<>();
        this.attendedList = new JList<>();
        this.labelIgnoredList = new JLabel("Procesos Ignorados");
        this.labelReadyQueue = new JLabel("Procesos En Espera");
        this.labelAttendedList = new JLabel("Procesos Atendidos");
        this.btnOpenGraphs = new MyJButton(listener, Commands.BTN_OPEN_GRAPHICS, "Abrir Graficas");
        this.init();
    }

    private void init() {
        setSize(Constants.SIZE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        this.fill();
        addToolTips();
        setVisible(true);
    }

    public void setEnableBtnWakeProcess(boolean status){
        this.btnOpenGraphs.setEnabled(status);
    }

    public void setEnablePanelProcessExecution(boolean status){
        this.panelProcessExecution.setEnableComponents(status);
    }

    public void clearList() {
        DefaultListModel<String> listModelEmpty = new DefaultListModel<>();
        this.readyQueue.setModel(listModelEmpty);
    }

    public void setTimeRestProcess(int time) {
        this.panelProcessExecution.setTimeRest(time);
    }

    public void updateReadyQueue(ArrayList<String> namesProcess) {
        updateList(namesProcess, readyQueue);
    }

    public void updateIgnoredList(ArrayList<String> processList) {
        updateList(processList, ignoredList);
    }

    public void updateAttendedList(ArrayList<String> processList){
        updateList(processList, attendedList);
    }

    private void updateList(ArrayList<String> processList, JList<String> jList){
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String process : processList) {
            listModel.addElement(process);
        }
        jList.setModel(listModel);
    }

    public void updateIgnoredQueue(ArrayList<String> namesProcess) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String process : namesProcess) {
            listModel.addElement("Proceso "+process);
        }
        ignoredList.setModel(listModel);
    }

    public void updateAttendedQueue(ArrayList<String> namesProcess) {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (String process : namesProcess) {
            listModel.addElement("Proceso "+process);
        }
        attendedList.setModel(listModel);
    }

    public void setProcessActual(String nameProcess, int timeAssign, int timeRest) {
        this.panelProcessExecution.setProcessActual(nameProcess, timeAssign, timeRest);
    }

    private void fill() {
        this.panelCreateSimulation.setBounds(30,20, 200,580);

        this.setLayout(null);
        int move = 220;
        this.panelSimulationInfo.setBounds(30+move, 20, 400, 120);
        this.panelCreateProcess.setBounds(480+move, 20, 300, 120);

        Border listBorder = BorderFactory.createLineBorder(new Color(0, 0, 0), 1);
        readyQueue.setBorder(listBorder);
        ignoredList.setBorder(listBorder);
        attendedList.setBorder(listBorder);

        readyQueue.setLayout(new ModifiedFlowLayout());
        ignoredList.setLayout(new ModifiedFlowLayout());
        attendedList.setLayout(new ModifiedFlowLayout());

        JScrollPane jScrollPaneQueueProcess = new JScrollPane(readyQueue);
        jScrollPaneQueueProcess.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //ready Queue Bounds
        labelReadyQueue.setBounds(90+move, 150, 230, 20);
        jScrollPaneQueueProcess.setBounds(30+move, 170, 230, 430);
        this.add(jScrollPaneQueueProcess);

        this.readyQueue.setFont(Constants.FONT_LIST);
        this.ignoredList.setFont(Constants.FONT_LIST);
        this.attendedList.setFont(Constants.FONT_LIST);

        this.panelProcessExecution.setBounds(270+move, 160, 250, 210);

        JScrollPane jScrollPaneIgnoredList = new JScrollPane(ignoredList);
        jScrollPaneIgnoredList.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //ignored List Bounds
        labelIgnoredList.setBounds(600+move, 150, 230, 20);
        jScrollPaneIgnoredList.setBounds(530+move, 170, 250,200);
        this.add(jScrollPaneIgnoredList);

        JScrollPane jScrollPaneAttendedProcess = new JScrollPane(attendedList);
        jScrollPaneAttendedProcess.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        //attended List Bounds
        labelAttendedList.setBounds(600+move, 380, 230, 20);
        jScrollPaneAttendedProcess.setBounds(530+move, 400, 250,200);
        this.add(jScrollPaneAttendedProcess);

        add(panelProcessExecution);
        this.btnOpenGraphs.setBounds(330+move, 380, 140, 30);
        add(panelSimulationInfo);
        add(panelCreateProcess);
        add(labelReadyQueue);
        add(labelIgnoredList);
        add(labelAttendedList);
        add(panelProcessExecution);
        add(btnOpenGraphs);
        add(panelCreateSimulation);
    }

    public void resetComponentsPanelCurrentProcess(){
        this.panelProcessExecution.resetComponents();
    }

    private void addToolTips(){
        this.btnOpenGraphs.setToolTipText(Constants.TOOL_TIP_BTN_OPEN_GRAPHICS);
    }

    public void clearLists() {
        DefaultListModel<String> listModelEmpty = new DefaultListModel<>();
        this.readyQueue.setModel(listModelEmpty);
        this.ignoredList.setModel(listModelEmpty);
        this.attendedList.setModel(listModelEmpty);
    }

    public void resetSpinnerUCP() {
        this.panelSimulationInfo.resetComponentsUCP();
    }


    public void resetNameProcess() {
        this.panelCreateProcess.resetNameProcess();
    }

    public PanelCreateSimulation getPanelCreateSimulation() {
        return panelCreateSimulation;
    }

    public PanelSimulationInfo getPanelSimulationInfo() {
        return panelSimulationInfo;
    }

    public PanelCreateProcess getPanelCreateProcess() {
        return panelCreateProcess;
    }

    public PanelProcessExecution getPanelProcessExecution() {
        return panelProcessExecution;
    }
}
