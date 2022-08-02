package simulator.models;

import simulator.models.exceptions.CPUException;
import util.observer.Observable;
import util.observer.Observer;
import util.priorityQueue.PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class Simulator extends Observable implements Runnable{

    private final SimulationStatus status;
    private boolean stopSimulation;
    //Lista de proceso que no pudieron acceder a la barbería
    private ArrayList<Process> processesIgnored;
    private ArrayList<Process> processesAttended;
    private PriorityQueue<Process> readyQueue;
    private CPU cpu;
    private ProcessCreator creator;
    private int chairsNumber;

    public Simulator(int simulatorTime, CPU cpu, ProcessCreator creator, int chairs) {
        if (simulatorTime < 0 || chairs < 0 || cpu == null || creator == null)
            throw new IllegalArgumentException("Alguno de los parametros ingresados es inválido");
        //crea la lista de bloqueados
        processesIgnored = new ArrayList<>();
        processesAttended = new ArrayList<>();
        //Crea una cola con los asientos disponibles
        readyQueue = new PriorityQueue<>(Comparator.comparing(Process::getPriority));
        //Crea la el objeto encargado de manejar el turno de uso de la CPU
        this.cpu = cpu;
        this.creator = creator;
        this.chairsNumber = chairs;
        status = new SimulationStatus(simulatorTime);
    }

    /**
     * Obtiene la lista de los nombres de los procesos
     * que se encuentran en la cola de espera
     * @return lista de nombres de procesos
     */
    public ArrayList<String> getReadyQueue(){
        return getAsStringList(readyQueue);
    }

    /**
     * Obtiene la lista de los nombres de los procesos
     * que se encuentran en la lista de procesos no atendidos
     * @return lista de nombres de procesos
     */
    public ArrayList<String> getIgnoredList(){
        return getAsStringList(processesIgnored);
    }

    /**
     * Obtiene la lista de los nombres de los procesos
     * que se encuentran en la lista de procesos ya atendidos por el barbero
     * @return lista de nombres de procesos
     */
    public ArrayList<String> getAttendedList(){
        return getAsStringList(processesAttended);
    }

    /**
     * Obtiene una lista de strings con la información de los procesos asociados
     * a la lista o colección ingresada
     * @param list colección de procesos
     * @return lista de strings
     */
    private ArrayList<String> getAsStringList(Iterable<Process> list){
        ArrayList<String> stringList = new ArrayList<>();
        for (Process p : list) {
            stringList.add(
                    p.getProcessName()
                            +"   T vida: "+p.getTimeLife()
                            +"   Prioridad: "+p.getPriority()
            );
        }
        return stringList;
    }


    @Override
    public void run() {
        try {
            while (status.isRunning() && !stopSimulation) {
                this.update();
                //duerme el hilo segundos
                TimeUnit.SECONDS.sleep(1);
                //notifica a los observadores que actualicen la vista
                this.notifyObservers(status);
            }
        } catch (InterruptedException | CPUException e) {
            e.printStackTrace();
        }
    }

    public SimulationStatus update() throws CPUException {
        //Actualiza el tiempo que llevan los procesos en la cola de espera
        readyQueue.forEach(Process::addTimeReady);
        status.update();
        Process newProcess = creator.update();
        Process lastProcess = cpu.update();
        if (newProcess != null){
            status.processCreated = true;
            if (readyQueue.size() < chairsNumber){
                readyQueue.push(newProcess);
            }else {
                processesIgnored.add(newProcess);
                status.processIgnored = true;
            }
        }
        if (lastProcess != null){
            processesAttended.add(lastProcess);
            status.cpuExpirationTime = true;
        }
        if (cpu.isFree() && hasProcessesReady()){
            cpu.runProcess(readyQueue.poll());
            status.newProcessRunning = true;
        }
        return status;
    }

    /**
     * Indica si aún hay procesos en la cola de espera
     */
    public boolean hasProcessesReady(){
        return !readyQueue.isEmpty();
    }


    /**
     * Obtienen el tiempo de ejcución restante que la CPU
     * dispone para el proceso actual
     * @return segundos de ejecución para el proceso actual
     */
    public int getCPUTimeRemaining() {
        return cpu.getExecutionTimeRemaining();
    }

    public Process getRunningProcess(){
        return cpu.getProcessRunning();
    }

    public boolean hasCPUAvailable(){
        return cpu.isFree();
    }

    public SimulationStatus getStatus() {
        return status;
    }

    public CPU getCpu() {
        return cpu;
    }

    public ProcessCreator getProcessCreator() {
        return creator;
    }

    public int getChairsOccupiedNumber(){
        return readyQueue.size();
    }

    @Override
    public void addObserver(Observer o) {
        super.addObserver(o);
    }

    public void startSimulation() {
        new Thread(this).start();
    }

    public void finishSimulation(){
        stopSimulation = true;
        while (status.isRunning()){
            try {
                update();
                notifyObservers(status);
            } catch (CPUException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
