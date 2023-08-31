package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    int width;
    int height;
    TETile[][] world;
    TERenderer ter;

    public HexWorld(int width, int height) {
        // initialize the tile rendering engine with a window of size WIDTH x HEIGHT
        ter = new TERenderer();
        this.width = width;
        this.height = height;
        ter.initialize(width, height);

        // initialize tiles
        world = new TETile[width][height];
        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
    }
    public void addHexagon(int size, int startRow, int startCol, TETile type) {
        for (int i = 0; i < size; i++) {
            drawLine(size + 2 * i, startRow + i, startCol - i, type);
        }
        //drawLine(size * 3 - 2, startRow + size - 1, startCol - size + 1, type);
        //drawLine(size * 3 - 2, startRow + size, startCol - size + 1, type);
        for (int i = 0; i < size; i++) {
            drawLine(size * 3 - 2 - 2 * i, startRow + size + i, startCol - size + 1 + i, type);
        }
    }
    private void drawLine(int length, int row, int col, TETile type) {
        for (int i = 0; i < length; i++) {
            world[row][col + i] = type;
        }
    }
}
