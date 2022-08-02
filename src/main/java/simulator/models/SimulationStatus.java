package simulator.models;

/**
 *
 */
public class SimulationStatus {

    /**
     * Tiempo Total(en segundos) de ejecucion de la simulaci�n
     */
    final int maxSimulatorTime;
    /**
     * Tiempo transcurrido de la simulacion
     */
    int simulatorClock;

    boolean usingCPU, processCreated, cpuExpirationTime, processIgnored, newProcessRunning;

    public SimulationStatus(int simulatorTime) {
        this.maxSimulatorTime = simulatorTime;
    }

    public boolean isRunning(){
        return maxSimulatorTime > simulatorClock;
    }

    public void update() {
        simulatorClock++;
        processCreated = false;
        cpuExpirationTime = false;
        processIgnored = false;
        newProcessRunning = false;
        usingCPU = false;
    }

    public int getMaxSimulatorTime() {
        return maxSimulatorTime;
    }

    public int getSimulatorClock() {
        return simulatorClock;
    }

    public boolean processCreated() {
        return processCreated;
    }

    public boolean cpuExpirationTime() {
        return cpuExpirationTime;
    }

    public boolean processIgnored() {
        return processIgnored;
    }

    public boolean newProcessRunning() {
        return newProcessRunning;
    }

    @Override
    public String toString() {
        return "{Simulation time=" + maxSimulatorTime +
                ", clock=" + simulatorClock +
                ", processCreated=" + processCreated +
                ", cpuExpTime=" + cpuExpirationTime +
                ", processIgnored=" + processIgnored +
                ", newProcessRunning=" + newProcessRunning +
                "}";
    }
}
