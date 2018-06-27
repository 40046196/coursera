import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A Queue is an array based item
 * 
 * 
 * Randomized queue. A randomized queue is similar to a stack or queue, except that the item removed is chosen 
 * uniformly at random from items in the data structure. Create a generic data type 
 *
 * @param <Item>
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
	private int size;  
	private int queueSize = 1;  // the initial queue size

	/**
	 * When the queus is initiated, the queue size is double of the initial size
	 */
	public RandomizedQueue() {
		this.queueSize *= 2;  //  twice the size
		this.queue = (Item[]) new Object[queueSize];
		this.size = 0; // zero object in the queue;
	}
	
	/**
	 * Return empty if size = 0
	 * @return
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Return the size of the queue
	 * @return
	 */
	public int size() {
		return this.size;
	}

	/**
	 * Add item to the queue
	 * Queue always add item based on FIFO
	 * Throw a java.lang.IllegalArgumentException if the client calls enqueue() with a null argument.
	 * 
	 * if the size already full, then double the array size
	 * @param item
	 */
	public void enqueue(Item item) {
		if (item == null) {
			throw new IllegalArgumentException("Item must not null");
		}
		
		resize();  // auto decide wheter to resize or not
		
		queue[size] = item; // set the item to the first queue
		this.size++;  // increase the size
		
	}
	
	/**
	 * resize the size of the Queue
	 *  2  Condition : 
	 *     a.  If size maximum  then double the queue capacity
	 *     b.  If size less than 25%, then half the queue capacity
	 */
	private void resize() {
		
		
		if (size >= 4)   // only if size > 4 (25%)
		{  
			if (this.size <= (this.queueSize/4)) {
				this.queueSize /= 2;  // half the queue size when queue is 25% 
			} 
			if (size > (queueSize/4) && size < queueSize)  {
				return;
			}
		}
		// double the capacity of the queue if already maximum 
		if (this.size == this.queueSize) {
			this.queueSize *= 2; 
		}
		
		Item[] tempQueue = (Item[]) new Object[queueSize];
        for (int i = 0; i < this.size; i++) {
        	tempQueue[i] = this.queue[i];
        }
        this.queue = tempQueue;
	}
	
	/**
	 * Throw a java.util.NoSuchElementException if the client calls either 
	 *       sample() or dequeue() when the randomized queue is empty.
	 * A randomized queue is similar to a stack or queue, 
	 * except that the item removed is chosen uniformly at random 
	 * from items in the data structure. 
	 * @return
	 */
	public Item dequeue() {
		if (isEmpty()) {
			throw new NoSuchElementException("Queue is empty!");
		}
		resize(); // auto check if it need to resize or not

		// pickup a  random Index between 0 and size
		//( between 0 and 5) will result 0 to 5 randomly
		int randIndex = StdRandom.uniform(0, size); 
		Item item = queue[randIndex]; // get the  random item to dequeue
		
		// restructured the new queue
		for (int i = randIndex; i < size; i++) {
			queue[i] = queue[i+1];
		}
		size--;  // decrease the item
		queue[size] = null; // set very end  as null (because you pop one at random
		
		return item;
	}
	
	/**
	// return a random item (but do not remove it)
	 * return the random queue from the index
	 * @return
	 */
	public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
		return queue[StdRandom.uniform(0, size)];
	}
	
	/**
	 * return an independent iterator over items in random order
	 */
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();

	}
	
	/*
	 * will return the iterator in random order
	 */
	private class ListIterator implements Iterator<Item> {
		
		private Item[] iteratorQueue;
		private int iteratorIndex = 0;
		
		public ListIterator() {
			iteratorQueue = (Item[]) new Object[size];
			// cloning the current queue and randomize the order
//			iteratorQueue = queue.clone();
			for (int i=0; i < size; i++) {
				iteratorQueue[i] = queue[i];
			}
			
			StdRandom.shuffle(iteratorQueue, 0, size); // shuffle the iterator order
		}
		
		@Override
		public boolean hasNext() {
			return (iteratorIndex < iteratorQueue.length);
		}

		@Override
		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException("Queue already empty");
			}
			
			Item item = iteratorQueue[iteratorIndex];
			iteratorIndex++;
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Remove method not supported");
		}
	}	
	
	
//	public static void main(String[] args) {
//		
//		RandomizedQueue queue = new RandomizedQueue();
//		StdOut.println(queue.size + " * " +  queue.queueSize);
//
//		queue.enqueue("Lion");
//		queue.enqueue("Tiger");
//		queue.enqueue("Rabbit");
//		queue.enqueue("Mangga");
//		queue.enqueue("Chicken");
//		queue.enqueue("Pokemon");
//		queue.enqueue("pIKACHU");
//		queue.enqueue("Raichu");
//		queue.enqueue("Zorro");
//		queue.enqueue("Jakarta");
//		Iterator<String> it = queue.iterator();
//		while (it.hasNext()) {
//			String str = it.next();
//			StdOut.print(str + "*");		
//		}
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue() + "  *  " + queue.size + " * " +  queue.queueSize);		
//		StdOut.println(queue.dequeue());
//	}



}
