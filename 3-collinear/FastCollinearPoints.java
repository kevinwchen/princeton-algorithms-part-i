/* *****************************************************************************
 *  Name: Kevin
 *  Date: 24/03/2023
 *  Description: Examines 4 points at a time and checks if they are collinear
 *               using a faster method (than brute-force)
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {
    private ArrayList<LineSegment> lineSegmentList = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
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


    }

    public int numberOfSegments() { // the number of line segments
        return 0;
    }

    public LineSegment[] segments() { // the line segments
        return null;
    }

    public static void main(String[] args) {
    }
}
