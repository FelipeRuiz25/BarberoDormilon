package simulator.models;

public class Process {

    //Contador para los nombres de los procesos
    private volatile static int sequential;

    private final String processName;
    //Segundos que el proceso va a estar en ejecución
    private final int timeLife;
    //Segundos que le quedan al proceso del tiempo de ejecucion
    private int lifeTimeRemaining;
    //Tiempo que lleva el proceso en la sala de espera
    private int timeReady;
    //Prioridad del proceso
    private int priority;

    /**
     * Crea un nuevo proceso y le asigna su tiempo de ejecución
     * @param timeLife tiempo de ejecución en segundos
     */
    public Process(int timeLife, int priority) {
        this.processName = String.valueOf(sequential);
        this.timeLife = timeLife;
        if (priority < 0)
            throw new IllegalArgumentException("Priority can't be null");
        this.priority = priority;
        lifeTimeRemaining = timeLife;
        sequential++;
    }

    public void update() {
        lifeTimeRemaining--;
    }

    public boolean isAlive() {
        return lifeTimeRemaining > 0;
    }

    public String getProcessName() {
        return processName;
    }

    public int getTimeLife() {
        return timeLife;
    }

    public int getLifeTimeRemaining() {
        return lifeTimeRemaining;
    }

    public static void resetSequential(){
        sequential = 0;
    }

    public int getTimeReady() {
        return timeReady;
    }

    /**
     * Actualiza el tiempo que lleva el proceso en la cola de espera
     */
    public void addTimeReady(){
        timeReady++;
    }

    public int getPriority() {
        return priority;
    }

    public static int getSequential() {
        return sequential;
    }


    @Override
    public String toString() {
        return "name='" + processName + '\'' +
                ", TL=" + timeLife +
                ", Pr=" + priority +
                ", TLR=" + lifeTimeRemaining;
    }
}
