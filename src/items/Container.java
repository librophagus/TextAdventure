package items;

import java.util.LinkedList;
import people.Being;

public abstract class Container {

	private int capacity;
	private int size;
	private Container enclosure;
	private Being owner;
	private LinkedList<Item> contents;
	private boolean[] holdType;
	
	public Container(int c) {
		this.capacity = c;
		contents = new LinkedList<Item>();
		holdType = new boolean[ItemState.getNumStates()];
		//TODO: How to represent different item types in an array state?
	}
	
	public abstract boolean addItem(Item i);
	public abstract Item removeItem(Item i);
	
	public abstract boolean insertIn(Container c);
	public abstract boolean ownsNow(Being p);
	
	public boolean isFull() {
		if(size >= capacity) {
			return true;
		} else return false;
	}
	
	public int capDiff() {
		return capacity - size;
	}
	
}
