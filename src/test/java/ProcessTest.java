import simulator.models.Process;
import org.junit.jupiter.api.Test;

public class ProcessTest {

    Process process;

    public void test() {
        System.out.println("Process: " +
                process.getProcessName() +
                "TL " + process.getTimeLife()
        );
        while (process.isAlive()) {
            process.update();
        }
        System.out.println("Passed");
    }

    public void outputTest() {
        System.out.println("Process: " +
                process.getProcessName() +
                " TL " + process.getTimeLife()
        );
        do {
            System.out.println(process.getLifeTimeRemaining());
            process.update();
        } while (process.isAlive());
        System.out.println("Passed");
    }

    @Test
    public void runTest(){
        ProcessTest test = new ProcessTest();
        Process[] list = new Process[]{
                new Process(10,9),
                new Process(-1,9),
                new Process(-1,9),
                new Process(10,9),
                new Process(10,9),
                new Process(0,9),
                new Process(10,9)
        };
        for (Process p: list) {
            test.process = p;
            test.outputTest();
        }
    }
}
