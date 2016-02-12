package people;

import items.Item;
import items.Food;
import items.Inventory;
import places.Room;

public abstract class Being {
	protected String name;
	//Bytes cannot go over 100
	protected byte stamina;
	protected byte hunger;
	//Bytes cannot go over 25
	protected byte power;
	protected byte focus;
	protected byte will;
	protected byte reflex;
	
	protected boolean isGirl;
	
	protected short age;
	protected short weight;
	
	//Percent values, 0-100
	protected byte injuries;
	protected byte tipsy;
	protected byte thirst;
	protected byte fatigue;
	protected byte temperature;
	protected byte breath;
	
	protected Room location;
	protected Inventory inventory;

	
	public Being(String name, boolean g) {
		this.name = name;
		this.stamina = 100;
		this.hunger = 100;
		this.power = 10;
		this.focus = 10;
		this.will = 10;
		this.reflex = 10;
		
		this.isGirl = g;
		this.weight = 100;
		this.injuries = 0;
		this.tipsy = 0;
		this.thirst = 0;
		this.fatigue = 0;
		this.temperature = 0;
		this.breath = 0;
		
		this.inventory = new Inventory(this);
		
	}
	
	public int getSP() {
		return stamina;
	}
	
	public void setSP(byte h) {
		this.stamina = h;
	}
	
	/**
	 * If the h value is positive, it will
	 * "heal" the Being. If it is negative,
	 * it will "damage" them.
	 * @param h
	 */
	public void modSP(int h) {
		this.stamina += h;
	}
	
	public int getHP() {
		return hunger;
	}
	
	public void setHP(byte h) {
		this.hunger = h;
	}
	
	/**
	 * If the h value is positive, it will
	 * "heal" the Being. If it is negative,
	 * it will "damage" them.
	 * @param h
	 */
	public void modHP(int h) {
		this.hunger += h;
	}
	
	public void setLocation(Room r) {
		this.location = r;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void give(Item i) {
		inventory.addElement(i);
	}
	
	public boolean drop(Item i) {
		return inventory.removeElement(i);
	}
	
	public void move(Room r) {
		location = r;
	}
	
	public Room getLocation() {
		return location;
	}
	
}
