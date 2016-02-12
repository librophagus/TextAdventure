package engine;

import items.*;
import places.*;
import people.*;

public class StoryTeller {
	
	Hero hero;
	Map map;
	//Room whiteHouse;
	//Room backofHouse;
	ItemFactory IF;
	
	public StoryTeller() {
		//Init
		map = Map.instance();
		
		IF = ItemFactory.instance();
		
		
		
		hero = Hero.instance();
		hero.give(IF.createItem(3));
		hero.give(IF.createItem(4));
		hero.give(IF.createItem(5));
		
	}
	
	public void start() {
		print(Map.instance().getLocation().verbose());
		Map.instance().getLocation().setVisited();
		
		//Full Stop
	}
	
	private void print(String s) {
		MainPanel.instance().mainOut(s);
	}
}
