package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Make a square grid and percolate it.
 * @author Harrison Dill
 */
public class Percolation {
    /**
     * length of one side of the square.
     */
    private int size;
    /**
     * Number of open tiles.
     */
    private int numOpen;
    /**
     * The union data type.
     * Note, the last two nodes represent the top and bottom.
     */
    private WeightedQuickUnionUF tiles;
    /**
     * I hate javadoxs.
     */
    private WeightedQuickUnionUF forPerc;

    /**
     * Tells which tiles have been opened.
     */
    private boolean[] openTiles;

    /**
     * construct a new percolation object.
     * @param N size of the object
     */
    public Percolation(int N) {
        this.size = N;
        tiles = new WeightedQuickUnionUF(N * N + 2);
        forPerc = new WeightedQuickUnionUF(N * N + 2);
        openTiles = new boolean[N * N];
        numOpen = 0;
    }

    /**
     * Return the 1D index corresponding to the given 2d coordinates.
     * @param row given y coord
     * @param col given x coord
     * @return the 1D coord
     */
    private int xyto1D(int row, int col) {
        if (row >= size || col >= size) {
            throw new IndexOutOfBoundsException("Tried to xyto1D");
        }
        return col + row * size;
    }

    /**
     * Open the tile at the given 2D coords.
     * @param row y coord
     * @param col x coord
     */
    public void open(int row, int col) {
        int index = xyto1D(row, col);
        if (!openTiles[xyto1D(row, col)]) {
            numOpen++;
        }
        openTiles[index] = true;
        if (row > 0 && openTiles[xyto1D(row - 1, col)]) {
            tiles.union(xyto1D(row - 1, col), index);
            forPerc.union(xyto1D(row - 1, col), index);
        } else if (row == 0) {
            tiles.union(index, size * size);
            forPerc.union(index, size * size);
        }
        if (row < size - 1 && openTiles[xyto1D(row + 1, col)]) {
            tiles.union(xyto1D(row + 1, col), xyto1D(row, col));
            forPerc.union(xyto1D(row + 1, col), xyto1D(row, col));
        } else if (row == size - 1) {
            forPerc.union(index, size * size + 1);
        }
        if (col > 0 && openTiles[xyto1D(row, col - 1)]) {
            tiles.union(xyto1D(row, col - 1), xyto1D(row, col));
            forPerc.union(xyto1D(row, col - 1), xyto1D(row, col));
        }
        if (col < size - 1 && openTiles[xyto1D(row, col + 1)]) {
            tiles.union(xyto1D(row, col + 1), xyto1D(row, col));
            forPerc.union(xyto1D(row, col + 1), xyto1D(row, col));
        }
    }

    /**
     * Return whether the tile is open.
     * @param row row of the tile
     * @param col column of the tile
     * @return whether its open
     */
    public boolean isOpen(int row, int col) {
        return openTiles[xyto1D(row, col)];
    }

    /**
     * Return whether the tile is full.
     * @param row of the tile
     * @param col of the tile
     * @return whether its full
     */
    public boolean isFull(int row, int col) {
        return tiles.connected(xyto1D(row, col), size * size);
    }

    /**
     * Return the number of open sites.
     * @return num of open sites
     */
    public int numberOfOpenSites() {
        return numOpen;
    }

    /**
     * Return whether the system percolates.
     * @return whether it percolates
     */
    public boolean percolates() {
        return forPerc.connected(size * size + 1, size * size);

    }

    /**
     * stupid method for AG.
     * @param args blah
     */
    public static void main(String[] args) {

    }
}
