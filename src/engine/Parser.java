package engine;

import items.*;

import java.util.*;

import people.Hero;
import places.Map;
import places.*;

public class Parser {
	private String input;
	private StringTokenizer tok;
	private static Parser instance;

	private Parser() {
		input = "";
		tok = new StringTokenizer(input);
	}

	public static Parser instance() {
		if (instance == null) {
			instance = new Parser();
		}
		return instance;
	}

	private void print(String s) {
		MainPanel.instance().mainOut(s);
	}

	public void process(String input) {
		if (input == null || input.equals("")) {
			print("Sorry, I didn't catch that?");
			return;
		}
		tok = new StringTokenizer(input);
		String command = tok.nextToken();

		switch (command) {
		case "get" : processGet(); break;
		case "drop" : processDrop(); break;
		case "move" : processMove(null); break;
		case "look" : processLook(); break;
		case "north" : processMove(Direction.NORTH); break;
		case "south" : processMove(Direction.SOUTH); break;
		case "east" : processMove(Direction.EAST); break;
		case "west" : processMove(Direction.WEST); break;
		case "dupe" : processDupe(); break;
		//case "eat" : processEat(); break;
		//			case "say" : processSay(); break;
		//			case "derp" : processEmote("derp"); break;
		//			case "examine" : processExamine(); break;
		//			case "attack" : processCombat(); break;
		default : print("Sorry, I don't understand that.");
		break;
		}
	}
	
//	private void processEat() {
//		if (tok.hasMoreTokens()) {
//			String thingName = tok.nextToken();
//			while (tok.hasMoreTokens()) {
//				thingName += " ";
//				thingName += tok.nextToken();
//			}
//			
//			//Determine ID of item asked for
//			Item i = Hero.instance().owns(thingName);
//			
//			if (i == null) {
//				print("You don't have any of that.");
//				return;
//			}
//			if (i.isEdible()) {
//				Hero.instance().eat(i);
//				Hero.instance().lose(i);
//				return;
//			} else print("That's not edible!");
//			
//			return;
//		} else print("Eat what?");
//		
//	}

	private void processDupe() {
		if (tok.hasMoreTokens()) {
			String thingName = tok.nextToken();
			while (tok.hasMoreTokens()) {
				thingName += " ";
				thingName += tok.nextToken();
			}
			Hero.instance().give(
					ItemFactory.instance().createItem(
							ItemFactory.instance().lookupItem(thingName)));
			if (ItemFactory.instance().lookupItem(thingName) > -1) {
				print("Here is another " + thingName + ". Cheater.");
			} else print("That isn't even a real thing! "
					+ "If you're going to cheat, at least do it correctly.");
			return;
		} else print("Dupe what?");
		
	}

	private void processLook() {
		if (tok.hasMoreTokens()) {
			print("Sorry, I don't understand that.");
			return;
		}
		Room rm = Map.instance().getLocation();
		print(rm.verbose());
		String outs = "";
		Iterator<Direction> iter = rm.exits().iterator();
		while (iter.hasNext()) {
			outs += iter.next().toString();
			if (iter.hasNext()) {
				outs += " ,";
			}
		}
		print("You can move: " + outs);
	}

	private void processMove(Direction dir) {
		if (dir == null) {
			dir = Direction.stringtoDir(tok.nextToken());
		}
		if (tok.hasMoreTokens() || dir == null) {
			print("Sorry, that wasn't a direction.");
			return;
		}
		Map.instance().move(dir);
	}

	private void processGet() {
		Inventory room = Map.instance().getLocation().getContents();
		Inventory heroInv = Hero.instance().getInventory();

		if (tok.hasMoreTokens()) {
			String thingName = tok.nextToken();
			
				//Pick up all items
			if (thingName.equals("all")) {
				if (room.isEmpty()) {
					print("There is nothing here!");
					return;
				}
				int size = room.getSize();
				heroInv.transferItems(room);
				print("Picked up " + size + " things.");
				return;
			}
				//Determine name of item asked for
			while (tok.hasMoreTokens()) {
				thingName += " ";
				thingName += tok.nextToken();
			}
				//Determine ID of item asked for
			int ID = ItemFactory.instance().lookupItem(thingName);
			if (heroInv.containsID(ID)) {
				print("You already have it!");
				return;
			}
				//Determine if item exists in room.
			Item get = room.removeID(ID);
			if (get != null) {
				heroInv.addElement(get);
				print("Picked up " + get.toString() + ".");
				return;
			} else print("No such thing here!");
			return;
		} else print("Get what?");
	}

	
	private void processDrop() {
		Inventory room = Map.instance().getLocation().getContents();
		Inventory heroInv = Hero.instance().getInventory();

		if (tok.hasMoreTokens()) {
			String thingName = tok.nextToken();
			
				//Drop all items
			if (thingName.equals("all")) {
				if (heroInv.isEmpty()) {
					print("You aren't carrying anything!");
					return;
				}
				int size = heroInv.getSize();
				room.transferItems(heroInv);
				print("Dropped " + size + " things.");
				return;
			}
				//Determine name of item asked for
			while (tok.hasMoreTokens()) {
				thingName += " ";
				thingName += tok.nextToken();
			}
				//Determine ID of item asked for
			int ID = ItemFactory.instance().lookupItem(thingName);
			if (room.containsID(ID)) {
				print("You already dropped it!");
				return;
			}
				//Determine if item exists in inventory.
			Item drop = heroInv.removeID(ID);
			if (drop != null) {
				room.addElement(drop);
				print("Dropped " + drop.toString() + ".");
				return;
			} else print("You don't have any of that!");
			return;
		} else print("Drop what?");
	}

}

