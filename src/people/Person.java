package people;

import items.Food;
import items.Item;
import items.equip.Slot;

public class Person extends Being {

	//Money can go over 32,000
	private int money = 0;
	private short fame = 0;
	private byte countenance = 0;
	private byte virtue = 0; //Positive == good, negative == bad. 

	private Species species;
	private Nation nationality;
	private Season season;
	
	//Percent values, 0-100
	private byte morale = 50;
	
	private boolean fish = false;
	private boolean mine = false;
	private boolean gather = false;
	private boolean skin = false;
	
	private Slot[] equipment = new Slot[11];
	
	public Person(String name, boolean g, Species s, Nation n, Season o) {
		super(name, g);
		this.species = s;
		this.nationality = n;
		this.season = o;
		this.age = Species.getAverageAge(s);
		
		for (int i = 0 ; i < equipment.length ; i++) {
			equipment[i] = new Slot();
		}
	}
	
	/**
	 * @param i The item to eat
	 * @return If the Being was able to eat the item or not.
	 */
	public boolean eat(Food d) {
		Food.FoodType[] ft = Species.getDiet(this.species);
		for (Food.FoodType f : ft) {
			if (d.getType() == f) {
				return false;
			}
		}
		d.useItem(this);
		return true;
	}
	
	/**
	 * 0- Hat         5- Gloves 
	 * 1- Vest        6- Belt
	 * 2- Shirt       7- Pants
	 * 3- Left Wrist  8- Shoes
	 * 4- Right Wrist 9- Left Shoulder
	 * 10- Right Shoulder
	 * @param type Index of slot array
	 * @return The equipped item
	 */
	public Item getEquip(byte type) {
		return equipment[type].getItem();
	}
	
	/**
	 * 0- Hat         5- Gloves 
	 * 1- Vest        6- Belt
	 * 2- Shirt       7- Pants
	 * 3- Left Wrist  8- Shoes
	 * 4- Right Wrist 9- Left Shoulder
	 * 10- Right Shoulder
	 * @param i The item to equip
	 * @param type Index of slot array
	 * @return The previously equipped item
	 */
	public Item setEquip(Item i, byte type) {
		Item r = equipment[type].getItem();
		equipment[type].setItem(i);
		return r;
	}
	
}
