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

/**
 * Clase controlador encargada de unir la lógica del la simulación con la vista
 */
public class Controller implements ActionListener, Observer {

    //Frame Principal
    private GuiManager guiManager;
    //Clase simulación
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
                //poner graficas
                new ViewGraphics(simulator.getProcessesAttendedTimeLife(),simulator.getProcessesAttendedPriority(),simulator.getProccesesCountTotal(),simulator.getProcessesAttendedName(),simulator.getProcessesAttendedTimeReady());
                break;
            case Commands.BTN_START_SIMULATION:
                if(simulator != null) finishSimulation();
                createSimulation();
                break;
        }
    }

    /**
     * Limpia la pantalla principal y realiza el proceso de la simulación hasta finalizarla
     */
    private void finishSimulation() {
        guiManager.resetNameProcess();
        guiManager.resetSpinnerUCP();
        guiManager.resetComponentsPanelCurrentProcess();
        guiManager.clearLists();
        if (simulator != null && simulator.getStatus().isRunning()) {
            //Si ya hay una simulación y no se ha terminado, ejecuta toda la simulación
            simulator.finishSimulation();
            Output.showInfoMessage("Simulacion finalizada.");
        }
        Process.resetSequential();
    }

    /**
     * Crea una simulción a partir de los parametros ingresados en la
     */
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
