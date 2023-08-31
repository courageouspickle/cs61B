package bearmaps.proj2c;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;
    private DoubleMapPQ<Vertex> pq;
    private HashMap<Vertex, Double> distances;
    private HashMap<Vertex, WeightedEdge<Vertex>> edgeTo;
    private Stopwatch sw;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        solution = new ArrayList<>();
        pq = new DoubleMapPQ<>();
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distances = new HashMap<>();
        distances.put(start, 0.0);
        edgeTo = new HashMap<>();
        numStatesExplored = 0;
        Vertex cur;
        sw = new Stopwatch();
        
        while (pq.size() > 0 && !pq.getSmallest().equals(end) /*&& sw.elapsedTime() < timeout*/) {
            cur = pq.removeSmallest();
            numStatesExplored++;
            solverHelper(input, cur, end, timeout);
        }
        explorationTime = sw.elapsedTime();
        if (outOfTime(timeout)) {
            return;
        } else if (pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
            return;
        } else {
            outcome = SolverOutcome.SOLVED;
            constructSolution(start, end);

        }
    }

    private void solverHelper(AStarGraph<Vertex> input, Vertex cur, Vertex end, double timeout) {
        for (WeightedEdge<Vertex> edge : input.neighbors(cur)) {
            if (outOfTime(timeout)) {
                return;
            }
            double newDist = distances.get(edge.from()) + edge.weight();
            double heuristic = input.estimatedDistanceToGoal(edge.to(), end);
            if (!distances.containsKey(edge.to())) {
                pq.add(edge.to(), newDist + heuristic);
                distances.put(edge.to(), newDist);
                edgeTo.put(edge.to(), edge);
            } else {
                if (newDist < distances.get(edge.to())) {
                    distances.replace(edge.to(), newDist);
                    edgeTo.replace(edge.to(), edge);
                    pq.changePriority(edge.to(), newDist);
                }
            }
        }
    }

    private boolean outOfTime(double timeout) {
        if (sw.elapsedTime() >= timeout) {
            outcome = SolverOutcome.TIMEOUT;
            return false; //CHANGE THIS
        }
        return false;
    }

    private void constructSolution(Vertex start, Vertex end) {
        Vertex cur = end;
        solution = new ArrayList<>();
        solutionWeight = 0.0;
        while (!cur.equals(start)) {
            solution.add(0, cur);
            solutionWeight += edgeTo.get(cur).weight();
            cur = edgeTo.get(cur).from();
        }
        solution.add(0, cur);
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }

    public double solutionWeight() {
        return solutionWeight;
    }

    public int numStatesExplored() {
        return numStatesExplored;
    }

    public double explorationTime() {
        return explorationTime;
    }
}
