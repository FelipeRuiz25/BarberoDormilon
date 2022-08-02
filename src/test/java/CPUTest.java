import simulator.models.CPU;
import simulator.models.Process;
import simulator.models.exceptions.CPUException;
import util.priorityQueue.PriorityQueue;

import java.util.Comparator;

public class CPUTest {

    public static void main(String[] args) {
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparing(Process::getPriority));
        readyQueue.push(new Process(10, 40));
        readyQueue.push(new Process(-1, 40));
        readyQueue.push(new Process(-1, 1));
        readyQueue.push(new Process(10, 5));
        readyQueue.push(new Process(10, 5));
        readyQueue.push(new Process(0, 0));
        readyQueue.push(new Process(10, 10));

        CPU cpu = new CPU();
        for (int i = 0; i < 100; i++) {
            if (cpu.isFree() && !readyQueue.isEmpty()){
                try {
                    cpu.runProcess(readyQueue.poll());
                    System.out.println("Proceso en ejecucion " + cpu.getProcessRunning());
                } catch (CPUException e) {
                    System.out.println(e.getCode());
                }
            }else {
                System.out.print(cpu.getProcessRunning());
                Process process = cpu.update();
                if (process != null && process.isAlive()){
                    readyQueue.push(process);
                }
            }
            System.out.println(" IDLE? = "+ cpu.isFree());
        }
    }
}
