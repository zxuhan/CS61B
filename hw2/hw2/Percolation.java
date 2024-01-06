package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int openSites;
    private int gridSize;
    private WeightedQuickUnionUF djSet;
    private int visualTopSite;
    private int visualDownSite;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("the size should be a positive number!");
        }
        openSites = 0;
        gridSize = N;
        grid = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grid[i][j] = 0;
            }
        }

        djSet = new WeightedQuickUnionUF(N * N + 2);

        //Set a visual top site and a visual down site
        visualTopSite = N * N;
        visualDownSite = N * N + 1;

    }

    public void open(int row, int col) {
        if (!checkSide(row, col)) {
            throw new IndexOutOfBoundsException("Site is not inside the grid!");
        }
        // Avoid opening a site again.
        if (isOpen(row, col)) {
            return;
        }

        grid[row][col] = 1;
        openSites += 1;

        int currentSite = turn2DTo1D(row, col);

        // Let current site connects each other
        if (checkSide(row - 1, col) && isOpen(row - 1, col)) {
            djSet.union(currentSite, turn2DTo1D(row - 1, col));
        }

        if (checkSide(row + 1, col) && isOpen(row + 1, col)) {
            djSet.union(currentSite, turn2DTo1D(row + 1, col));
        }

        if (checkSide(row, col - 1) && isOpen(row, col - 1)) {
            djSet.union(currentSite, turn2DTo1D(row, col - 1));
        }

        if (checkSide(row, col + 1) && isOpen(row, col + 1)) {
            djSet.union(currentSite, turn2DTo1D(row, col + 1));
        }

        // Connect the first row with the visual top site.
        if (row == 0) {
            djSet.union(currentSite, visualTopSite);
        }
        
        // Before percolates, connect the last row with the visual top site if it has connected
        if (!percolates() && row == gridSize - 1 && djSet.connected(currentSite, visualTopSite)) {
            djSet.union(currentSite, visualDownSite);
        }

    }

    public boolean isOpen(int row, int col) {
        if (!checkSide(row, col)) {
            throw new IndexOutOfBoundsException("Site is not inside the grid!");
        }
        return grid[row][col] == 1;
    }

    public boolean isFull(int row, int col) {
        if (!checkSide(row, col)) {
            throw new IndexOutOfBoundsException("Site is not inside the grid!");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        return djSet.connected(visualTopSite, turn2DTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return openSites;
    }

    public boolean percolates() {
        return djSet.connected(visualTopSite, visualDownSite);
    }

    private boolean checkSide(int row, int col) {
        if (row < 0 || row >= gridSize || col < 0 || col >= gridSize) {
            return false;
        }
        return true;
    }

    private int turn2DTo1D(int row, int col) {
        return row * gridSize + col;
    }

    public static void main(String[] args) {
        Percolation p = new Percolation(3);
        p.open(0, 0);
        p.open(1, 1);
        System.out.println(p.isOpen(0, 0));
        System.out.println(p.isFull(1, 1));
        System.out.println(p.isOpen(2, 2));
        System.out.println(p.numberOfOpenSites());
    }
}
