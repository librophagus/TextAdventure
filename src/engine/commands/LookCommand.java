package engine.commands;

import engine.MainPanel;

public class LookCommand extends Command {

	public LookCommand() {
		super();
	}

	@Override
	public void execute() {
		MainPanel.instance().mainOut(map.getLocation().verbose());
	}

}
