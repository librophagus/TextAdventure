package items;

import people.Being;

public class Food extends Item {

	public enum FoodType {
		MEAT,
		VEGGIE,
		FRUIT,
		GRAIN,
		DAIRY,
		CARRION;
	}

	private FoodType type;
	private int staminaRestore;
	private int hungerRestore;
	private int tempRestore;

	public Food(int id, String n, int v, int b, String d, FoodType t) {
		super(id, n, v, b, d);
		this.type = t;
	}

	public void useItem(Being p) {
		p.modSP(staminaRestore);
		//p.modHG(hungerRestore);
		//p.modTP(tempRestore);
	}

	@Override
	public boolean isEdible() {
		return true;
	}

	public FoodType getType() {
		return type;
	}

}
