package simulator.controllers;

import simulator.models.*;
import simulator.models.Process;
import simulator.views.GuiManager;
import simulator.views.ViewGraphics;
import simulator.views.components.Output;
import util.observer.Observer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Controller implements ActionListener, Observer {

    private GuiManager guiManager;
    private Simulator simulator;

    public Controller() {
        this.guiManager = new GuiManager(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        switch (e.getActionCommand()) {
            case Commands.BTN_FINISH_SIMULATION:
                finishSimulation();
                break;
            case Commands.BTN_OPEN_GRAPHICS:
                new ViewGraphics(getListTimeOfLife(),getListTimeOfBlock(),getListTimeReady());
                break;
            case Commands.BTN_START_SIMULATION:
                if(simulator != null) finishSimulation();
                createSimulation();
                break;
        }
    }

    private void finishSimulation() {
        guiManager.resetNameProcess();
        guiManager.resetSpinnerUCP();
        guiManager.resetComponentsPanelCurrentProcess();
        guiManager.clearLists();
        Process.resetSequential();
        if (simulator != null && simulator.getStatus().isRunning()) {
            simulator.finishSimulation();
            Output.showInfoMessage("Simulacion finalizada.");
        }
    }

    private void createSimulation() {
        int chairs = guiManager.getPanelCreateSimulation().getChairsNumber();
        guiManager.getPanelSimulationInfo().setChairs(chairs);
        ProcessCreator creator = new ProcessCreator(
                guiManager.getPanelCreateSimulation().getMaxPriority(),
                guiManager.getPanelCreateSimulation().getMaxProcessTimeLife(),
                guiManager.getPanelCreateSimulation().getMaxTimeNextProcess()
        );
        guiManager.getPanelCreateProcess().setCreatorInfo(
                creator.getMaxPriority(), creator.getMaxTimeProcessLife()
        );
        int simulationTime = guiManager.getPanelCreateSimulation().getSimulationTime();
        guiManager.getPanelSimulationInfo().setSimulationTime(simulationTime);

        simulator = new Simulator(simulationTime,new CPU(), creator, chairs);
        simulator.addObserver(this);
        simulator.startSimulation();
    }

    @Override
    public void update(SimulationStatus status) {
        updateTime();
        if (status.processCreated()){
            guiManager.getPanelCreateProcess().addCount();
            if (status.processIgnored()){
                guiManager.updateIgnoredList(simulator.getIgnoredList());
            }else {
                guiManager.updateReadyQueue(simulator.getReadyQueue());
                updateOccupiedNumber();
            }
        }
        if (status.newProcessRunning()){
            guiManager.updateReadyQueue(simulator.getReadyQueue());
        }
        if (!simulator.hasCPUAvailable()){
            updateProcessRunningView();
        }else {
            guiManager.getPanelProcessExecution().clear();
        }
        if (status.cpuExpirationTime()){
            guiManager.updateAttendedList(simulator.getAttendedList());
            updateOccupiedNumber();
        }
    }

    private void updateOccupiedNumber() {
        guiManager.getPanelSimulationInfo()
                .setChairsOccupied(simulator.getChairsOccupiedNumber());
    }

    private void updateTime() {
        int timeClock = simulator.getStatus().getSimulatorClock();
        guiManager.getPanelSimulationInfo().setTimeClock(timeClock);
        int timeNextProcess = simulator.getProcessCreator().getTimeToNewProcess();
        guiManager.getPanelCreateProcess().setTimeToNewProcess(timeNextProcess);
    }

    private ArrayList<Integer> getListTimeOfLife(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Process process : simulator.getProcessCreator().getProcesses()){
            list.add(process.getTimeLife());
        }
        return list;
    }

    private ArrayList<Integer> getListTimeReady(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Process process : simulator.getProcessCreator().getProcesses()){
            list.add(process.getTimeReady());
        }
        return list;
    }

    private ArrayList<Integer> getListTimeOfBlock(){
        ArrayList<Integer> list = new ArrayList<>();
        for (Process process : simulator.getProcessCreator().getProcesses()){
            try {
                throw new Exception("Lista de procesos bloqueados eliminada");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return list;
    }


    private void updateProcessRunningView() {
        if (!simulator.hasCPUAvailable()) {
            Process process = simulator.getRunningProcess();
            guiManager.setProcessActual(
                    process.getProcessName(),
                    process.getTimeLife(),
                    process.getLifeTimeRemaining());
            guiManager.getPanelProcessExecution().setProgressBarTimeTask(simulator.getCpu().getExecutionTime());
            guiManager.getPanelProcessExecution().setProgressBarValue(
                    simulator.getCpu().getExecutionTime() - simulator.getCPUTimeRemaining()
            );
        }
    }
}
