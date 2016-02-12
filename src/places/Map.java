package places;

import java.util.*;

import engine.FileNames;
import engine.MainPanel;

import java.io.*;

import people.Hero;

/**
 * @author zephyr
 * A map is like a RoomFactory. Only tastier!
 */
public class Map  {
	
	private static Map instance;
	private LinkedList<Room> locations;
	private Room currLocation;

	private Map() {
		locations = new LinkedList<Room>();
		currLocation = null;
		Scanner scan = null;
		
		//Generation phase
		try {
			File dataFile = new File(FileNames.roomList);
			scan = new Scanner(dataFile);
			String line;
			String name;
			String desc;
			String itStrs;
			Room room = null;
			while (scan.hasNextLine()) {
				int ID = 1;
				line = scan.nextLine();
				StringTokenizer tok = new StringTokenizer(line, "\t");
				name = tok.nextToken();
				if (name.equals("#")) {
					continue;
				}
				desc = tok.nextToken();
				itStrs = tok.nextToken();
				String[] temp = itStrs.split(",");
				int[] items = new int[temp.length];
				for (int i = 0 ; i < temp.length ; i++) {
					items[i] = Integer.parseInt(temp[i]);
				}
				int[] links = new int[6];
				links[0] = Integer.parseInt(tok.nextToken());
				links[1] = Integer.parseInt(tok.nextToken());
				links[2] = Integer.parseInt(tok.nextToken());
				links[3] = Integer.parseInt(tok.nextToken());
				links[4] = Integer.parseInt(tok.nextToken());
				links[5] = Integer.parseInt(tok.nextToken());
				room = new Room(name, desc, items, links);
				locations.add(room);
				ID = ID + 1;				
			}
		} catch (FileNotFoundException e) {
			System.err.println("Room database is missing!");
			System.exit(1);
		} finally {
			scan.close();
		}
		
		//Linking phase
		for (Room r : locations) {
			int[] links = r.getLinks();
			if (links[0] != 0) {
				r.setLink(Direction.NORTH, locations.get(links[0]-1));
			}
			if (links[1] != 0) {
				r.setLink(Direction.SOUTH, locations.get(links[1]-1));
			}
			if (links[2] != 0) {
				r.setLink(Direction.EAST, locations.get(links[2]-1));
			}
			if (links[3] != 0) {
				r.setLink(Direction.WEST, locations.get(links[3]-1));
			}
			if (links[4] != 0) {
				r.setLink(Direction.UP, locations.get(links[4]-1));
			}
			if (links[5] != 0) {
				r.setLink(Direction.DOWN, locations.get(links[5]-1));
			}
		}
		
		currLocation = locations.getFirst();
	}
	
	/**
	 * @return
	 */
	public static Map instance() {
		if (instance == null) {
			instance = new Map();
		}
		return instance;
	}
	
	/**
	 * @param s
	 */
	private void print(String s) {
		MainPanel.instance().mainOut(s);
	}
	
	/**
	 * @param r
	 * @return
	 */
	public Room remove(Room r) {
		Room o = null;
		for (Room m : locations) {
			if (m.equals(r)) {
				o = m;
				locations.remove(m);
				return o;
			}
		}
		return null;
	}
	
	/**
	 * @return
	 */
	public Room getLocation() {
		return currLocation;
	}
	
	public Room getRoom(int id) {
		return locations.get(id);
	}
	
	/**
	 * @param dir
	 */
	public void move(Direction dir) {
		Room newLocation = currLocation.canMove(dir);
		if (newLocation != null) {
			Hero.instance().setLocation(newLocation);
			MainPanel.instance().newLocation(newLocation);
			currLocation = newLocation;
			if (currLocation.isVisited()) {
				print("Moved " + dir);
			} else {
				print(newLocation.verbose());
				currLocation.setVisited();
			}
		} else print("You can't go that way!");
	
	}

}
