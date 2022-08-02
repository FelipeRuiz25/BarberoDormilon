package simulator.models;

import util.RandomNumberGenerator;

import java.util.ArrayList;

public class ProcessCreator {

    /**
     * prioridad maxima que puede tener un proceso
     */
    private final int maxPriority;

    /**
     * Tiempo maximo de vida que puede tener un proceso
     */
    private final int maxTimeProcessLife;
    /**
     * Tiempo maximo en que se creeara el proceso luego de la crecion del ultimo proceso
     */
    private final int maxTimeNextProcess;
    /**
     * Tiempo restante para la creacion de un nuevo proceso
     */
    private int timeToNewProcess;

    /**
     * procesos creados por el creador de procesos
     */
    private ArrayList<Process> processes;

    /**
     * Define el creador de procesos con sus parametros limite para la creacion de nuevos
     * procesos
     * @param maxPriority Tiempo maximo que puede tomar una operacion de entrada salida
     * @param maxTimeProcessLife Tiempo maximo de vida que puede tener un proceso
     * @param maxTimeNextProcess Tiempo maximo en que se creeara el proceso luego de la crecion del ultimo proceso
     */
    public ProcessCreator(int maxPriority, int maxTimeProcessLife, int maxTimeNextProcess) {
        if (maxPriority < 0 || maxTimeProcessLife < 0 || maxTimeNextProcess < 0)
            throw new IllegalArgumentException("todos los parametros deben ser mayores a cero");
        this.maxPriority = maxPriority;
        this.maxTimeProcessLife = maxTimeProcessLife;
        this.maxTimeNextProcess = maxTimeNextProcess;
        this.timeToNewProcess = 1;
        this.processes = new ArrayList<>();
    }

    /**
     * Crea un nuevo proceso con sus parametros aleatorios
     * @see Process
     * @return proceso nuevo
     */
    public Process createProcess(){
        // Define el tiempo de vida del proceso
        int lifeTime = RandomNumberGenerator.getRandIntBetween(1, maxTimeProcessLife +1);
        // Define el tiempo en que se creara el siguiente proceso
        timeToNewProcess = RandomNumberGenerator.getRandIntBetween(1, maxTimeNextProcess+1);
        // Define la prioridad del bloqueo
        int priority = RandomNumberGenerator.getRandIntBetween(1, maxPriority+1);
        Process process = new Process(lifeTime, priority);
        processes.add(process);
        return process;
    }

    public boolean itsTimeToCreate(){
        return timeToNewProcess == 0;
    }

    public Process update(){
        timeToNewProcess--;
        if (itsTimeToCreate()){
            return createProcess();
        }
        return null;
    }

    public int getTimeToNewProcess() {
        return timeToNewProcess;
    }

    public int getMaxPriority() {
        return maxPriority;
    }

    public int getMaxTimeProcessLife() {
        return maxTimeProcessLife;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    @Override
    public String toString() {
        return "ProcessCreator{" +
                "max Priority=" + maxPriority +
                ", maxTimeProcessLive=" + maxTimeProcessLife +
                ", maxTimeNextProcess=" + maxTimeNextProcess +
                '}';
    }
}
