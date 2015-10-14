/****************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:  java Percolation N
 *  Dependencies: StdIn.java StdOut.java java.util
 *
 *  Point.
 *
 ****************************************************************************/
/**
 * Class Name: Point.java
 * Description: The <tt>Point</tt> .java creates an immutable point that
 * represents points in a plane. It supports draw(), drawTo(), toString()
 * compareTo() and slopeTo() operations. It also compares the point by their 
 * slopes with the invoking point using a comparator.
 * <p>
 * 
 * @author Amulya Manchikanti
 * @date : 07/10/2015
 */

import java.util.Comparator;

public class Point implements Comparable<Point> {
    public final Comparator<Point> SLOPE_ORDER = new SlopeOrder(); // compare
                                                                    // points by
                                                                    // slope to
                                                                    // this
                                                                    // point

    private final class SlopeOrder implements Comparator<Point> {

        @Override
        public int compare(Point o1, Point o2) {

            if (o1.equals(null) && o2.equals(null))
                throw new NullPointerException("null argument found");

            double a = slopeTo(o1);
            double b = slopeTo(o2);
            if (a < b) {
                return -1;
            } else if (a == b) {
                return 0;
            } else
                return 1;
        }

    }

    private final int x; // x coordinate
    private final int y; // y coordinate

    /**
     * constructor used to construct the point
     * 
     * @param x
     *            indicates x coordinate
     * @param y
     *            indicates y coordinate
     */
    public Point(int x, int y) // construct the point (x, y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * draw the constructed point
     */
    public void draw() // draw this point
    {
        StdDraw.point(x, y);
    }

    /**
     * line segment is drawn from the invoking point to the argument point
     * 
     * @param that
     *            indicates the point to which segment is drawn
     */
    public void drawTo(Point that) // draw the line segment from this point to
                                    // that point
    {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * string representation of the point
     */
    public String toString() // string representation
    {
        return "(" + x + ", " + y + ")";
    }

    /**
     * compares the invoking point to the argument point to determine the
     * smaller one
     */
    public int compareTo(Point that) // is this point lexicographically smaller
                                        // than that point?
    {
        if (that.equals(null))
            throw new NullPointerException("null argument found");
        if (this.y < that.y)
            return -1;
        else if ((this.y == that.y) && (this.x < that.x))
            return -1;
        else if (this.y == that.y && this.x == that.x)
            return 0;
        else
            return 1;
    }

    /**
     * computes the slope between the invoking point and the argument point
     * 
     * @param that
     *            indicates the other end point
     * @return the slope
     */
    public double slopeTo(Point that) // the slope between this point and that
                                        // point
    {
        if (that.equals(null))
            throw new NullPointerException("null argument found");

        if (this.y == that.y && this.x == that.x)
            return -1.0 / 0.0; // returns negative infinity
        if (that.x - this.x == 0)
            return 1.0 / 0.0; // returns positive infinity
        if (that.y - this.y == 0)
            return 0 / 1.0; // returns positive zero
        return (that.y - this.y) / (double) (that.x - this.x);
    }

}
