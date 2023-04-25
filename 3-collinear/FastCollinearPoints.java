/* *****************************************************************************
 *  Name: Kevin
 *  Date: 24/03/2023
 *  Description: Examines 4 points at a time and checks if they are collinear
 *               using a faster method (than brute-force)
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        if (points == null) throw new IllegalArgumentException();

        // Check if any entries are null
        for (int i = 0; i < points.length; i++) {
            if (points[i] == null) throw new IllegalArgumentException();
        }

        // Clone array of points, sort, and check for duplicates
        Point[] checkDuplicates = points.clone();
        Arrays.sort(checkDuplicates);
        for (int i = 0; i < checkDuplicates.length - 1; i++) {
            if (checkDuplicates[i].compareTo(checkDuplicates[i + 1]) == 0)
                throw new IllegalArgumentException();
        }

        for (int i = 0; i < points.length; i++) {
            Point[] slopePoints = points.clone(); // clone points array
            Arrays.sort(slopePoints); // sort by natural order of points
            Arrays.sort(slopePoints, points[i].slopeOrder()); // sort by slope to points[i]
            ArrayList<Point> pointsGroup = new ArrayList<Point>();
            double currentSlope;
            double prevSlope = Double.NaN;
            int currentSegments = 1;
            int j = 1; // start at 1 because 0th element is always itself
            while (j < points.length) {
                currentSlope = points[i].slopeTo(slopePoints[j]);
                if (currentSlope == prevSlope) {
                    currentSegments++;
                }
                else {
                    if (currentSegments >= 3) {
                        pointsGroup.add(points[i]);
                        pointsGroup.sort(Comparator.naturalOrder());
                        LineSegment lineSegment = new LineSegment(pointsGroup.get(0),
                                                                  pointsGroup.get(
                                                                          pointsGroup.size() - 1));
                        System.out.println(lineSegment.toString());
                        lineSegmentList.add(lineSegment);
                    }
                    currentSegments = 1;
                    pointsGroup.clear();
                }
                pointsGroup.add(slopePoints[j]);

                for (int x = 0; x < pointsGroup.size(); x++) {
                    System.out.print(pointsGroup.get(x));
                }
                System.out.println();
                prevSlope = currentSlope;
                j++;
            }
        }

    }

    public int numberOfSegments() { // the number of line segments
        return lineSegmentList.size();
    }

    public LineSegment[] segments() { // the line segments
        LineSegment[] lineSegments = new LineSegment[lineSegmentList.size()];
        for (int i = 0; i < lineSegmentList.size(); i++) {
            lineSegments[i] = lineSegmentList.get(i);
        }
        return lineSegments;
    }

    public static void main(String[] args) {
        Point point1 = new Point(1, 1);
        Point point2 = new Point(4, 4);
        Point point3 = new Point(2, 2);
        Point point4 = new Point(3, 3);
        Point point5 = new Point(4, 8);
        Point point6 = new Point(2, 4);
        Point point7 = new Point(3, 6);
        Point point8 = new Point(1, 2);
        Point[] myPoints = new Point[8];
        myPoints[0] = point1;
        myPoints[1] = point2;
        myPoints[2] = point3;
        myPoints[3] = point4;
        myPoints[4] = point5;
        myPoints[5] = point6;
        myPoints[6] = point7;
        myPoints[7] = point8;

        FastCollinearPoints fast = new FastCollinearPoints(myPoints);
        System.out.println(fast.numberOfSegments());
        for (int i = 0; i < fast.numberOfSegments(); i++) {
            System.out.println(fast.segments()[i]);
        }
    }
}
