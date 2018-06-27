import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.StdOut;


/**
 * 
 * @author Leo Sudarma
 *
 */
public class PercolationStats {

	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	
	private double APPROX_VALUE_PROBABILITY;
	/**
	 * The constructor should throw a java.lang.IllegalArgumentException if either n ≤ 0 or trials ≤ 0.
	 * @param n
	 * @param trials
	 */
	public PercolationStats(int n, int trials)  {  // perform trials independent experiments on an n-by-n grid
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("N must be > 0  or  trial > 0");
		}
		
		APPROX_VALUE_PROBABILITY = 1.96;
		
		double[] percolationTrials = new double[trials];
		
		// repeat trials for each Percolation, until get percolated..
		for (int i=0; i < trials; i++) {
			Percolation p= new Percolation(n);
			int ctr= 0;
			while (!p.percolates()) {  // run until it percolates
				int row = StdRandom.uniform(n) + 1;   // simulate row 
                int col = StdRandom.uniform(n) + 1;   // simulate col
                if (!p.isOpen(row, col)) { // if not open, then open the cell
                	p.open(row, col);
                	ctr++;    // count the opening cell
                }
			}
			
			percolationTrials[i] = ctr / (double) (n * n);   // count the probability how many run till percolate.
		}
		
		this.mean = StdStats.mean(percolationTrials);
		this.stddev = StdStats.stddev(percolationTrials);
		this.confidenceLo = mean - (APPROX_VALUE_PROBABILITY * stddev / Math.sqrt(trials));
		this.confidenceHi = mean + (APPROX_VALUE_PROBABILITY * stddev / Math.sqrt(trials));
		
	}
	
	/**
	 * @return
	 */
	public double mean()      {                    // sample mean of percolation threshold
		return mean;
	}
	public double stddev()  {                       // sample standard deviation of percolation threshold
		return stddev;
	}
	public double confidenceLo() {                 // low  endpoint of 95% confidence interval
		return this.confidenceLo;
	}
	public double confidenceHi() {                 // high endpoint of 95% confidence interval
		return this.confidenceHi;
	}
	
	public static void main(String[] args) {        // test client (described below)
		int n = Integer.parseInt(args[0]);
		int t = Integer.parseInt(args[1]);
		
		PercolationStats st = new PercolationStats(n, t);
		
		StdOut.println("Means=" + st.mean );
		StdOut.println("stddev=" + st.stddev );
		StdOut.println("95% confidence interval = [" + st.confidenceLo + "," + st.confidenceHi + "]" );
	}
}
