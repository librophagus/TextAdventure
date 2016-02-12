package items;

import java.util.Iterator;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import people.Being;
import places.Room;

/**
 * @author zephyr
 * The multi-featured container that keeps track of the Items held by People and Rooms.
 * It is drop-in ready for the JList component of the GUI and can be iterated over
 * in a for loop or other iterable context.
 */
public class Inventory extends DefaultListModel<Item> implements ListModel<Item>, Iterable<Item> {

	private static final long serialVersionUID = -5012488417443451974L;
	private Room location;
	private Being holder;

	/**
	 * Constructs a new ItemBag and sets its holder to
	 * a specific Room.
	 * @param r The Room to be located in.
	 */
	public Inventory(Room r) {
		super();
		this.location = r;
		this.holder = null;
	}

	/**
	 * Constructs a new ItemBag and sets it holder to
	 * a specific Being.
	 * @param p The Being to hold the Bag.
	 */
	public Inventory(Being p) {
		super();
		this.location = null;
		this.holder = p;
	}
	
	public Inventory() {
		super();
		this.location = null;
		this.holder = null;
	}
	
	public Inventory(int... indices) {
		super();
		this.location = null;
		this.holder = null;
		this.fillWith(indices);
	}
	
	public Inventory(int[] indices, Room r) {
		super();
		this.location = r;
		this.holder = null;
		this.fillWith(indices);
	}

	/**
	 * Transfers all of the ite
	 * @param other
	 */
	public void transferItems(Inventory other) {
		for (Item i : other) {
			addElement(i);
		}
		other.clear();
	}

	public Room getLocation() {
		return location;
	}

	public Being getHolder() {
		return holder;
	}

	public void setLocation(Room r) {
		this.location = r;
	}

	public void setOwner(Being p) {
		this.holder = p;
	}
	
	@Override
	public boolean contains(Object item) {
		Item other = (Item) item;
		for (Item it : this) {
			if (it.compareTo(other) == 0) {
				return true;
			}
		}
		return false;
	}
	
	public int contains(String name) {
		for (Item i : this) {
			if (i.toString().equals(name)) {
				return i.getID();
			}
		}
		return -1;
	}
	
	
	public boolean containsID(int ID) {
		for (Item it : this) {
			if (it.getID() == ID) {
				return true;
			}
		}
		return false;
	}
	
	public Item removeID(int ID) {
		for (Item it : this) {
			if (it.getID() == ID) {
				this.removeElement(it);
				return it;
			}
		}
		return null;
	}
	
	public void fillWith(int... indices) {
		for (int i : indices) {
			addElement(ItemFactory.instance().createItem(i));
		}
	}

	@Override
	public Iterator<Item> iterator() {
		return new InventoryIterator(this);
	}

	/**
	 * @author zephyr
	 *
	 */
	public class InventoryIterator implements Iterator<Item> {
		
		Inventory bag;
		int pointer;
		
		/**
		 * @param ib
		 */
		public InventoryIterator(Inventory ib) {
			bag = ib;
			pointer = 0;
		}
		
		/* (non-Javadoc)
		 * @see java.util.Iterator#hasNext()
		 */
		@Override
		public boolean hasNext() {
			if (bag.isEmpty()) return false;
			if (pointer <= bag.getSize()-1) {
				return true;
			} else return false;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#next()
		 */
		@Override
		public Item next() {
			if (pointer < bag.getSize()) {
				Item out = bag.getElementAt(pointer);
				pointer++;
				return out;
			} else return null;
		}

		/* (non-Javadoc)
		 * @see java.util.Iterator#remove()
		 */
		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}

	}

}
