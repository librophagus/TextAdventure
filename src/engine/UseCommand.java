package engine;

import javax.swing.JList;

import engine.commands.Command;
import items.*;
import people.Hero;

public class UseCommand extends Command {

	public UseCommand() {
		super();
	}
	
	@Override
	public void execute() {
		JList<Item> invView = MainPanel.instance().getInv();
		JList<Item> roomView = MainPanel.instance().getRoom();
		
		if (invView.getSelectedIndex() != -1) {
			int index = invView.getSelectedIndex();
			Inventory invList = (Inventory) invView.getModel();
			Item i = invList.getElementAt(index);
			if (i instanceof Food) {
				if (Hero.instance().eat(i)) {
					MainPanel.instance().mainOut("You ate the " + i.toString() + ". It was delicious.");
					invList.remove(index);
				} else {
					MainPanel.instance().mainOut("You can't eat " + i.toString() + "!");
				}
				
			}
			return;
		}
		if (roomView.getSelectedIndex() != -1) {
			int index = roomView.getSelectedIndex();
			Inventory roomList = (Inventory) roomView.getModel();
			MainPanel.instance().mainOut("You should probably pick up the " +
					roomList.getElementAt(index).toString() + " before eating it.");
			return;
		}
		
		// TODO Auto-generated method stub

	}

}
