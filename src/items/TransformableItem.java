package items;

public class TransformableItem extends Item {
	
	int transType;
	int transTo;

	public TransformableItem(int id, String name, int value, int weight,
			String desc, int type, int result) {
		super(id, name, value, weight, desc);
		this.transType = type;
		this.transTo = result;
	}
	
	public boolean transformOnLook() {
		if (transType == 0) {
			return true;
		} else return false;
	}
	
	public boolean transformOnUse() {
		if (transType == 1) {
			return true;
		} else return false;
	}
	
	public Item getResult() {
		Item tmp = ItemFactory.instance().createItem(transTo);
		return tmp;
	}

}
