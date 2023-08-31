
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.*;

/**
 * Class that collects timing information about AList construction.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        Stopwatch sw = new Stopwatch();
        AList<Integer> test;
        List<Integer> Ns =  new ArrayList();
        List<Double> times = new ArrayList();
        double lastTime;
        for (int i = 0; i < 8; i++) {
            test = new AList();
            lastTime = sw.elapsedTime();
            for (int j = 0; j < pow(2, i) * 1000; j++) {
                test.addLast(1);
            }
            times.add(sw.elapsedTime() - lastTime);
            Ns.add((int) (1000 * pow(2, i)));
        }
        printTimingTable(Ns, times, Ns);
    }

}
