package hw2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] stats;

    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException("the size and experiment times should be positive numbers!");
        }   
        stats = new double[T];
        for (int i = 0; i < T; i += 1) {
            Percolation p = pf.make(N);

            while (!p.percolates()) {
                int row = StdRandom.uniform(N);
                int col = StdRandom.uniform(N);
                if (p.isOpen(row, col)) {

                }
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
        double stddevSqrt = Math.sqrt(this.stddev());
        return this.mean() - 1.96 * stddevSqrt / Math.sqrt(stats.length);
    }

    public double confidenceHigh() {
        double stddevSqrt = Math.sqrt(this.stddev());
        return this.mean() + 1.96 * stddevSqrt / Math.sqrt(stats.length);
    }

    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats ps = new PercolationStats(10, 10, pf);
        System.out.println(ps.mean());
        System.out.println(ps.stddev());
        System.out.println(ps.confidenceLow());
        System.out.println(ps.confidenceHigh());
    }
}
