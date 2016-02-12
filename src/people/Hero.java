package people;

import items.*;
import places.Map;

public class Hero extends Person {

	private static Hero instance;
	
	private Hero(String name, boolean g, Species s, Nation n, Season o) {
		super(name, g, s, n, o);
		inventory = new Inventory(this);
		location = Map.instance().getLocation();
	}
	
	public static Hero instance() {
		if (instance == null) {
			instance = new Hero("YOU", false, Species.MANNAM, Nation.ALCABAST, Season.AUTUMN);
		}
		return instance;
	}
	
	public void setName(String n) {
		this.name = n;
	}

	public String getStatus() {
		return "You are feeling fine. You are carrying " + inventory.getSize() + " things.";
	}

}
