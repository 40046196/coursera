import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;

public class Point implements Comparable<Point> {
    private int x, y;
    
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() { //   draws this point
    	StdDraw.point(x, y);
    	
    }
    public void drawTo(Point that) { // draws the line segment from this point to that point
    	StdDraw.line(this.x, this.y, that.x, that.y);
    }
    


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	/**
	 * The compareTo() method should compare points by their y-coordinates, 
	 * breaking ties by their x-coordinates. 
	 * Formally, the invoking point (x0, y0) is 
	 * less than the argument point (x1, y1) 
	 *    if and only if either y0 < y1 or if ( y0 = y1 and x0 < x1.)
	 *    
	 *  in easy saying, compare your current X, Y coordinat to the input Point coordinat
	 *  if the original coordinat is below the new Point return -1
	 *  if the original coordinat match the newPoint return 0
	 *  otherwise return 1
	 */
    public int compareTo(Point that) {
        if (this.y < that.y) {  // below point,  if and only if yo < y1
            return -1;
        } 
        
        // in this level, all y1 is >= y0
        if (this.y == that.y  && this.x < that.x) {  // x is lower , if y0 == y1 and x0 < x1
             return -1;
        } else if (this.y == that.y  && this.x == that.x) { // perfect match, return 0
             return 0;
        } 
        // all going here is upper point, return 1
        return 1;
    }
    
    /**
     * The slopeTo() method should return the slope between the invoking point (x0, y0) 
     * and the argument point (x1, y1), which is given by the formula (y1 − y0) / (x1 − x0). 
     * 
     * Treat the slope of a horizontal line segment as positive zero; y=0  ==> 0.00  
     * treat the slope of a vertical line segment as positive infinity; x=0 ==> POSITIVE_INFINITY
     * treat the slope of a degenerate line segment (between a point and itself) as negative infinity.
     * slope m = (y2-y1) / (x2-x1)
     * @param that
     * @return
     */
    public double slopeTo(Point that) {     // the slope between this point and that point
    	if (this.x == that.x && this.y == that.y) {  // the line point to itself
    		return Double.NEGATIVE_INFINITY;
    	}
    	if (this.x == that.x)  {  // if x2-x1=0; it give u y = VERTICAL_LINE
    		return Double.POSITIVE_INFINITY;
    	}
    	if (this.y == that.y) { 
    		return 0.0;  
    	}
    	
    	double slope = (double) (that.y - this.y) / (that.x - this.x);  //given by the formula (y1 − y0) / (x1 − x0). 
    	return slope;
    }
    
    /**
     * The slopeOrder() method should return a comparator that compares its two argument points 
     * by the slopes they make with the invoking point (x0, y0). 
     * Formally, the point (x1, y1) is less than the point (x2, y2) 
     * if and only if the slope (y1 − y0) / (x1 − x0) is less than the slope (y2 − y0) / (x2 − x0). 
     * 
     * Treat horizontal, vertical, and degenerate line segments as in the slopeTo() method.
     * @return
     */
    public Comparator<Point> slopeOrder()    {          // compare two points by slopes they make with this point
        return new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                double val = slopeTo(o1) - slopeTo(o2);
                if (val > 0) return 1;
                else if (val < 0) return -1;
                else return 0;
            }
        };
    }
    
    
//    public static void main(String[] args) {
//        Point p1 = new Point(10000,0);
//        Point p2 = new Point (0,10000);
//        Point p3 = new Point(3000,7000);
//        Point p4 = new Point(7000,3000);
//        Point p5 = new Point(20000,21000);
//        Point p6 = new Point(3000,4000);
//        Point p7 = new Point(14000,15000);
//        Point p8 = new Point(6000,4000);;
//        Point p11 = new Point(5,5);
//        Point p12 = new Point(10,10);
//        StdOut.println(p11.slopeOrder());
//        StdOut.println(p6.slopeTo(p6));
//        StdOut.println(p6.slopeTo(p3));
//        StdOut.println(p6.slopeTo(p8));    
//        StdOut.println(p11.slopeTo(p12));   
//    }
}
