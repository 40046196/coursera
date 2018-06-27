import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Deque is decided to be a linked list.
 *   Since it has addFirst  and addLast ( also removeFirst, removeLast)
 *   Then it should be double linked list ,   with first and last parameters pointer
 *   
 *   Your submission may not call library functions except those in StdIn, StdOut, StdRandom, j
 *   ava.lang, java.util.Iterator, and java.util.NoSuchElementException. 
 *   In particular, do not use either java.util.LinkedList or java.util.ArrayList.
 *   
 * @author Leo Sudarma
 *
 * @param <Item>
 */

public class Deque<Item> implements Iterable<Item> {
	private Node firstNode;
	private Node lastNode;
	private int size;
	

	/**
	 * This is the inner node class that can link 
	 * to next or previous node
	 *
	 */
	private class Node {
		Item item;
		Node next;  
		Node prev;
	}

	/**
	 * construct empty deque
	 */
	public Deque() {  //// construct an empty deque
		this.firstNode = null;
		this.lastNode = firstNode;
		this.size = 0;
	}
	
	// check if dequeu is empty, first will be null when empty
	public boolean isEmpty() {  //// is the deque empty?
		return size == 0;
	}
	
	/** 
	 * The size of the dequeu
	 * @return
	 */
	public int size()   { // return the number of items on the deque
		return this.size;
	}

	/**
	 * add new item in the first position
	 *    Throw a java.lang.IllegalArgumentException if the client calls either 
	 *    addFirst() or addLast() with a null argument.
	 * 
	 * @param item
	 */
	public void addFirst(Item item)  {   // add the item to the front
        if (item == null) {
            throw new IllegalArgumentException("Item must not null!");
        }
        
		//Create a new node and assign the item
		Node newNode = new Node();
		newNode.item = item;
	

		if (isEmpty()) {  // if empty, set firstNode and lastNode to this newNode
			this.firstNode = newNode;
			this.lastNode = firstNode;
		} else {   //  the new Node become First, 
			// the newNode.next to the firstNode, then set firstNode as NewNode;
			newNode.next = firstNode;
			firstNode.prev = newNode;
			firstNode = newNode;
			
		}
		//  increase the size
		size++;

	}	
	
	/**
	 *    Throw a java.lang.IllegalArgumentException if the client calls either 
	 *    addFirst() or addLast() with a null argument.	 * @param item
	 */
	public void addLast(Item item) { // add the item to the end
        if (item == null) {
            throw new IllegalArgumentException ("Item must not null!");
        }
        
		//Create a new node and assign the item
		Node newNode = new Node();
		newNode.item = item;

		if (isEmpty()) {  // if empty, set firstNode and lastNode to this newNode
			this.firstNode = newNode;
			this.lastNode = firstNode;
		} else {   //  the new Node become lastNode, 
			// the newNode.next to the firstNode, then set firstNode as NewNode;
			newNode.prev = lastNode;
			lastNode.next = newNode;
			lastNode = newNode;
			
		}
		//  increase the size
		size++;		
	}
	
	/*
	 * Throw a java.util.NoSuchElementException if the client calls either 
	 * removeFirst() or removeLast when the deque is empty.
	 */
	public Item removeFirst() {
		
		if (isEmpty()){
			throw new NoSuchElementException ("Dequeu is empty");
		}
		
		Item item = firstNode.item;
		
		if (this.size() == 1) {  //  destroy first and last node
			this.firstNode = null;
			this.lastNode = null;
		} else {   // set firstNode set to the next Node and destroy the node
			this.firstNode = firstNode.next;
			this.firstNode.prev = null;  // destroy the prev 
		}
		// decrease the size
		this.size--;
		
		return item;
		
	}
	
	/*
	 * Throw a java.util.NoSuchElementException if the client calls either 
	 * removeFirst() or removeLast when the deque is empty.
	 */	
	public Item removeLast()  {               // remove and return the item from the end
		if (isEmpty()){
			throw new NoSuchElementException ("Dequeu is empty");
		}
		
		Item item = lastNode.item;
		
		if (this.size() == 1) {  //  destroy first and last node
			this.firstNode = null;
			this.lastNode = null;
		} else {   // set lastNode set to the previous Node and destroy the node
			this.lastNode = lastNode.prev;  // last node pointer to previous one.
			this.lastNode.next = null;  // destroy the next node. 
		}
		// decrease the size
		this.size--;
		
		return item;
	}
	
	 // return an iterator over items in order from front to end
	public Iterator<Item> iterator()   {     
		return new ListIterator();
	}
	
	/**
	 * 
	 * Throw a java.util.NoSuchElementException if the client calls the next() method 
	 * in the iterator when there are no more items to return.
	 *
	 */
	private class ListIterator implements Iterator<Item> {
		private Node current = firstNode;  //  will iterator from first to last
		
		
		/** Returns true if current position is not null */
		@Override
		public boolean hasNext() {
			return (current != null);
		}

		/** returns the current item and increments the pointer */
		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException("Element next not found");
			}
			
			Item item = current.item;
			current = current.next;
			
			return item;
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException("Iterator remove function not supported.");
		}
	}	
	
//	public static void main(String[] args) {  // unit testing (optional)
//		Deque deque = new Deque();
//		StdOut.println("Helo Deque");
//		deque.addFirst("Lion");
//		deque.addFirst("Tiger");
//		deque.addFirst("Seahorse");
//		deque.addFirst("Pikachu");
//		deque.addLast("Ash Ketchum");
//		StdOut.println(deque.size);
//	    Iterator iter = deque.iterator();
//	    while (iter.hasNext()) {
//	            String str = (String) iter.next();
//	            StdOut.println(str);
//	    }
//	    StdOut.println("All is good order now remove lastNode");
//	    deque.removeLast();
//	    iter = deque.iterator();
//	    while (iter.hasNext()) {
//	            String str = (String) iter.next();
//	            StdOut.println(str);
//	    }
////	    StdOut.println("All is good order");
////	    try {
////	    deque.removeFirst();
////	    } catch (Exception ex ) {
////		    StdOut.println(ex.getMessage());
////	    }
////	    deque.addFirst("Aheng");
////	    StdOut.println(deque.firstNode.item);
////		StdOut.println(deque.removeFirst());
////		StdOut.println(deque.removeFirst());
//	}
}
