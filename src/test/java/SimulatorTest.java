import simulator.models.CPU;
import simulator.models.ProcessCreator;
import simulator.models.SimulationStatus;
import simulator.models.Simulator;
import simulator.models.exceptions.CPUException;

public class SimulatorTest {

    public SimulatorTest() {
    }

    public static void main(String[] args) {
        CPU cpu = new CPU();
        ProcessCreator creator = new ProcessCreator(5, 100,1);
        Simulator simulator = new Simulator(100,cpu, creator,5);
        SimulationStatus status = simulator.getStatus();
        while (status.isRunning()){
            try {
                System.out.println(simulator.update());
                System.out.println(simulator.getCpu().getProcessRunning());
            } catch (CPUException e) {
                e.printStackTrace();
            }
        }
    }
}
