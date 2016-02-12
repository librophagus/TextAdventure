package places;

/**
 * @author zephyr
 * Enum representing the various directions that the hero
 * can move in.
 */
public enum Direction {
	NORTH,
	SOUTH,
	EAST,
	WEST,
	UP,
	DOWN,
	NORTHEAST,
	NORTHWEST,
	SOUTHEAST,
	SOUTHWEST;
	
	/**
	 * Converts a String literal to a Direction object.
	 * @param s The String to convert
	 * @return The corresponding Direction.
	 */
	public static Direction stringtoDir(String s) {
		switch (s.toLowerCase()) {
			case "north" : return NORTH;
			case "south" : return SOUTH;
			case "east" : return EAST;
			case "west" : return WEST;
			case "up" : return UP;
			case "down" : return DOWN;
			case "northeast" : return NORTHEAST;
			case "northwest" : return NORTHWEST;
			case "southeast" : return SOUTHEAST;
			case "southwest" : return SOUTHWEST;
			default : return null;
		}
	}
}
