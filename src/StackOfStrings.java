
public class StackOfStrings {
	private String[] stacks;
	private int size = 0;
	
	public StackOfStrings() {
		this.stacks = new String[30];
		this.size = 0;
	}
	
	private void push(String s) {
		stacks[size] = s;
		size++;
	}
	
	private String pop() {
		size--;
		return stacks[size];
	}
	
	private boolean isEmpty() {
		return size==0;
	}
	
	public static void main(String[] args) {
		StdOut.println("Stacts Strings ");
		StackOfStrings stack = new StackOfStrings();
		while (!StdIn.isEmpty()) {
			String s = StdIn.readString();
			if (s.equals("-")) {
				StdOut.print(stack.pop());
			}
			else stack.push(s);
		}

	}

}
