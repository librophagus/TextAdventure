package items;

public enum ItemState {
	LIQUID,
	GAS,
	POWDER,
	CHUNK,
	FOLDABLE,
	SMALL;
	
	public static int getNumStates() {
		return 6;
	}
}
