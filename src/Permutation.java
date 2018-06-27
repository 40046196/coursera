import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Randomly print input from file
 *
 */

public class Permutation {

   public static void main(String[] args) {
        int ctr = Integer.parseInt(args[0]); 

        RandomizedQueue<String> queue = new RandomizedQueue<String>();
        
        while (!StdIn.isEmpty()) {
             queue.enqueue(StdIn.readString());
        }
        for (int i = 0; i < ctr; i++) {
           StdOut.println(queue.dequeue());
        }
   }
}
