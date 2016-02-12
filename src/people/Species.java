package people;

import java.util.ArrayList;
import items.Food.FoodType;

public enum Species {
	MANNAM,
	JUSHAVA,
	PRATAVA,
	VELIAGOS,
	LEHVOKI,
	STRIGIDER,
	GYSERADA,
	TERIAREUCH,
	CERVINAEUS;
	
	/**
	 * @param s The species to check
	 * @return The average age of a Hero of that
	 * species. Returns -1 upon failure.
	 */
	public static short getAverageAge(Species s) {
		switch (s) {
		case MANNAM: 
			return 20;
		case JUSHAVA:
			return 40;
		case PRATAVA:
			return 50;
		case VELIAGOS:
			return 18;
		case LEHVOKI:
			return 19;
		case STRIGIDER:
			return 21;
		case GYSERADA:
			return 24;
		case TERIAREUCH:
			return 50;
		case CERVINAEUS:
			return 25;
		default:
			return -1;
		}
	}
	
	/**
	 * @param s
	 * @return The types of food that the Species CANNOT eat
	 */
	public static FoodType[] getDiet(Species s) {
		FoodType[] c = {FoodType.CARRION};
		switch (s) {
		case MANNAM: 
			return c;
		case JUSHAVA:
			return c;
		case PRATAVA:
			return c;
		case VELIAGOS:
			FoodType[] a = {FoodType.FRUIT, FoodType.GRAIN, FoodType.VEGGIE, FoodType.CARRION};
			return a;
		case LEHVOKI:
			FoodType[] b = {FoodType.MEAT, FoodType.CARRION};
			return b;
		case STRIGIDER:
			FoodType[] d = {FoodType.DAIRY, FoodType.CARRION};
			return d;
		case GYSERADA:
			FoodType[] e = {FoodType.FRUIT, FoodType.GRAIN, FoodType.VEGGIE};
			return e;
		case TERIAREUCH:
			return c;
		case CERVINAEUS:
			FoodType[] f = {FoodType.MEAT, FoodType.CARRION};
			return f;
		default:
			return null;
		}
	}

}
