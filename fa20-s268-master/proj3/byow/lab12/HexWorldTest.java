package byow.lab12;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

public class HexWorldTest {
    public static void main(String[] args) {
        HexWorld world = new HexWorld(60, 30);

        TERenderer ter = new TERenderer();
        ter.initialize(60, 40);
        world.addHexagon(4, 5, 10, Tileset.AVATAR);
        ter.renderFrame(world.world);
    }
}
