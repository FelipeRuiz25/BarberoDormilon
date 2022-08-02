package simulator.models;

import simulator.models.exceptions.CPUException;
import simulator.models.exceptions.ErrorCode;

public class CPU {

    private int totalIdleTime;

    private Process processRunning;

    /**
     * Crea la unidad de CPU
     */
    public CPU() {}

    public void runProcess(Process process) throws CPUException {
        if (process == null) throw new CPUException(ErrorCode.NO_ASSIGNED_PROCESS);
        processRunning = process;
    }

    public Process liberateCPU(){
        Process process = processRunning;
        processRunning = null;
        return process;
    }

    public int getExecutionTimeRemaining() {
        return processRunning.getLifeTimeRemaining();
    }

    public Process getProcessRunning() {
        return processRunning;
    }

    public int getExecutionTime() {
        return processRunning.getTimeLife();
    }

    public Process update(){
        if (!isFree()){
            //Ejecuta el proceso
            processRunning.update();
            //Si se acaba el quantum o el tiempo de vida del proceso libera la CPU
            if (!processRunning.isAlive()){
                return liberateCPU();
            }
        }else {
            totalIdleTime++;
        }
        return null;
    }

    public boolean isFree(){
        return processRunning == null;
    }
}
