package engine.commands;

import items.*;
import places.*;
import people.*;

public abstract class Command {
	
	protected ItemFactory IF;
	protected Hero hero;
	protected Map map;
	
	public Command() {
		IF = ItemFactory.instance();
		hero = Hero.instance();
		map = Map.instance();
	}

	public abstract void execute();
	
}
