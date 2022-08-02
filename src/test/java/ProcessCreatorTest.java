import simulator.models.Process;
import simulator.models.ProcessCreator;

public class ProcessCreatorTest {

    ProcessCreator creator;

    public static String getProcessInfo(Process process) {
        return process.getProcessName() + " " +
                process.getTimeLife();
    }

    public void testOutput(int iterations) {
        System.out.println(creator);
        try {
            for (int i = 0; i < iterations; i++) {
                System.out.print(creator.getTimeToNewProcess() + " ");
                Process process = creator.update();
                if (process != null) {
                    System.out.println("\n" + getProcessInfo(process));
                }
            }
            System.out.println("Pased");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int test(int iterations) {
        int count = 0;
        System.out.println(creator);
        try {
            for (int i = 0; i < iterations; i++) {
                if (creator.update() != null){
                    count++;
                };
            }
            System.out.println("Pased");
        } catch (IllegalArgumentException e) {
            System.out.println("ILLEGAL ARGUMENT EXCEPTION");
        }
        return count;
    }

    public static void main(String[] args) {
        ProcessCreatorTest test = new ProcessCreatorTest();
        ProcessCreator[] list = new ProcessCreator[]{
                //new ProcessCreator(1, 1, 1),
                //new ProcessCreator(5, 1, 1),
                //new ProcessCreator(5, 10, 10),
                new ProcessCreator(0, 0, 0)
        };
        for (ProcessCreator c: list) {
            test.creator = c;
            test.test(10000);
        }

        test.creator = new ProcessCreator(1,10,10);

        double times = 1000;
        double sum = 0;
        for (int i = 0; i < times; i++) {
            sum += test.test(120);
        }
        System.out.println(sum / times);
    }
}
