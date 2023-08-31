package bearmaps;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;


public class KDTreeTest {
    public static void main(String[] args) {
        KDTreeTest k = new KDTreeTest();
        k.testNaive();
    }
    @Test
    public void testNaive() {
        List<Point> L = new ArrayList<>();
        L.add(new Point(1, 1));
        L.add(new Point(2, 2));
        L.add(new Point(2.5, 2.5));
        NaivePointSet S = new NaivePointSet(L);
        assertEquals(S.nearest(1.2, 0).getX(), 1.0, 0.000001);
        assertEquals(S.nearest(50, 50).getY(), 2.5, 0.000001);
    }

    @Test
    public void testKDConstructor() {
        List<Point> L = new ArrayList<>();
        L.add(new Point(1, 1));
        L.add(new Point(2, 2));
        L.add(new Point(2.5, 2.5));
        KDTree tree = new KDTree(L);
    }

    @Test
    public void testNearest() {
        List<Point> L = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }

        NaivePointSet set = new NaivePointSet(L);
        KDTree tree = new KDTree(L);
        double x;
        double y;
        for (int i = 0; i < 1000; i++) {
            x = Math.random() * 1000;
            y = Math.random() * 1000;
            Point p = new Point(x, y);
            assertEquals(Point.distance(p, set.nearest(x, y)),
                    Point.distance(p, tree.nearest(x, y)), .0000000001);
        }
    }
    @Test
    public void kdTreeConstructorSpeedTest() {
        System.out.print("Time to construct a n length KDTree: \n " + "n = 100: ");
        List<Point> L = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        KDTree tree = new KDTree(L);
        double x = Math.random() * 1000;
        double y = Math.random() * 1000;
        long start = System.currentTimeMillis();
        tree.nearest(x, y);
        long end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 1000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 10000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 10000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 100000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 100000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 1000000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 1000000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n");

    }
    @Test
    public void naiveTest() {
        System.out.print("Time to construct a n length NaivePointSet: \n " + "n = 100: ");
        List<Point> L = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        NaivePointSet s = new NaivePointSet(L);
        double x = Math.random() * 1000;
        double y = Math.random() * 1000;
        long start = System.currentTimeMillis();
        s.nearest(x, y);
        long end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 1000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 10000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 10000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 100000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 100000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 1000000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 1000000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms");
    }
    @Test
    public void kDTreeNearestSpeedTest() {

        System.out.print("Time to search a n length KDTree: \n " + "n = 100: ");
        List<Point> L = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        KDTree tree = new KDTree(L);
        double x = Math.random() * 1000;
        double y = Math.random() * 1000;
        long start = System.currentTimeMillis();
        tree.nearest(x, y);
        long end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 1000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 10000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 10000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        System.out.print("with 10000 tests.");
        start = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            x = Math.random() * 1000;
            y = Math.random() * 1000;
            tree.nearest(x, y);
        }
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 100000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 100000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n n = 10000000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 10000000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        tree = new KDTree(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        tree.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start + " ms \n");
    }
    @Test
    public void naiveSpeedTest() {

        System.out.print("Time to search a n length NaivePointSet: \n " + "n = 100: ");
        List<Point> L = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        NaivePointSet s = new NaivePointSet(L);
        double x = Math.random() * 1000;
        double y = Math.random() * 1000;
        long start = System.currentTimeMillis();
        s.nearest(x, y);
        long end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 1000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 10000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 10000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 100000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 100000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms \n n = 10000000: ");

        L = new ArrayList<Point>();
        for (int i = 0; i < 10000000; i++) {
            Point p = new Point(Math.random() * 1000, Math.random() * 1000);
            L.add(p);
        }
        s = new NaivePointSet(L);
        x = Math.random() * 1000;
        y = Math.random() * 1000;
        start = System.currentTimeMillis();
        s.nearest(x, y);
        end = System.currentTimeMillis();
        System.out.print(end - start +  " ms");
    }
}
