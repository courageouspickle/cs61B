package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.pow;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {
    @Test
    public void testSameItem() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, 2.0);
        try {
            heap.add(1, 2.0);
        } catch(IllegalArgumentException e) {
            System.out.println("correct");
        }
    }
    @Test
    public void removeLastElement() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, .1);
        heap.add(0, 0.0);
        heap.add(4, .6);
        for (int i = 0; i < 100; i++) {
            heap.add(i + 10, Math.random());
        }
        heap.add(9, .3);
        while (heap.size() > 0) {
            System.out.println(heap.removeSmallest());
        }

    }
    @Test
    public void testRemoveSmallest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> lame = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i++) {
            int x = i;
            double y = Math.random() * 10;
            heap.add(x, y);
            lame.add(x, y);
        }
        double last = 0;
        /* this part only works when parts of ArrayMinPQ mare made public
        (Against API rules)
        for (int i = 0; i < 10000 - 1; i++) {
            System.out.println(heap.getMinPrio());
            double h = heap.removeSmallest();
            double l = lame.removeSmallest();
            assertEquals("my next prio: " + h + " expected: " + l, h, l, .0000001);
            if (last > heap.getMinPrio()) {
                System.out.println("HEYyYyYYYYyYYy");
            }
            if (heap.size() >= 2) {
                last = heap.getMinPrio();
            }
        }*/
    }
    @Test
    public void printHeapTest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> lame = new NaiveMinPQ<>();
        for (int i = 0; i < 10; i++) {
            int x = (int) Math.round(Math.random() * 10000);
            double y = Math.random() * 10;
            heap.add(x, y);
            lame.add(x, y);
        }
        /* this part only works when parts of ArrayMinPQ mare made public
        (Against API rules)
        ArrayList<Double> intHeap = new ArrayList<>();
        for (ArrayHeapMinPQ.Node n : heap.heap) {
            if (n != null)
            intHeap.add(n.prio);
        }*/
    }
    @Test
    public void addSpeedTest() {
        Stopwatch sw = new Stopwatch();
        /*
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            heap.add(i, Math.random());
        }
        System.out.println("time to build heap, n = 100000: " + sw.elapsedTime());*/
        NaiveMinPQ<Integer> lame = new NaiveMinPQ<>();
        for (int i = 0; i < 100000; i++) {
            lame.add(i, Math.random());
        }
        System.out.println("time to build naive, n = 100000: " + sw.elapsedTime());
    }
    @Test
    public void changePriorityTestSimple() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        heap.add(1, .1);
        heap.add(2, .2);
        heap.add(3, .3);
        heap.changePriority(3, .4);
    }

    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<Integer> heap = new ArrayHeapMinPQ<>();
        NaiveMinPQ<Integer> lame = new NaiveMinPQ<>();
        for (int i = 0; i < 10000; i++) {
            double y = Math.random();
            heap.add(i, y);
            lame.add(i, y);
        }
        for (int i = 5000; i < 10; i++) {
            heap.changePriority(i, .001);
            lame.changePriority(i, .001);
        }
        for (int i = 0; i < 10000; i++) {
            double h = heap.removeSmallest();
            double l = lame.removeSmallest();
            assertEquals("my next prio: " + h + " expected: " + l, h, l, .0000001);
        }
    }

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
    @Test
    public void finalTimingTest() {
        Stopwatch sw = new Stopwatch();
        ArrayHeapMinPQ<Integer> test;
        List<Integer> Ns =  new ArrayList();
        List<Double> times = new ArrayList();
        List<Integer> ops = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ops.add(1000);
        }
        double lastTime;
        for (int i = 0; i < 10; i++) {
            test = new ArrayHeapMinPQ<>();
            for (int j = 1; j < pow(2, i) * 1000; j++) {
                test.add(j, Math.random());

            }
            lastTime = sw.elapsedTime();
            for (int j = 1; j < 1000; j++) {
                test.changePriority(j, Math.random());
            }
            times.add(sw.elapsedTime() - lastTime);
            Ns.add((int) (1000 * pow(2, i)));
        }
        printTimingTable(Ns, times, ops);

        NaiveMinPQ<Integer> test1;
        Ns =  new ArrayList();
        times = new ArrayList();
        for (int i = 0; i < 10; i++) {
            test1 = new NaiveMinPQ<>();
            for (int j = 1; j < pow(2, i) * 1000; j++) {
                test1.add(j, Math.random());

            }
            lastTime = sw.elapsedTime();
            for (int j = 1; j < pow(2, i) * 100; j++) {
                test1.changePriority(j, Math.random());
            }
            times.add(sw.elapsedTime() - lastTime);
            Ns.add((int) (1000 * pow(2, i)));
        }
        printTimingTable(Ns, times, ops);

    }

}
