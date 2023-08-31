package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;

/**
 * Percolation stats.
 * @author Harrison Dill
 */
public class PercolationStats {
    /**
     * Array with the number of open tiles it took to percolate.
     */
    private double[] stats;
    /**
     * The number of samples.
     */
    private int numSamples;

    /**
     * Construct a percolationStats.
     * @param N size the arrays
     * @param T num of arrays
     * @param pf useless
     */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("nope");
        }
        Percolation p;
        stats = new double[T];
        this.numSamples = T;
        while (T > 0) {
            p = pf.make(N);
            while (!p.percolates()) {
                p.open(StdRandom.uniform(N), StdRandom.uniform(N));
            }
            stats[T - 1] = (double) p.numberOfOpenSites() /  (N * N * 1.0);
            T--;
        }
    }

    /**
     * Return the mean of stats.
     * @return the mean
     */
    public double mean() {
        return StdStats.mean(stats);
    }

    /**
     * return the std dev of stats.
     * @return the std dev
     */
    public double stddev() {
        return StdStats.stddev(stats);
    }

    /**
     * this is a silly variable that the style checker made me make.
     */
    private final double why = 1.96;
    /**
     * Low bound.
     * @return the low
     */
    public double confidenceLow() {
        return mean() - why * stddev() / Math.pow(numSamples, .5);
    }

    /**
     * high bound.
     * @return high
     */
    public double confidenceHigh() {
        return mean() + why * stddev() / Math.pow(numSamples, .5);
    }
}
