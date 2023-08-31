import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        Stopwatch sw = new Stopwatch();
        SLList<Integer> test = new SLList<Integer>();
        List<Integer> Ns =  new ArrayList();
        List<Double> times = new ArrayList();
        List<Integer> ops = new ArrayList();
        double lastTime;
        for (int i = 0; i < 8; i++) {
            test = new SLList<Integer>();
            for (int j = 0; j < 1000 * pow(2, i); j++) {
                test.addLast(1);
            }
            lastTime = sw.elapsedTime();
            for (int j = 0; j < 10000; j++) {
                test.getLast();
            }
            times.add(sw.elapsedTime() - lastTime);
            Ns.add((int) (1000 * pow(2, i)));
            ops.add(10000);
        }
        printTimingTable(Ns, times, ops);
    }

}
