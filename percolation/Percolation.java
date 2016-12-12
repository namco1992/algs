import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by namco on 16/12/5.
 */

public class Percolation {
    private WeightedQuickUnionUF uf;
    private int[] openMap;  // openMap[i] = 1 if i is open, otherwise 0
    private final int size;  // size of the array for the n-by-n grid
    private final int nValue;  // n for n-by-n grid

    // create n-by-n grid, with all sites blocked
    public Percolation(int n) {
        nValue = n;
        size = n*n+2;  // add 2 vitural sites

        openMap = new int[size];
        openMap[0] = openMap[size - 1] = 1;

        uf = new WeightedQuickUnionUF(size);
    }
    // open site (row, col) if it is not open already
    public void open(int row, int col) {
        if (isOpen(row, col)) return;
        int index = xyTo1D(row, col);
        open(row, col);
        int[] neighbours = findNeighbours(row, col, index);
        for(int x: neighbours) {
            if (openMap[x] == 1) uf.union(index, x);
        }

    }
    // is site (row, col) open?
    public boolean isOpen(int row, int col) {
        int index = xyTo1D(row, col);
        return openMap[index] == 1;
    }
    // is site (row, col) full?
    public boolean isFull(int row, int col) {
        int index = xyTo1D(row, col);
        return uf.find(index) == 0;
    }
    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, size - 1);
    }
    public static void main(String[] args) {

    }

    // validate that p is a valid index
    private void validate(int p) {
        if (p < 1 || p >= nValue) {
            throw new IndexOutOfBoundsException("index " + p + " out of bounds");
        }
    }
    /**
     * Return the 1-dimensional union find object index.
     */
    private int xyTo1D(int row, int col) {
        validate(row);
        validate(col);
        return nValue*(row - 1) + col;
    }

    /**
     * TODO: findNeighours is not a good idea, change it to FOUR situations.
     * @param row
     * @param col
     * @param index
     * @return
     */
    private int[] findNeighbours(int row, int col, int index) {
        int[] neighbours;
        if (row == 1 && col == 1) neighbours = new int[]{index + 1, index + nValue};
        else if (row == 1 && col == nValue) neighbours = new int[]{index - 1, index + nValue};
        else if (row == 1) neighbours = new int[]{index - 1, index + 1, index + nValue};
        else if (row == nValue && col == 1) neighbours = new int[]{index + 1, index - nValue};
        else if (row == nValue && col == nValue) neighbours = new int[]{index - 1, index - nValue};
        else if (row == nValue) neighbours = new int[]{index - 1, index + 1, index - nValue};
        else neighbours = new int[]{index - 1, index + 1, index - nValue, index + nValue};
        return neighbours;
    }
}