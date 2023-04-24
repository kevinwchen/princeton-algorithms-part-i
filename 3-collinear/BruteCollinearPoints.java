/* *****************************************************************************
 *  Name: Kevin
 *  Date: 24/04/2023
 *  Description: Examines 4 points at a time and checks if they are collinear
 *               using a brute-force method
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class BruteCollinearPoints {
    private ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();

    public BruteCollinearPoints(Point[] points) { // finds all line segments containing 4 points
        if (points == null) throw new IllegalArgumentException();

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

        ArrayList<Point> pointsGroup = new ArrayList<Point>();
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    if (points[i].slopeTo(points[j]) == points[j].slopeTo(points[k])) {
                        for (int m = k + 1; m < points.length; m++) {
                            if (points[j].slopeTo(points[k]) == points[k].slopeTo(points[m])) {

                                // All four points collinear
                                pointsGroup.add(points[i]);
                                pointsGroup.add(points[j]);
                                pointsGroup.add(points[k]);
                                pointsGroup.add(points[m]);
                                pointsGroup.sort(Comparator.naturalOrder());
                                LineSegment newSegment = new LineSegment(pointsGroup.get(0),
                                                                         pointsGroup.get(3));
                                lineSegmentList.add(newSegment);
                                pointsGroup.clear();
                            }
                        }
                    }
                }
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

        BruteCollinearPoints brute = new BruteCollinearPoints(myPoints);
        System.out.println(brute.numberOfSegments());
    }
}
