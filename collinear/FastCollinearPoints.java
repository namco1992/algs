import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by namco on 22/01/17.
 */

public class FastCollinearPoints {
    private static final int minCollinearCount = 4;
    private int len;
    private int lineSegmentsNumber;
    private ArrayList<LineSegment> lineSegments;

    public FastCollinearPoints(Point[] points) {
        if(points == null) throw new NullPointerException();
        len = points.length;
        lineSegments = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            if(points[i] == null) throw new NullPointerException();
            if(i > 0 && points[i] == points[i-1]) throw new IllegalArgumentException();
        }
        Arrays.sort(points, 1, len-1);
        for (int i = 0; i < len - 1; i++) {
            double initSlope = points[i].slopeTo(points[i+1]);
            int continuousCount = 1;
            for (int j = i + 2; j < len; j++) {
                double slope = points[i].slopeTo(points[j]);
                if(initSlope == slope) {
                    continuousCount++;
                } else {
                    if(continuousCount >= minCollinearCount) this.lineSegments.add(new LineSegment(points[i], points[j]));
                    continuousCount = 1;
                    initSlope = slope;
                }


            }
        }

    }    // finds all line segments containing 4 or more points
    public int numberOfSegments() {
        return lineSegments.size();
    }        // the number of line segments

    public LineSegment[] segments() {
        return (LineSegment[]) this.lineSegments.toArray();
    }               // the line segments

    public static void main(String[] args) {

        // read the n points from a file
//        In in = new In(args[0]);
        In in = new In("input8.txt");
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}
