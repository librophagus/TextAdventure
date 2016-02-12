package engine.commands;

import items.*;

import javax.swing.JList;

import engine.MainPanel;

public class ExamineCommand extends Command {

	public ExamineCommand() {
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
			MainPanel.instance().mainOut(i.verbose() + 
					" You are currently holding it.");
			if (i instanceof TransformableItem) {
				Item i2 = ((TransformableItem) i).getResult();
				invList.addElement(i2);
				invList.remove(index);
			}
			return;
		}
		if (roomView.getSelectedIndex() != -1) {
			int index = roomView.getSelectedIndex();
			Inventory roomList = (Inventory) roomView.getModel();
			MainPanel.instance().mainOut(roomList.getElementAt(index).verbose() + 
					" It is in the room with you.");
			return;
		}


	}

}
