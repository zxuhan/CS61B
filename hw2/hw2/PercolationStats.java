package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] stats;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("the size of a grid or experiment times should be positive numbers!");
        }
        stats = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);

            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }

            stats[i] = p.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }
    
    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLow() {
        double stddevSqrt = Math.sqrt(this.stddev());
        return this.mean() - 1.96 * stddevSqrt / Math.sqrt(stats.length);
    }

    public double confidenceHigh() {
        double stddevSqrt = Math.sqrt(this.stddev());
        return this.mean() + 1.96 * stddevSqrt / Math.sqrt(stats.length);
    }

}
