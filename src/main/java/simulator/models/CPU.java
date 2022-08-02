package simulator.models;

import simulator.models.exceptions.CPUException;
import simulator.models.exceptions.ErrorCode;

public class CPU {

    private int executionTime;
    private int executionTimeRemaining;

    private Process processRunning;

    /**
     * Crea la unidad de CPU
     */
    public CPU() {}

    public void runProcess(Process process) throws CPUException {
        if (process == null) throw new CPUException(ErrorCode.NO_ASSIGNED_PROCESS);
        processRunning = process;
        executionTime = process.getTimeLife();
        executionTimeRemaining = executionTime;
    }

    public Process liberateCPU(){
        executionTimeRemaining = 0;
        executionTime = 0;
        Process process = processRunning;
        processRunning = null;
        return process;
    }

    public int getExecutionTimeRemaining() {
        return executionTimeRemaining;
    }

    public Process getProcessRunning() {
        return processRunning;
    }

    public Process update(){
        if (!isFree()){
            executionTimeRemaining--;
            //Ejecuta el proceso
            processRunning.update();
            //Si se acaba el quantum o el tiempo de vida del proceso libera la CPU
            if (executionTimeRemaining < 0 || !processRunning.isAlive()){
                return liberateCPU();
            }
        }
        return null;
    }

    public boolean isFree(){
        return processRunning == null;
    }
}
