/* *****************************************************************************
 *  Name: Kevin
 *  Date: 25/03/2023
 *  Description: Examines 4 or more points at a time and checks if they are
 *               collinear using a faster method (than brute-force)
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
                    if (j == points.length - 1 && currentSegments >= 3) {
                        pointsGroup.add(slopePoints[j]);
                        addLineSegment(pointsGroup, points, i);
                    }
                }
                else {
                    if (currentSegments >= 3) addLineSegment(pointsGroup, points, i);
                    currentSegments = 1;
                    pointsGroup.clear();
                }
                pointsGroup.add(slopePoints[j]);
                prevSlope = currentSlope;
                j++;
            }
        }

    }

    private void addLineSegment(ArrayList<Point> pointsGroup, Point[] points, int i) {
        // create line segment if at least three subsegments
        pointsGroup.add(points[i]); // add comparison point
        pointsGroup.sort(Comparator.naturalOrder()); // sort

        // check if point is minimal point to avoid duplicates
        if (points[i].compareTo(pointsGroup.get(0)) == 0) {
            LineSegment lineSegment = new LineSegment(pointsGroup.get(0),
                                                      pointsGroup.get(
                                                              pointsGroup.size()
                                                                      - 1));
            lineSegmentList.add(lineSegment);
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
        Point point9 = new Point(1, 3);
        Point point0 = new Point(1, 4);
        Point[] myPoints = new Point[10];
        myPoints[0] = point1;
        myPoints[1] = point2;
        myPoints[2] = point3;
        myPoints[3] = point4;
        myPoints[4] = point5;
        myPoints[5] = point6;
        myPoints[6] = point7;
        myPoints[7] = point8;
        myPoints[8] = point9;
        myPoints[9] = point0;

        FastCollinearPoints fast = new FastCollinearPoints(myPoints);
        System.out.println(fast.numberOfSegments());
        for (int i = 0; i < fast.numberOfSegments(); i++) {
            System.out.println(fast.segments()[i]);
        }
    }
}
