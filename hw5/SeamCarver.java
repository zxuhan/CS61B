import java.awt.Color;
import edu.princeton.cs.algs4.Picture;

public class SeamCarver {
    private int width;
    private int height;
    private Color[][] colorTable;
    private double[][] energyTable;
    private Picture currentPicture;

    public SeamCarver(Picture picture) {
        currentPicture = picture;
        width = picture.width();
        height = picture.height();
        colorTable = new Color[height][width];
        energyTable = new double[height][width];

        for (int row = 0; row < height; row += 1) {
            for (int col = 0; col < width; col += 1) {
                colorTable[row][col] = picture.get(col, row);
            }
        }
        
        for (int row = 0; row < height; row += 1) {
            for (int col = 0; col < width; col += 1) {
                energyTable[row][col] = calEnergyByCol(row, col) + calEnergyByRow(row, col);
            }
        }
    }
    
    private double calEnergyByCol(int row, int col) {
        int posLeft = col - 1;
        int posRight = col + 1;
        if (col == 0) {
            posLeft = width - 1;
        }
        if (col == width - 1) {
            posRight = 0;
        }
        int rX = colorTable[row][posRight].getRed() - colorTable[row][posLeft].getRed();
        int gX = colorTable[row][posRight].getGreen() - colorTable[row][posLeft].getGreen();
        int bX = colorTable[row][posRight].getBlue() - colorTable[row][posLeft].getBlue();
        
        double deltaX = rX * rX + gX * gX + bX * bX;
        return deltaX;
    }
    
    private double calEnergyByRow(int row, int col) {
        int posUp = row - 1;
        int posDown = row + 1;
        if (row == 0) {
            posUp = height - 1;
        }
        if (row == height - 1) {
            posDown = 0;
        }
        int rY = colorTable[posUp][col].getRed() - colorTable[posDown][col].getRed();
        int gY = colorTable[posUp][col].getGreen() - colorTable[posDown][col].getGreen();
        int bY = colorTable[posUp][col].getBlue() - colorTable[posDown][col].getBlue();

        double deltaY = rY * rY + gY * gY + bY * bY;
        return deltaY;
    }

    // current picture
    public Picture picture() {
        return new Picture(currentPicture);
    }

    // width of current picture
    public int width() {
        return width;
    }

    // height of current picture
    public int height() {
        return height;
    }

    // energy of pixel at column x and row y
    public double energy(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IndexOutOfBoundsException("Invalid index!");
        }
        return energyTable[y][x];
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        int[] horizontalSeam = new int[width];
        if (height == 1) {
            return horizontalSeam;
        }

        double[][] oldEnergyTable = energyTable;
        double[][] newEnergyTable = rotateMatrixBy90(energyTable);
        energyTable = newEnergyTable;
        
        horizontalSeam = findVerticalSeam();
        energyTable = oldEnergyTable;

        // reverse the array to get the correct sequence
        for (int i = 0, j = width - 1; i <= j; i += 1, j -= 1) {
            int temp = horizontalSeam[i];
            horizontalSeam[i] = horizontalSeam[j];
            horizontalSeam[j] = temp;
        }

        return horizontalSeam;
    }

    // the new top left is the original top right
    private double[][] rotateMatrixBy90(double[][] matrix) {
        int rows = matrix[0].length;
        int cols = matrix.length;
        double[][] newMatrix = new double[rows][cols];
        
        for (int i = 0; i < rows; i += 1) {
            for (int j = 0; j < cols; j += 1) {
                newMatrix[i][j] = matrix[j][rows - 1 - i];
            }
        }

        return newMatrix;
    }
    
    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        int rows = energyTable.length;
        int cols = energyTable[0].length;

        int[] verticalSeam = new int[rows];

        if (cols == 1) {
            return verticalSeam;
        }

        // record the index from the upper row
        int[][] pathTo = new int[rows][cols];
        // calculate the min energy for each position
        double[][] minEnergy = new double[rows][cols]; 
        
        // initialise the first row
        for (int j = 0; j < cols; j += 1) {
            minEnergy[0][j] = energyTable[0][j];
            pathTo[0][j] = j;
        }

        for (int i = 1; i < rows; i += 1) {
            for (int j = 0; j < cols; j += 1) {
                int comp = 0;

                if (j == 0) {
                    double num1 = minEnergy[i - 1][j];
                    double num2 = minEnergy[i - 1][j + 1];
                    comp = compareTwoLeftSide(num1, num2);
                } else if (j == cols - 1) {
                    double num1 = minEnergy[i - 1][j - 1];
                    double num2 = minEnergy[i - 1][j];
                    comp = compareTwoRightSide(num1, num2);
                } else {
                    double num1 = minEnergy[i - 1][j - 1];
                    double num2 = minEnergy[i - 1][j];
                    double num3 = minEnergy[i - 1][j + 1];
                    comp = compareThree(num1, num2, num3);
                }

                minEnergy[i][j] = minEnergy[i - 1][j + comp] + energyTable[i][j];
                pathTo[i][j] = j + comp;
            }
        }
        
        int indexOfMin = 0;
        for (int j = 0; j < cols; j += 1) {
            if (minEnergy[rows - 1][j] < minEnergy[rows - 1][indexOfMin]) {
                indexOfMin = j;
            }
        }

        for (int i = rows - 1; i >= 0; i -= 1) {
            verticalSeam[i] = indexOfMin;
            indexOfMin = pathTo[i][indexOfMin];
        }
        
        return verticalSeam;
    }

    // 0 for upper middle one, 1 for upper right one, -1 for upper left one
    private int compareTwoLeftSide(double num1, double num2) {
        if (num1 < num2) {
            return 0;
        }
        return 1;
    }

    private int compareTwoRightSide(double num1, double num2) {
        if (num1 < num2) {
            return -1;
        }
        return 0;
    }

    private int compareThree(double num1, double num2, double num3) {
        if (num1 <= num2 && num1 <= num3) {
            return -1;
        }
        if (num2 <= num1 && num2 <= num3) {
            return 0;
        }
        return 1;
    }
    
    // remove horizontal seam from picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("input array can't be null!");
        }
        if (seam.length != width) {
            throw new IllegalArgumentException("Wrong array length!");
        }
        for (int i = 0; i < seam.length - 1; i += 1) {
            int diff = seam[i + 1] - seam[i];
            if (diff > 1 || diff < -1) {
                throw new IllegalArgumentException("Array is not a valid seam!");
            }
        }

        SeamRemover.removeHorizontalSeam(currentPicture, seam);
    }
    
    // remove vertical seam from picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null) {
            throw new IllegalArgumentException("input array can't be null!");
        }
        if (seam.length != height) {
            throw new IllegalArgumentException("Wrong array length!");
        }
        for (int i = 0; i < seam.length - 1; i += 1) {
            int diff = seam[i + 1] - seam[i];
            if (diff > 1 || diff < -1) {
                throw new IllegalArgumentException("Array is not a valid seam!");
            }
        }

        SeamRemover.removeVerticalSeam(currentPicture, seam);
    }
}
