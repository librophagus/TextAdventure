package items;

/**
 * @author zephyr
 * The eight elements making up the world plus two
 * universal meta-elements Void and Star.
 */
public enum Element {
	HEAT,
	LIGHT,
	LAND,
	METAL,
	WATER,
	DARK,
	AIR,
	WOOD,
	VOID,
	STAR;
	
	/**
	 * Converts a String literal to an Element object.
	 * @param s The String to convert
	 * @return The matching Element
	 */
	public static Element stringToElem(String s) {
		switch (s.toLowerCase()) {
		case "heat" : return HEAT;
		case "light" : return LIGHT;
		case "land" : return LAND;
		case "metal" : return METAL;
		case "water" : return WATER;
		case "dark" : return DARK;
		case "air" : return AIR;
		case "wood" : return WOOD;
		case "void" : return VOID;
		case "star" : return STAR;
		default: return null;
		}
	}
}
