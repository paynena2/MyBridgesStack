import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.base.Array;
import bridges.base.ElementVisualizer;
@SuppressWarnings({"unchecked","rawtypes"})
public class MyBridgesStack<E> implements StackInterface<E>{
	private SLelement firstNode; //Acts as the first node in the linked chain
	private Bridges bridges = new Bridges(0, "paynena2","197701776935"); //setting up bridges communication
	int counter = 0; //for use later in determining the size of the array needed for the elements
	/*
	 *calls the various methods that are implemented from stackInterface
	 */
	public static void main(String[] args) throws Exception {
		MyBridgesStack stack = new MyBridgesStack();
		stack.setUp();
		stack.push(1);
		stack.push(2);
		stack.push("Hello");
		stack.pop();
		stack.display();
	}
	/**
	 * Sets the title for my bridges app
	 */
	public void setUp() {
		bridges.setTitle("Nathaniel Payne");
	}
	/**
	 * Constructor for bridges class
	 */
	public MyBridgesStack() {
		firstNode = null;
	}
	/**
	 * pushes an element of type E into the stack
	 */
	@Override
	public void push(E newEntry) {
		SLelement newNode = new SLelement(newEntry, firstNode);
		firstNode = newNode;
		counter++;
	}

	/**
	 * Pops the element off the top of the stack
	 */
	@Override
	public E pop() {
		E data;
		if(firstNode != null) {
			data = (E) firstNode.getValue();
			firstNode = firstNode.getNext();
		}
		else {
			throw new IndexOutOfBoundsException("Stack empty");
		}
		counter--;
		return data;
	}
	
	/**
	 * Sees what the next element in the stack is without removing it
	 */
	@Override
	public E peek() {
		if(firstNode != null) {
			if(firstNode.getValue() != null) {
				return (E) firstNode.getValue();
			}
			return null;
		}
		return null;
	}

	/**
	 * Checks if the stack is empty
	 */
	@Override
	public boolean isEmpty() {
		if(firstNode == null) {
			return true;
		}
		return false;
	}

	/**
	 * clears the stack
	 */
	@Override
	public void clear() {
		firstNode = null;
	}
	
	/**
	 * Outputs the link for the bridges visualization
	 * @throws Exception
	 */
	public void display() throws Exception {
		Array<E> data = new Array<E>(counter);
		ElementVisualizer vis;
		if(firstNode == null) {
			throw new IllegalArgumentException("Cannot visualize empty stack");
		}
		else {
			MyBridgesStack temp1 = this;
			MyBridgesStack temp2 = new MyBridgesStack();
			int count = 0;
			/*
			 * Empties the stack and saves the values into the bridges array
			 */
			while(!temp1.isEmpty()) {
				E item = (E)temp1.pop();
				temp2.push(item);
				data.getElement(count).setValue(item);
				data.getElement(count).setLabel(String.valueOf(item));
				vis = data.getElement(count).getVisualizer();
				vis.setColor("green");
				count++;
			}
			/*
			 * resets the primary stack back to its original value
			 */
			while(!temp2.isEmpty()) {
				E item = (E)temp2.pop();
				temp1.push(item);
			}
		}
		bridges.setDataStructure(data);
		bridges.visualize();
	}
	
}
