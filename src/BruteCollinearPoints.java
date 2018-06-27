import java.util.ArrayList;
import java.util.Arrays;

/**
 * Write a program BruteCollinearPoints.java that examines 4 points at a time and checks
 * whether they all lie on the same line segment, returning all such line segments. 
 * To check whether the 4 points p, q, r, and s are collinear, check whether the three slopes 
 * between p and q, between p and r, and between p and s are all equal.
 *
 */
public class BruteCollinearPoints {
    private ArrayList<LineSegment> segmentList = new ArrayList<>();
	/**
	 * Corner cases. Throw a java.lang.IllegalArgumentException if the argument to the constructor is null, 
	 * if any point in the array is null, or if the argument to the constructor contains a repeated point.
	 * The method segments() should include each line segment containing 4 points exactly once. 
	 * If 4 points appear on a line segment in the order p→q→r→s, then you should include either 
	 * the line segment p→s or s→p (but not both) and 
	 * you should not include subsegments such as p→r or q→r. For simplicity, 
	 * we will not supply any input to BruteCollinearPoints that has 5 or more collinear points.
	 * @param points
	 */
    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
    	if (points == null) throw new IllegalArgumentException("Array points is null"); //if null then illegal
    	for (int i=0; i < points.length; i++) {
    		if (points[i] == null) throw new IllegalArgumentException("Point is null");
    		Point temp = points[i];
    		for (int j=i+1; j < points.length; j++) {
    			if (temp.compareTo(points[j]) == 0) {
    				throw new IllegalArgumentException(); // repetition Point
    			}
    		}
    	}
    	
    	
    	Point[] aux = Arrays.copyOf(points, points.length);
        Arrays.sort(aux);
        int len = aux.length;
        
        for (int i = 0; i < len; i++)
            for (int j = i + 1; j < len; j++)
                for (int k = j + 1; k < len; k++) {
                    for (int m = k + 1; m < len; m++) {
                        Point p1 = aux[i], p2 = aux[j], p3 = aux[k], p4 = aux[m];
                        if (p1.slopeTo(p2) == p1.slopeTo(p3) && p1.slopeTo(p2) == p1.slopeTo(p4)) {
                            segmentList.add(new LineSegment(p1, p4));
                        }
                    }
                }
       
//        
//        if (aux[0].slopeTo(aux[1])  == aux[0].slopeTo(aux[2]) 
//            && aux[0].slopeTo(aux[2]) == aux[0].slopeTo(aux[3])) {
//        	segmentList.add(new LineSegment(aux[0], aux[3]));
//        }
        
    }
    
    
    public int numberOfSegments() {       // the number of line segments
        return segmentList.size();
    }
    
    public LineSegment[] segments() {               // the line segments
        LineSegment[] segments = new LineSegment[segmentList.size()];
        return segmentList.toArray(segments);    	
    }
}
