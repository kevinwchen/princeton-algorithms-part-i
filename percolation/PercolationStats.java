import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
    private final int size;
    private double[] percolationThresholds;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        size = n;
        for (int i = 0; i < n; i++) {
            Percolation myPercolation = new Percolation(n);
            StdRandom.uniformInt(n);
        }
    }

    // sample mean of percolation threshold
    public double mean() {

    }

    // sample standard deviation of percolation threshold
    public double stddev() {

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {

    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {

    }

    // test client (see below)
    public static void main(String[] args) {
        int n = StdIn.readInt();
        int trials = StdIn.readInt();
        PercolationStats percolationStats = new PercolationStats(n, trials);
        StdOut.println("mean                     = " + percolationStats.mean());
        StdOut.println("stddev                   = " + percolationStats.stddev());
        StdOut.println("95%% confidence interval = [" + percolationStats.confidenceLo() + ", " + percolationStats.confidenceHi() + "]");
    }

}
