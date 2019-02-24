import java.lang.reflect.Array;
import java.util.EmptyStackException;

@SuppressWarnings({"rawtypes","unchecked", "unused","hiding"})

public class MyStack<E> implements StackInterface<E> {
	
	private MyNode firstNode;
	private int alreadyDisplayed = 0;
	
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
	
	public MyStack() {
		firstNode = null;
	}
	
	@Override
	public void push(E newEntry) {
		MyNode newNode = new MyNode(newEntry, firstNode);
		firstNode = newNode;
	}

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

	@Override
	public boolean isEmpty() {
		if(firstNode == null) {
			return true;
		}
		else {
			return false;
		}
	}
	
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

	@Override
	public void clear() {
		firstNode = null;
	}
	
	private class MyNode<E>{
		private E data;
		private MyNode next;
		
		public MyNode() {
			
		}
		public MyNode(E data) {
			this.data = data;
		}
		public MyNode(E data, MyNode next) {
			this.data = data;
			this.next = next;
		}
		public E getData() {
			return data;
		}
		public void setData(E data) {
			this.data = data;
		}
		public void setNext(MyNode next) {
			this.next = next;
		}
		public MyNode getNext() {
			return next;
		}
	}
	
}
