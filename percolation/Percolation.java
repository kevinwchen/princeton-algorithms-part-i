public class Percolation {
    private int[][] grid; // 2d array representation grid numbers
    private int[] gridId;
    private boolean[] state; // true if site is open

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {

        grid = new int[n][n];
        gridId = new int[n * n];
        state = new boolean[n * n];

        int x = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = gridId[x] = x;
                state[x] = false;
                x++;
            }
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        state[grid[row][col]] = true;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        return state[grid[row][col]];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        return true;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int count = 0;
        for (boolean b : state) {
            if (b) count++;
        }
        return count;
    }

    // does the system percolate?
    public boolean percolates() {
        return true;
    }

    // test client (optional)
    public static void main(String[] args) {
        new Percolation(5);
    }
}
