package places;

import java.util.LinkedList;

public class Ruin {

	private Room entrance;
	private Room[][] nodes;
	private int size;
	
	public Ruin(Room e, Room n, int s) {
		this.entrance = e;
		this.size = s;
		nodes = new Room[s][s];
		
		for (int i = 0 ; i < size ; i++) {
			for (int j = 0 ; j < size ; j++) {
				//Room 
				//nodes[i][j] = 
			}
		}
	}

}
