package items;

/**
 * @author zephyr
 *
 */
public class Item implements Comparable<Item> {
	
	private int ID;
	private String name;
	private int value;
	private int bulk;
	private String description;
	private ItemState state;
	
	/**
	 * Constructs a new Item.
	 * @param n The name of the item
	 * @param v The value of the item in Tokens.
	 * @param w The weight of the item.
	 */
	public Item(int id, String n, int v, int b, String d) {
		this.ID = id;
		this.name = n;
		this.value = v;
		this.bulk = b;
		this.description = d;
	}
	
	public void useItem() {
		//Do Nothing!
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.name;
	}
	
	public String verbose() {
		return description;
	}
	
	public int getID() {
		return ID;
	}
	
	public boolean transformOnLook() {
		return false;
	}
	
	public boolean isEdible() {
		return false;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Item other) {
		if (this.ID == other.getID()) {
			return 0;
		}
		else return -1;
	}

}