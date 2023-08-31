import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
    public void randomizedTest() {
        RedBlackFloorSet red = new RedBlackFloorSet();
        AListFloorSet listFloor = new AListFloorSet();
        for (int i = 0; i < 1000000; i++) {
            double temp = StdRandom.uniform(-5000, 5000);
            red.add(temp);
            listFloor.add(temp);
        }
        for (int i = 0; i < 100000; i ++) {
            double temp = StdRandom.uniform(-5000, 5000);
            assertEquals(listFloor.floor(temp), red.floor(temp), 0.0001);
            assertEquals(listFloor.floor(temp), red.floor(temp), 0.0001);
        }
    }
}
