package items;

import java.util.*;

import engine.FileNames;

import java.io.*;

/**
 * @author zephyr
 * 0- General Item, 1- Tool, 2- Weapon, 
 * 3- Transformable item (0=Look|1=Use,ID) 
 */
public class ItemFactory {

	private static ItemFactory instance;
	private HashMap<Integer,Item> database;
	private int ID = 1;
	
	private void ItemFactory2() {
		database = new HashMap<Integer,Item>();
		Scanner scan = null;
		try {
			File dataFile = new File(FileNames.itemList);
			scan = new Scanner(dataFile);
			String line;
			int type;
			Item item = null;
			while (scan.hasNextLine()) {
				line = scan.nextLine();
				StringTokenizer tok = new StringTokenizer(line, "\t");
				try {
					type = Integer.parseInt(tok.nextToken());
				} catch (NumberFormatException e) {
					//Line is a comment and not an item.
					continue;
				}
				switch (type) {
				case 0: { //General Item
					item = generateItem(tok); 
					break;
				}
				case 1: { //Tool
					break;
				}
				case 2: { //Weapon
					break;
				}
				case 3: { //Transformable item
					item = generateTransformer(tok); 
					break;
				}
				case 4: { //Edible item
					item = generateFood(tok); 
					break;
				}
				default: {
					System.err.println("Malformed item entry on line " + ID);
					System.exit(1);
				}
				}
				if (item != null) {
					database.put(ID, item);
					ID++;
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Item database is missing!");
			System.exit(1);
		} finally {
			scan.close();
		}
	}

	private ItemFactory() {
		//database.put(id++, new Item(ID, name, value, weight, desc));
		int id = 0;
		database = new HashMap<Integer,Item>();
		
		database.put(id, new Item(id++, "Ice", 1, 1, "A piece of cold ice"));
		database.put(id, new Food(id++, "Mushroom", 1, 1, "A slimy mushroom", Food.FoodType.VEGGIE));
		database.put(id, new Item(id++, "Walkie-Talkie", 1, 1, "A walkie-talkie with no batteries"));
		database.put(id, new Food(id++, "Pickle", 1, 1, "A salty pickle", Food.FoodType.VEGGIE));
		database.put(id, new TransformableItem(id++, "Bell", 1, 1, "A tarnished little bell. Polishing it with your sleeve reveals a silvery shine", 0, 6));
		database.put(id, new Item(id++, "Silver Bell", 1, 1, "A silvery little bell. Strange markings ring the bottom of it."));
		database.put(id, new Food(id++, "Chili", 1, 1, "With an I.", Food.FoodType.VEGGIE));
		database.put(id, new Item(id++, "Sword of Scripting", 1, 1, "A magical sword of scriptingness."));
		
	}
	
	private Item generateItem(StringTokenizer tok) {
		Item item = null;
		String name = tok.nextToken();
		int value = Integer.parseInt(tok.nextToken());
		int weight = Integer.parseInt(tok.nextToken());
		String desc = tok.nextToken();
		item = new Item(ID, name, value, weight, desc);
		return item;
	}
	
	private Item generateTool(StringTokenizer tok) {
		Item item = null;
		return item;
	}
	
	private Item generateWeapon(StringTokenizer tok) {
		Item item = null;
		return item;
	}
	
	private Item generateTransformer(StringTokenizer tok) {
		TransformableItem item = null;
		String name = tok.nextToken();
		int value = Integer.parseInt(tok.nextToken());
		int weight = Integer.parseInt(tok.nextToken());
		String desc = tok.nextToken();
		int type = Integer.parseInt(tok.nextToken());
		int result = Integer.parseInt(tok.nextToken());
		item = new TransformableItem(ID, name, value, weight, 
				desc, type, result);
		return item;
	}
	
	private Item generateFood(StringTokenizer tok) {
		Food item = null;
		String name = tok.nextToken();
		int value = Integer.parseInt(tok.nextToken());
		int weight = Integer.parseInt(tok.nextToken());
		String desc = tok.nextToken();
		Food.FoodType type = Food.FoodType.valueOf(tok.nextToken());
		item = new Food(ID, name, value, weight, 
				desc, type);
		return item;
	}
	
	public static ItemFactory instance() {
		if (instance == null) {
			instance = new ItemFactory();
		}
		return instance;
	}
	
	/**
	 * Returns a copy of an Item based on ID.
	 * Allows for unique and uncopyable items
	 * to be made by singleton-ing them on an
	 * individual basis.
	 * ID, Name, Value, Weight, Verbose
	 * @param ID The Item's ID
	 * @return The corresponding Item
	 */
	public Item createItem(int ID) {
		return database.get(ID);
	}

	/**
	 * @param name The String to be looked up.
	 * @return The ID of the item needed.
	 */
	public int lookupItem(String name) {
		switch (name.toLowerCase()) {
		case "ice" : return 0;
		case "mushroom" : return 1;
		case "walkie-talkie" : return 2;
		case "pickle" : return 3;
		case "bell" : return 4;
		case "chili" : return 5;
		case "sword of scripting" : return 6;
		default : return -1;
		}
	}
	
	
}
