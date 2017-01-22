import java.util.Arrays;
import edu.princeton.cs.algs4.*;

/**
 * Created by namco on 21/01/17.
 */
public class BruteCollinearPoints {
    private Point[] collinearPoints;
    private int len;
    private LineSegment[] lineSegments = new LineSegment[1];

    public BruteCollinearPoints(Point[] points) {
        if(points == null) throw new NullPointerException();
        len = points.length;
        Arrays.sort(points);
        for (int i = 0; i < len; i++) {
            if(points[i] == null) throw new NullPointerException();
            if(i > 0 && points[i] == points[i-1]) throw new IllegalArgumentException();
        }
        for (int i = 0; i < len; i++) {
            for (int j = i+1; j < len; j++) {
                for (int k = j+1; k < len; k++) {
                    for (int l = k+1; l < len; l++) {
                        double slope12 = points[i].slopeTo(points[j]);
                        double slope13 = points[i].slopeTo(points[k]);
                        double slope14 = points[i].slopeTo(points[l]);

                        if (slope12 == slope13 && slope13 == slope14) {
                            this.collinearPoints = new Point[len];
                            System.arraycopy(points, 0, this.collinearPoints, 0, len);
                            this.lineSegments[0] = new LineSegment(points[0], points[len-1]);
                        }
                    }
                }
            }
        }
    }   // finds all line segments containing 4 points
    public int numberOfSegments() {
        return this.lineSegments.length;
    }       // the number of line segments
    public LineSegment[] segments() {
        return this.lineSegments;
    }               // the line segments

    public static void main(String[] args) {
        Point p = new Point(10, 57);
        Point q = new Point(415, 270);
        Point r = new Point(213,239);
        System.out.println(p.slopeTo(q));
        System.out.println(p.slopeTo(r));

        // read the n points from a file
//        In in = new In(args[0]);
//        In in = new In("input8.txt");
//
//        int n = in.readInt();
//        Point[] points = new Point[n];
//        for (int i = 0; i < n; i++) {
//            int x = in.readInt();
//            int y = in.readInt();
//            points[i] = new Point(x, y);
//        }
//
//        // draw the points
//        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (Point p : points) {
//            p.draw();
//        }
//        StdDraw.show();
//
//        // print and draw the line segments
//        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
//        for (LineSegment segment : collinear.segments()) {
//            StdOut.println(segment);
//            segment.draw();
//        }
//        StdDraw.show();
    }

}
