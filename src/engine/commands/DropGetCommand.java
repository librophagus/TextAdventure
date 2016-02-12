package engine.commands;

import items.*;

import javax.swing.JList;

import engine.MainPanel;
import people.Hero;

public class DropGetCommand extends Command {
	
	JList<Item> itemTo;
	JList<Item> itemFrom;
	
	public DropGetCommand(JList<Item> iT, JList<Item> iF) {
		super();
		itemTo = iT;
		itemFrom = iF;
	}
	
	public void execute() {
		int index = itemTo.getSelectedIndex();
		if (index == -1) {
			if (itemTo.getModel() == Hero.instance().getInventory()) {
				MainPanel.instance().mainOut("Its already on the floor!");
			} else MainPanel.instance().mainOut("You already have it!");
			return;
		}
		Inventory ib = (Inventory) itemTo.getModel();
		Item trans = ib.remove(index);
		ib = (Inventory) itemFrom.getModel();
		ib.addElement(trans);
	}
}
