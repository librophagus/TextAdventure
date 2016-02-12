package places;

import items.*;

import java.util.LinkedList;

import engine.MainPanel;

public class Room {
	public String name;
	private Room north = null;
	private Room south = null;
	private Room east = null;
	private Room west = null;
	private Room up = null;
	private Room down = null;
	private boolean visited = false;
	private int[] links;
	private Inventory things;
	private String description;
	
	/**
	 * @param name The name of the room.
	 * @param desc The verbose description of the room.
	 * @param items The item id's that will be in the room.
	 * @param links The room id's that this room links to.
	 */
	public Room(String name, String desc, int[] items, int[] links) {
		this.name = name;
		this.description = desc;
		things = new Inventory(items, this);
		this.links = links;
	}
	
	@SuppressWarnings("unused")
	private void print(String s) {
		MainPanel.instance().mainOut(s);
	}
	
	public void give(Item i) {
		things.addElement(i);
	}
	
	public boolean pickup(Item i) {
		return things.removeElement(i);
	}
	
	public Inventory getContents() {
		return things;
	}
	
	public Room canMove(Direction dir) {
		switch (dir) {
		case NORTH : {
			return north;
		}
		case SOUTH : {
			return south;
		}
		case EAST : {
			return east;
		}
		case UP : {
			return up;
		}
		case DOWN : {
			return down;
		}
		default : return null;
		}
	}
	
	public String verbose() {
		return description;
	}
	
	public LinkedList<Direction> exits() {
		LinkedList<Direction> exits = new LinkedList<Direction>();
		if (north != null) {
			exits.add(Direction.NORTH);
		}
		if (south != null) {
			exits.add(Direction.SOUTH);
		}
		if (east != null) {
			exits.add(Direction.EAST);
		}
		if (west != null) {
			exits.add(Direction.WEST);
		}
		if (up != null) {
			exits.add(Direction.UP);
		}
		if (down != null) {
			exits.add(Direction.DOWN);
		}
		return exits;
	}
	
	public void setLink(Direction dir, Room rm) {
		switch (dir) {
		case NORTH : {
			north = rm;
			break;
		}
		case SOUTH : {
			south = rm;
			break;
		}
		case EAST : {
			east = rm;
			break;
		}
		case WEST : {
			west = rm;
			break;
		}
		default : return;
		}
	}
	
	public int[] getLinks() {
		return this.links;
	}
	
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited() {
		this.visited = true;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
