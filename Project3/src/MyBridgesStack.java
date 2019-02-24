import bridges.base.SLelement;
import bridges.connect.Bridges;
import bridges.base.Array;
import bridges.base.ElementVisualizer;
@SuppressWarnings({"unchecked","rawtypes"})
public class MyBridgesStack<E> implements StackInterface<E>{
	private SLelement firstNode;
	private Bridges bridges = new Bridges(0, "paynena2","197701776935");
	int counter = 0;
	public static void main(String[] args) {
		MyBridgesStack stack = new MyBridgesStack();
		stack.setUp();
		stack.push(1);
		stack.push(2);
		//stack.push("Hello");
	}
	public void setUp() {
		bridges.setTitle("Nathaniel Payne");
	}
	public MyBridgesStack() {
		firstNode = null;
	}
	@Override
	public void push(E newEntry) {
		SLelement newNode = new SLelement(newEntry, firstNode);
		firstNode = newNode;
		counter++;
	}

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

	@Override
	public boolean isEmpty() {
		if(firstNode == null) {
			return true;
		}
		return false;
	}

	@Override
	public void clear() {
		firstNode = null;
	}
	
	public void display() throws Exception {
		Array<E> data = new Array<E>(counter);
		ElementVisualizer vis = new ElementVisualizer();
		if(firstNode == null) {
			throw new IllegalArgumentException("Cannot visualize empty stack");
		}
		else {
			MyBridgesStack temp1 = this;
			MyBridgesStack temp2 = new MyBridgesStack();
			int count = 0;
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
