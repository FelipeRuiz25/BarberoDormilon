import simulator.models.Process;
import simulator.models.ProcessCreator;
import util.priorityQueue.PriorityQueue;

import java.util.Comparator;

public class PriorityQueueTest {

    public static void main(String[] args) {
        ProcessCreator creator = new ProcessCreator(100, 10, 100);
        PriorityQueue<Process> queue = new PriorityQueue<>(Comparator.comparing(Process::getPriority));
        for (int i = 0; i < 100; i++) {
            Process newProcess = creator.update();
            if (newProcess != null && queue.size() < 5){
                queue.push(newProcess);
            }
            System.out.println(queue.show());
            System.out.println(queue.size());
        }
    }
}
