package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] stats;
    private double sqrtT;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("the size and times should be positive numbers!");
        }   
        stats = new double[T];
        sqrtT = Math.sqrt(T);
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);

            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                p.open(row, col);
            }
            
            double openSites = p.numberOfOpenSites();
            double totalSites = N * N;
            stats[i] = openSites / totalSites;
        }
    }

    public double mean() {
        return StdStats.mean(stats);
    }
    
    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLow() {
        double sqrtStddev = Math.sqrt(this.stddev());
        return this.mean() - 1.96 * sqrtStddev / sqrtT;
    }

    public double confidenceHigh() {
        double sqrtStddev = Math.sqrt(this.stddev());
        return this.mean() + 1.96 * sqrtStddev / sqrtT;
    }

}
