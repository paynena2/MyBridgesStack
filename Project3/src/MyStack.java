import java.lang.reflect.Array;
import java.util.EmptyStackException;

@SuppressWarnings({"rawtypes","unchecked", "unused","hiding"})

public class MyStack<E> implements StackInterface<E> {
	
	private MyNode firstNode;
	private int alreadyDisplayed = 0;
	
	/**
	 * Creates a new stack and calls various test cases on it
	 * @param args
	 */
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push("hello");
		stack.display();
		stack.pop();
		stack.pop();
		System.out.println();
		System.out.println("Called pop twice");
		stack.display();
		stack.pop();
		System.out.println();
		System.out.println("Called pop once more");
		stack.display();
		stack.pop();
		System.out.println("Called pop again");
		stack.display();
	}
	
	/**
	 * Constructor that sets the firstNode var to null
	 */
	public MyStack() {
		firstNode = null;
	}
	
	/**
	 * Pushes an element of type E on to the top of the stack
	 */
	@Override
	public void push(E newEntry) {
		MyNode newNode = new MyNode(newEntry, firstNode);
		firstNode = newNode;
	}

	/**
	 * Pops the element off the top of the stack
	 */
	@Override
	public E pop() {
		E data;
		if(firstNode != null) {
			data = (E) firstNode.getData();
			firstNode = firstNode.next;
		}
		else {
			throw new IndexOutOfBoundsException("Stack is empty");
		}
		return data;
	
	}

	/**
	 * Checks what the top value is without taking it off the stack.
	 */
	@Override
	public E peek() {
		if(firstNode != null) {
			if(firstNode.getData() != null) {
				return (E)firstNode.getData();
			}
			return null;
		}
		else {
			return null;
		}
	}

	/**
	 * checks if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		if(firstNode == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Displays the stack in the console
	 */
	public void display() {
		if(firstNode == null) {
			System.out.println("Stack is empty");
		}
		else {
			MyStack temp = this;
			MyStack temp2 = new MyStack();
			if(alreadyDisplayed == 0) {
				System.out.print("Pushed: ");
				while(!temp.isEmpty()) {
					E item = (E)temp.pop();
					if(temp.peek() != null) {
						System.out.print(item + ", ");
					}
					else {
						System.out.print("and " + item);
					}
					temp2.push(item);
				}
				while(!temp2.isEmpty()) {
					temp.push(temp2.pop());
				}
				alreadyDisplayed = 1;
			}
			int control = 0;
			while(!temp.isEmpty()) {
				E item = (E)temp.pop();
				temp2.push(item);
				if(temp.peek() != null && control == 0) {
					System.out.println("\nTop of stack: " + item);
					control = 1;
				}
				else if(temp.peek() != null && control == 1) {
					System.out.println("\t      " + item);
				}
				else if(control == 0) {
					System.out.println("\nTop and bottom of stack: " + item);
					control = 1;
				}
				else if(control == 1) {
					System.out.println("Stack bottom: " + item);
				}
			}
			while(!temp2.isEmpty()) {
				temp.push(temp2.pop());
			}
		}
	}

	/**
	 * Clears the stack
	 */
	@Override
	public void clear() {
		firstNode = null;
	}
	
	/**
	 * private helper class for the Stack, creates and gives values to the nodes
	 * to create a linked chain
	 * @author Allen Payne
	 *
	 * @param <E>
	 */
	private class MyNode<E>{
		private E data;
		private MyNode next;
		
		/**
		 * Constructor
		 */
		public MyNode() {
			
		}
		/**
		 * Constructor that sets the data 
		 * @param data
		 */
		public MyNode(E data) {
			this.data = data;
		}
		/**
		 * Constructor that sets the data and the next node in the chain
		 * @param data
		 * @param next
		 */
		public MyNode(E data, MyNode next) {
			this.data = data;
			this.next = next;
		}
		/**
		 * Returns the data the node holds
		 * @return
		 */
		public E getData() {
			return data;
		}
		/**
		 * sets the data the node holds
		 * @param data
		 */
		public void setData(E data) {
			this.data = data;
		}
		/**
		 * Sets the next link in the chain
		 * @param next
		 */
		public void setNext(MyNode next) {
			this.next = next;
		}
		/**
		 * Returns the next link in the chain
		 * @return
		 */
		public MyNode getNext() {
			return next;
		}
	}
	
}
