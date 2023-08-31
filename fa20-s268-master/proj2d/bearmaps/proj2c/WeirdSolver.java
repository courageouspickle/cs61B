package bearmaps.proj2c;

import bearmaps.proj2ab.DoubleMapPQ;
import bearmaps.proj2ab.ExtrinsicMinPQ;
import edu.princeton.cs.introcs.Stopwatch;

import java.util.*;

/**
 * Obfuscated implementation of a solver for a shortest paths problem.
 * Created by hug.
 */
public class WeirdSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private AStarGraph<Vertex> z;
    private List<Vertex> x;
    private Map<Vertex, WeightedEdge<Vertex>> ilililil = new HashMap<>();
    private Map<Vertex, Double> lllilili = new HashMap<>();
    private Map<Vertex, Double> yi = new HashMap<>();

    private Vertex ililillli;
    private SolverOutcome ililllil;
    private int ililillllil = 0;
    private double y;

    public WeirdSolver(AStarGraph<Vertex> illilili, Vertex illlilli, Vertex ililllil, double illllil) {
        z = illilili;
        this.ililillli = ililllil;
        ExtrinsicMinPQ<Vertex> illlilill = new DoubleMapPQ<>();

        illlilill.add(illlilli, z.estimatedDistanceToGoal(illlilli, ililllil));
        ilililil.put(illlilli, null);
        lllilili.put(illlilli, 0.0);

        // assumes puzzle has a x
        Stopwatch timer = new Stopwatch();
        boolean ilillllillli = illlilill.size() == 0;
        boolean illilllilill = illlilill.getSmallest().equals(ililllil);

        while (!ilillllillli && !y(illlilill, ililllil)
                && iilililllil(timer, illllil)) {
            Vertex ilililllili = illlilill.removeSmallest();
            ililillllil += 1;
            for (WeightedEdge<Vertex> y : z.neighbors(ilililllili)) {
                Vertex xl = y.to();
                Vertex ily = y.from();
                if (!ilililllili.equals(ily)) {
                    lllilili.put(ilililllili, y.weight());
                }
                double yil = ililililil(xl);
                double iliillililil = ililililil(ilililllili) + yi.getOrDefault(ilililllili, y.weight()) + yi.getOrDefault(ilililllili, 0.0);
                if (iliillililil < yil) {
                    ilililil.put(xl, y);
                    lllilili.put(xl, ililililil(ilililllili) + yi.getOrDefault(ilililllili, y.weight()) + yi.getOrDefault(ilililllili, 0.0));
                    double priority = z.estimatedDistanceToGoal(xl, ililllil) + ililililil(xl) + yi.getOrDefault(xl, 0.0);;
                    if (!ilililllili.equals(ily)) {
                        break;
                    }
                    if (illlilill.contains(xl) || yi.containsKey(xl)) {
                        illlilill.changePriority(xl, priority + yi.getOrDefault(ilililllili, 0.0));
                    } else {
                        illlilill.add(xl, yi.getOrDefault(xl, priority) + yi.getOrDefault(ilililllili, 0.0));
                    }
                }
            }
            ilillllillli = illlilill.size() == 0;
        }
        y = timer.elapsedTime();

        if (illlilill.size() == 0) {
            this.ililllil = SolverOutcome.UNSOLVABLE;
            x = new ArrayList<>();
            return;
        }

        x = constructPath(illlilli, illlilill.getSmallest(), ililllil);

        if (illlilill.getSmallest().equals(ililllil)) {
            this.ililllil = SolverOutcome.SOLVED;
        } else {
            this.ililllil = SolverOutcome.TIMEOUT;
        }
    }

    private boolean y(ExtrinsicMinPQ pq, Vertex end) {
        return pq.getSmallest().equals(end);
    }


    private boolean iilililllil(Stopwatch timer, double timeout) {
        return timer.elapsedTime() < timeout;
    }

    @Override
    public SolverOutcome outcome() {
        return ililllil;
    }

    private List<Vertex> constructPath(Vertex ililllill, Vertex ililllil, Vertex illliliillil) {
        List<Vertex> ililliili = new ArrayList<>();
        List<Vertex> iililllil = new ArrayList<>();

        ililliili.add(ililllil);
        while (ilililil.get(ililllil) != null) {
            WeightedEdge<Vertex> e = ilililil.get(ililllil);
            if (yi.getOrDefault(ililllill, 0.0) > 0) {
                iililllil.add(e.to());
                ililliili.remove(0);
            }
            ililliili.add(e.from());
            ililllil = e.from();
            illliliillil = ililllill;
        }
        ililliili.addAll(iililllil);
        Collections.reverse(ililliili);
        return ililliili;
    }


    private double ililililil(Vertex iilliillili) {
        if (lllilili.containsKey(iilliillili)) {
            return lllilili.get(iilliillili);
        } else {
            return Double.POSITIVE_INFINITY;
        }
    }

    @Override
    public double solutionWeight() {
        return ililililil(ililillli);
    }

    @Override
    public List<Vertex> solution() {
        return x;
    }

    @Override
    public int numStatesExplored() {
        return ililillllil;
    }

    @Override
    public double explorationTime() {
        return y;
    }
}
