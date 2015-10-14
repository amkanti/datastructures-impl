/****************************************************************************
 *  Compilation:  javac Fast.java
 *  Execution:  java Fast filename
 *  Dependencies: StdIn.java StdOut.java java.util Point.java
 *
 *  Fast.
 *
 ****************************************************************************/
/**
 * Class Name: Fast.java
 * Description: The <tt>Fast</tt> .java program determines whether given 
 * point p participates in a set of 4 or more collinear points.
 * <p>
 * This implementation uses Arrays.sort method with and without comparator.
 * The order of growth of the running time of the program is N^2 log N in the
 * worst case and it should use space proportional to N.
 * 
 * @author Amulya Manchikanti
 * @date : 07/10/2015
 */

import java.util.Arrays;

public class Fast {

    public static void main(String[] args) {
        // TODO Auto-generated method stub

        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        StdDraw.show(0);
        StdDraw.setPenRadius(0.01); // make the points a bit larger

        String filename = args[0];
        In in = new In(filename);
        int N = in.readInt();

        Point[] points = new Point[N]; // to store initial points
        Point[] pointsBySlope = new Point[N]; // to store points after sorting
                                                // by slope
        for (int i = 0; i < N; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
            pointsBySlope[i] = points[i];
            points[i].draw();
        }

        // natually sort array
        Arrays.sort(points);

        for (int i = 0; i < N; i++) {
            // Think of p as origin
            Point p = points[i];

            // For each other point q, determine the slope it makes with p.
            // Sort the points according to the slopes they makes with p.
            Arrays.sort(pointsBySlope);
            Arrays.sort(pointsBySlope, p.SLOPE_ORDER);

            // Check if any 3 (or more) adjacent points in the sorted order
            // have equal slopes with respect to p. If so, these points,
            // together with p, are collinear.

            // There can be multiple segments for each point.

            int j = 1;
            while (j < N - 1) {
                double slope = p.slopeTo(pointsBySlope[j]);

                if (slope == p.slopeTo(pointsBySlope[j + 1])) {
                    // Found the start of a segment.
                    // Now look for the end of the segment.
                    int start = j;
                    int end = j + 1;
                    for (int k = j + 1; k < N; k++) {
                        if (slope == p.slopeTo(pointsBySlope[k])) {
                            end = k;
                        } else {
                            break;
                        }
                    }

                    // A segment must be 4 or more points.
                    // A segment must not be a subsegment.
                    if ((end - start >= 2)
                            && (p.compareTo(pointsBySlope[start]) < 0)) {
                        StdOut.print(p);
                        for (int l = start; l <= end; l++) {
                            StdOut.print(" -> " + pointsBySlope[l]);
                        }
                        StdOut.println();
                        p.drawTo(pointsBySlope[end]);
                    }

                    j = end + 1;
                } else {
                    j++;
                }
            }
        }

        StdDraw.show(0);

        // reset the pen radius
        StdDraw.setPenRadius();
    }

}
