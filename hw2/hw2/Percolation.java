package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int[][] grid;
    private int openSites;
    private int gridSize;
    private WeightedQuickUnionUF DJset;
    private int visualTopSite;
    private int visualDownSite;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("the size of a grid cannot be less or equal than 0.");
        }
        openSites = 0;
        gridSize = N;
        grid = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                grid[i][j] = 0;
            }
        }

        DJset = new WeightedQuickUnionUF(N * N + 2);
        visualTopSite = N * N;
        visualDownSite = N * N + 1;

    }

    public void open(int row, int col) {
        if (!checkSide(row, col)) {
            throw new IndexOutOfBoundsException("Site is not inside the grid!");
        }
        grid[row][col] = 1;
        openSites += 1;

        int currentSite = turn2DTo1D(row, col);

        if (!percolates() && row == 0) {
            DJset.union(currentSite, visualTopSite);
        }
        if (!percolates() && row == gridSize - 1) {
            DJset.union(currentSite, visualDownSite);
        }

        if (checkSide(row - 1, col) && isOpen(row - 1, col)) {
            DJset.union(currentSite, turn2DTo1D(row - 1, col));
        }

        if (checkSide(row + 1, col) && isOpen(row + 1, col)) {
            DJset.union(currentSite, turn2DTo1D(row + 1, col));
        }

        if (checkSide(row, col - 1) && isOpen(row, col - 1)) {
            DJset.union(currentSite, turn2DTo1D(row, col - 1));
        }

        if (checkSide(row, col + 1) && isOpen(row, col + 1)) {
            DJset.union(currentSite, turn2DTo1D(row, col + 1));
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
        return DJset.connected(visualTopSite, turn2DTo1D(row, col));
    }

    public int numberOfOpenSites() {
        System.out.println("current open site is" + openSites);
        return openSites;
    }

    public boolean percolates() {
        return DJset.connected(visualTopSite, visualDownSite);
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
}
