import java.util.Arrays;

import edu.princeton.cs.algs4.UF;

public class UFMain {

    private int[] parent;  // parent[i] = parent of i
    private byte[] rank;   // rank[i] = rank of subtree rooted at i (never more than 31)
    private int count;   

    // set parent initial value  S = { 0,1,2,3,,,,);
    public UFMain(int n) {
        if (n < 0) throw new IllegalArgumentException();
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }    	
    }
    
    // validate that p is a valid index
    private void validate(int p) {
        int n = parent.length;
        if (p < 0) {   //  || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));  
        }
    }    
    
    private void testParent() {
    	parent = new int[] {0,2,2,5,5,7,7,10,12,12};
    }
    
    public int count(int p) {
    	int count = 0;
    	for (int i=0; i < parent.length; i++) {
    		if (parent[i] == p)  count++;
    	}
        return count;
    }    
    
    /**
     * simple remove p,
     * note still has error when u remove 2, then remove 9, 
     *  cause the lenght 0-8,  and validate doesnt count 9,
     * @param p
     */
    private void remove(int p) {
    	validate(p);
    	
    	int[] newParent = new int[parent.length - count(p)];
    	rank = new byte [parent.length - count(p)];
    	int indexParent = 0;
    	int indexNewParent = 0;

    	while (indexParent < parent.length) {
        	if (parent[indexParent] == p) {
        		indexParent++;
        		continue;
        	}
        	newParent[indexNewParent] = parent[indexParent];
        	rank[indexNewParent] = 0;
        	indexNewParent++;
        	indexParent++;

        }    	
    	count = newParent.length;
    	parent = newParent;
    }
    
    private int successor(int p) {
    	validate(p);
    	int child = -1;
    	for (int i = 0; i < parent.length; i++) {
    		if (parent[i] > p) {
    			child = parent[i];
    			break;
    		}
    	}
     	return child;
    }    
    
    
    
    
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "  UF [parent=" + Arrays.toString(parent) + ", rank=" + Arrays.toString(rank) + ", count=" + count + "]";
	}    
    
	public static void main(String[] args) {
		UFMain uf = new UFMain(10);
        StdOut.println("UFMAIN components" +  uf.toString());
        
        uf.testParent();
        StdOut.println("UFMAIN components" +  uf.toString());

        
        
        uf.remove(12);
        StdOut.println("UFMAIN components" +  uf.toString());
        
        StdOut.println(uf.successor(2));
        
//        
//        uf.remove(8);
//        StdOut.println("UFMAIN components" +  uf.toString());

        
        
	}

	
}
