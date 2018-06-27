import edu.princeton.cs.algs4.WeightedQuickUnionUF;


/**
 * Percolation  - The assignment week 1 on Algorithm I coursera
 * check the detail for the instruction at 
 * http://coursera.cs.princeton.edu/algs4/assignments/percolation.html 
 * 
 * Corner cases.  By convention, the row and column indices are integers between 1 and n, 
 * where (1, 1) is the upper-left site: Throw a java.lang.IllegalArgumentException 
 * if any argument to open(), isOpen(), or isFull() is outside its prescribed range. 
 * The constructor should throw a java.lang.IllegalArgumentException if n ≤ 0.

 *  I simulate that a grid of size 2, will be construct like that..  
         0                  1       
 * =================================
 0 |             |                |                  ( row, col )
 * |  1= (1,1)   |  2= (1,2)      | 
 * =================================
 * |             |                | 
 1 |  3= (2,1)   | 4 = (2,2)      | 
 * =================================
 * 
 * Performance requirements.  
 * The constructor should take time proportional to n2; 
 * all methods should take constant time plus a constant number of calls to 
 * the union–find methods union(), find(), connected(), and count().
 * 
 * 	 * Q. How to check whether an N-by-N system percolates?
			􀉾Create an object for each site and name them 0 to N 2 – 1.
			􀉾Sites are in same component if connected by open sites.
 * 
 * @author Leo Sudarma
 *
 */

public class Percolation {
	private final int gridSize;
	private final boolean[][] grid;
	private final WeightedQuickUnionUF qu; 
	private final WeightedQuickUnionUF qu2; 
	private int topLiner = 1;
	private final int bottomLiner;
	private int numberOfOpenSites = 0;
	
	/**
	 * Create nxn grid size,  initialze as blocked grid.
	 * 	ex: if n = 2, then create a grid of 2x2 with all values as false (blocked).
	 * Nb: because top right corner is marked as (1,1), meanwhile grid starts at 0, so we have to add n * n + 2
	 * 
	 * The constructor should take time proportional to n^2  plus a constant number of calls 
	 * 	to the union–find methods union(),,find(), connected(), and count().
	 * 

	 * 
	 * 
	 * @param n
	 */
	public Percolation(int n)      {          // create n-by-n grid, with all sites blocked
		if (n <= 0) {
			throw new java.lang.IllegalArgumentException("The Grid size must be at least 1x1");
		}
		this.gridSize = n;

		bottomLiner = n * n +1;
		topLiner = 0;
		numberOfOpenSites = 0;

		grid = new boolean[n][n];  // by default will be false
		
		qu = new WeightedQuickUnionUF(n*n + 2); // extra for the border at (0 0) and (n * n  +1).
		qu2 =new WeightedQuickUnionUF(n*n + 2);
	}
	
	/**
	 * If is its blocked then open the grid location
	 * @param row
	 * @param col
	 */
	public void open(int row, int col) {   // open site (row, col) if it is not open already
		if (row < 1 ||  row > gridSize || col < 1 || col > gridSize)   {
			throw new IllegalArgumentException("Row is wrong");
		}
		if (!isOpen(row, col)) {  // if open , calculate the object location,  row-1 
			
			int gridIndex = this.getIndex(row, col);
			// if row = 1, all row 1 will be connected to virtual 0 link..
			// if row = bottom(gridsize), it will connectedt o virtual bottom link.
			if (row == 1) {  // if row = 1 , it will open from the top line
				qu.union(topLiner, gridIndex);
				qu2.union(topLiner, gridIndex);
			}
			if (row == gridSize) {  // if exmple (5, 4)    it connected to bottom line
				qu.union(bottomLiner, gridIndex);
				qu2.union(gridIndex, gridIndex);
			}
			grid[row-1][col-1] = true;  // set as open location			
			
			this.numberOfOpenSites++;
			
			if (row > 1) {
				if (isOpen(row-1, col)) {   // check top connection
					qu.union(gridIndex, gridIndex - gridSize);
					qu2.union(gridIndex, gridIndex - gridSize);
				} 
			}
			if (row < gridSize) {
				if (isOpen(row + 1, col)) {   //  bottom connected?
					qu.union(gridIndex, gridIndex +  gridSize);
					qu2.union(gridIndex, gridIndex +  gridSize);
				}
			}
			if (col > 1) {
				if (isOpen(row, col - 1)) {  // left connected?
					qu.union(gridIndex, gridIndex - 1);
					qu2.union(gridIndex, gridIndex - 1);
				}
			}
			if (col < gridSize)  {
				if (isOpen(row, col + 1)) {   // right connected
					qu.union(gridIndex, gridIndex + 1);
					qu2.union(gridIndex, gridIndex + 1);
				}
			}
			
			
			
		}
	}
	

	
	/**
	 * Return the current status of the grid[row][col].
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isOpen(int row, int col) { // is site (row, col) open?
		if (row < 1 ||  row > gridSize || col < 1 || col > gridSize)   {
			throw new IllegalArgumentException("Row is wrong");
		}
		return grid[row-1][col-1];
	}
	
	public boolean isFull(int row, int col)  {// is site (row, col) full?
		if (row < 1 || row > gridSize || col < 1 || col > gridSize)   {
			throw new IllegalArgumentException("Row is wrong");
		}		

		int gridIndex = this.getIndex(row, col);		
		return qu2.connected(topLiner, gridIndex);		
	}
	
	public int numberOfOpenSites()  {     // number of open sites
		return this.numberOfOpenSites;
	}
	
	/**
	 * check the status of bottom is it connect to top
	 * @return
	 */
	public boolean percolates()  {            // does the system percolate?
//		boolean isConnect = false;
//		for (int i=1; i <= gridSize; i++) {
//			int cellNumber = (gridSize  * (gridSize-1)) + i;
//			isConnect = qu.connected(topLiner, cellNumber);
//			if (isConnect) break;
//		}
//		return isConnect;
		return qu.connected(topLiner, bottomLiner);
	}
	
	
	/**
	 * N grid = 2; (row -1) * N +  col
 * =================================
 0 |             |                |                
 * |  1= (1,1)   |  2= (1,2)      | 
 * =================================
 * |             |                | 
 1 |  3= (2,1)   | 4 = (2,2)      | 
 * =================================
 * |             |                | 
 3 |  5= (3,1)   | 6 = (3,2)      | 
 * =================================
 * 
 * Ex: (3,1) = return  (3-1)  * 2   + (2) = 
 * 
	 * @param row
	 * @param col
	 * @return
	 */
    private int getIndex(int row, int col) {
//		if (row <= 0 || col <= 0 || row > gridSize || col > gridSize) {
//			throw new java.lang.IllegalArgumentException("The column must be at least 1");
//		}
        return ((row - 1) *  this.gridSize) + col;
    }	
	
	public static void main(String[] args) {  // test client (optional)
	}
}
