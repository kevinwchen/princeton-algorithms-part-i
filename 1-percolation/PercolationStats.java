import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private final double size;
    private final int numTrials;
    private double[] thresholds;
    private double CONFIDENCE_95 = 1.96;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) throw new IllegalArgumentException();
        size = n;
        numTrials = trials;
        thresholds = new double[trials];

        for (int i = 0; i < trials; i++) {
            Percolation myPercolation = new Percolation(n);
            while (!myPercolation.percolates()) {
                int row = StdRandom.uniformInt(1, n + 1);
                int col = StdRandom.uniformInt(1, n + 1);
                if (!myPercolation.isOpen(row, col)) myPercolation.open(row, col);
            }
            thresholds[i] = myPercolation.numberOfOpenSites() / (size * size);
        }
        StdRandom.uniformInt(n);
    }


    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(thresholds);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(thresholds);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - CONFIDENCE_95 * this.stddev() / Math.sqrt(numTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + CONFIDENCE_95 * this.stddev() / Math.sqrt(numTrials);
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats stats = new PercolationStats(n, trials);
        StdOut.println("mean                     = " + stats.mean());
        StdOut.println("stddev                   = " + stats.stddev());
        StdOut.println("95%% confidence interval = [" + stats.confidenceLo() + ", " + stats.confidenceHi() + "]");
    }

}
