package hw4.puzzle;

import java.util.ArrayList;

public class Board implements WorldState {
    private int[][] tiles;
    private int size;
    private int rowOfBlank;
    private int colOfBlank;
    private int[][] goalTiles;


    /*  Constructs a board from an N-by-N array of tiles where
     *  tiles[i][j] = tile at row i, column j.
     */
    public Board(int[][] tiles) {
        size = tiles.length;
        this.tiles = new int[size][size];
        goalTiles = new int[size][size];
        
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] == 0) {
                    rowOfBlank = i;
                    colOfBlank = j;
                }
                this.tiles[i][j] = tiles[i][j];
                goalTiles[i][j] = i * size + j + 1;
            }
        }
        goalTiles[size - 1][size - 1] = 0;
    }

    /*  Returns value of tile at row i, column j (or 0 if blank). */
    public int tileAt(int i, int j) {
        if (i < 0 || i >= size || j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("Index out of bounds!");
        }
        return tiles[i][j];
    }
    
    public int size() {
        return size;
    }

    @Override
    public Iterable<WorldState> neighbors() {
        ArrayList<WorldState> neighborsList = new ArrayList<>();

        if (isValidIndex(rowOfBlank - 1, colOfBlank)) {
            int[][] newTiles = copyAndSwap(rowOfBlank, colOfBlank, rowOfBlank - 1, colOfBlank);
            neighborsList.add(new Board(newTiles));
        }

        if (isValidIndex(rowOfBlank + 1, colOfBlank)) {
            int[][] newTiles = copyAndSwap(rowOfBlank, colOfBlank, rowOfBlank + 1, colOfBlank);
            neighborsList.add(new Board(newTiles));
        }

        if (isValidIndex(rowOfBlank, colOfBlank - 1)) {
            int[][] newTiles = copyAndSwap(rowOfBlank, colOfBlank, rowOfBlank, colOfBlank - 1);
            neighborsList.add(new Board(newTiles));
        }

        if (isValidIndex(rowOfBlank, colOfBlank + 1)) {
            int[][] newTiles = copyAndSwap(rowOfBlank, colOfBlank, rowOfBlank, colOfBlank + 1);
            neighborsList.add(new Board(newTiles));
        }

        return neighborsList;
    }
    
    private boolean isValidIndex(int i, int j) {
        if (i < 0 || j < 0 || i >= size || j >= size) {
            return false;
        }
        return true;
    }

    private int[][] copyAndSwap(int row1, int col1, int row2, int col2) {
        int[][] newTiles = new int[size][size];
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                newTiles[i][j] = tiles[i][j];
            }
        }

        int temp = newTiles[row1][col1];
        newTiles[row1][col1] = newTiles[row2][col2];
        newTiles[row2][col2] = temp;

        return newTiles;
    }

    /*  Hamming estimate described below. */
    public int hamming() {
        int count = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] != 0 && tiles[i][j] != goalTiles[i][j]) {
                    count += 1;
                }
            }
        }
        return count;
    }
    
    /*  Manhattan estimate described below. */
    public int manhattan() {
        int count = 0;
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tiles[i][j] != 0 && tiles[i][j] != goalTiles[i][j]) {
                    int value = tiles[i][j];
                    count += Math.abs(getGoalRowIndex(value) - i);
                    count += Math.abs(getGoalColIndex(value) - j);
                }
            }
        }
        return count;
    }

    private int getGoalRowIndex(int value) {
        return (value - 1) / size;
    }
    
    private int getGoalColIndex(int value) {
        return (value - 1) % size;
    }
    
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    
    public boolean equals(Object y) {
        if (this == y) {
            return true;
        }
        if (y == null || y.getClass() != this.getClass()) {
            return false;
        }

        Board b = (Board) y;
        if (b.size() != this.size()) {
            return false;
        }

        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                if (tileAt(i, j) != b.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i,j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

}
