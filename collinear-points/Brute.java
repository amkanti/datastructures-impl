/****************************************************************************
 *  Compilation:  javac Brute.java
 *  Execution:  java Brute filename
 *  Dependencies: StdIn.java StdOut.java java.util Point.java
 *
 *  Brute.
 *
 ****************************************************************************/
/**
 * Class Name: Brute.java
 * Description: The <tt>Brute</tt> .java examines 4 points at a time and checks
 * whether they all lie on the same line segment, printing out any such line 
 * segments to standard output and drawing them using standard drawing.
 * To check whether the 4 points p, q, r, and s are collinear, check 
 * whether the slopes between p and q, between p and r, and between
 * p and s are all equal.
 * <p>
 * This implementation uses Arrays.sort method with and without comparator.
 * The order of growth of the running time of the program is N^4 in the
 * worst case and it should use space proportional to N.
 * 
 * @author Amulya Manchikanti
 * @date : 07/10/2015
 */

import java.util.Arrays;

public class Brute {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        double slope1, slope2, slope3;
        // rescale coordinates and turn on animation mode
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01); // make the points a bit larger

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            points[i].draw();
        }

        for (int i = 0; i < N; i++)
            for (int j = i + 1; j < N; j++)
                for (int k = j + 1; k < N; k++)
                    for (int l = k + 1; l < N; l++) {
                        slope1 = points[i].slopeTo(points[j]);
                        slope2 = points[i].slopeTo(points[k]);
                        slope3 = points[i].slopeTo(points[l]);
                        if ((slope1 == slope2) && (slope1 == slope3)) {
                            draw(points, i, j, k, l);
                        } else
                            continue;
                    }

        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }

    /**
     * draws the line between the first and last point in the collinear set and
     * prints them in order
     * 
     * @param points
     *            the initial array
     * @param i
     *            indicates one of the points in collinear set
     * @param j
     *            indicates one of the points in collinear set
     * @param k
     *            indicates one of the points in collinear set
     * @param l
     *            indicates one of the points in collinear set
     */
    private static void draw(Point[] points, int i, int j, int k, int l) {
        Point[] psubarr = new Point[4];
        psubarr[0] = points[i];
        psubarr[1] = points[j];
        psubarr[2] = points[k];
        psubarr[3] = points[l];

        // to draw line segment b/w 1st and last point
        Arrays.sort(psubarr);
        psubarr[0].drawTo(psubarr[3]);

        // display the points
        for (int h = 0; h < 4; h++) {
            if (h == 3)
                System.out.println(psubarr[h].toString());
            else
                System.out.print(psubarr[h].toString() + " -> ");
        }

    }
}
