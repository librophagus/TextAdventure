package items.equip;

import items.Item;

/**
 * @author zephyr
 * A slot is a place for an item, usually attached to a person
 * or sometimes another item.
 */
public class Slot {

	private Item item;
	
	public Slot() {
		//Empty Slot
	}
	
	public Slot(Item i) {
		this.item = i;
	}
	
	public void setItem(Item i) {
		this.item = i;
	}
	
	public Item getItem() {
		return item;
	}
	
	public Item removeItem() {
		Item i = item;
		this.item = null;
		return i;
	}
}
