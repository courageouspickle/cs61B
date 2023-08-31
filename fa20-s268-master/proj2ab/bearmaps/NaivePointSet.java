package bearmaps;
import java.util.List;

/**
 * @author Harrison Dill
 */
public class NaivePointSet implements PointSet {
    /**
     * list of elements in the set.
     */
    private List<Point> points;

    /**
     * Creates a new set.
     * @param points the points.
     */
    public NaivePointSet(List<Point> points) {
        this.points = points;
    }

    /**
     * Return the nearest point to the given coordinates in the set.
     * @param x coordinate
     * @param y coordinate
     * @return the nearest point
     */
    @Override
    public Point nearest(double x, double y) {
        Point thisPoint = new Point(x, y);
        Point temp = null;
        double dist = Double.POSITIVE_INFINITY;
        for (Point p : points) {
            if (p.distance(p, thisPoint) < dist) {
                temp = p;
                dist = p.distance(p, thisPoint);
            }
        }
        return temp;
    }
}
