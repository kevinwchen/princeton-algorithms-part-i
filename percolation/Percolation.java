import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private final int[][] grid; // 2d array for referencing grid index
    private boolean[] state; // true if site is open
    private final WeightedQuickUnionUF gridUF;
    private final int size;
    private int openSites;
    private final int topVirtualSite;
    private final int botVirtualSite;

    // check if site is a within the bounds of the grid
    private void checkSite(int row, int col) {
        if (row <= 0 || row > grid.length || col <= 0 || col > grid.length)
            throw new IllegalArgumentException();
    }

    // create union for grid edge sites
    private void checkGridEdgeUnion(int row, int col) {
        if (col == 1) {
            gridUF.union(grid[row][col], grid[row][col + 1]);
        } else if (col == size) {
            gridUF.union(grid[row][col], grid[row][col - 1]);
        }
    }

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        size = n;
        gridUF = new WeightedQuickUnionUF(n * n + 2);
        grid = new int[n][n];
        state = new boolean[n * n + 2];
        openSites = 0;
        topVirtualSite = n * n;
        botVirtualSite = n * n + 1;

        int x = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = x;
                if (i == 0) {
                    gridUF.union(x, topVirtualSite);
                } else if (i == n - 1) {
                    gridUF.union(x, botVirtualSite);
                }
                x++;
            }
        }
        state[topVirtualSite] = state[botVirtualSite] = true;
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        checkSite(row, col);

        if (!state[grid[row - 1][col - 1]]) {
            state[grid[row - 1][col - 1]] = true;
            openSites++;

            if (row != size && isOpen(row + 1, col)) {
                gridUF.union(grid[row - 1][col - 1], grid[row][col - 1]);
            }
            if (row != 1 && isOpen(row - 1, col)) {
                gridUF.union(grid[row - 1][col - 1], grid[row - 2][col - 1]);
            }
            if (col != size && isOpen(row, col + 1)) {
                gridUF.union(grid[row - 1][col - 1], grid[row - 1][col]);
            }
            if (col != 1 && isOpen(row, col - 1)) {
                gridUF.union(grid[row - 1][col - 1], grid[row - 1][col - 2]);
            }

        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        checkSite(row, col);
        return state[grid[row - 1][col - 1]];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        checkSite(row, col);
        return gridUF.find(grid[row - 1][col - 1]) == gridUF.find(topVirtualSite);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return gridUF.find(botVirtualSite) == gridUF.find(topVirtualSite);
    }

    // test client (optional)
    public static void main(String[] args) {
//        Percolation myPercolation = new Percolation(5);
//        myPercolation.open(1, 1);
//        myPercolation.open(1, 4);
//        myPercolation.open(2, 1);
//        myPercolation.open(2, 3);
//        myPercolation.open(2, 4);
//        myPercolation.open(3, 1);
//        myPercolation.open(3, 3);
//        myPercolation.open(3, 5);
//        myPercolation.open(4, 3);
//        myPercolation.open(4, 4);
//        myPercolation.open(5, 1);
//        myPercolation.open(5, 4);
//        System.out.println(myPercolation.numberOfOpenSites());
//        System.out.println(myPercolation.isFull(2, 1));
//        System.out.println(myPercolation.isFull(3, 1));
//        System.out.println(myPercolation.isFull(3, 5));
//        System.out.println(myPercolation.percolates());
    }
}
