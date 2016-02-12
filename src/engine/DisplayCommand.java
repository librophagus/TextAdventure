package engine;

import engine.commands.Command;

public class DisplayCommand extends Command {

	private String display;
	
	public DisplayCommand(String s) {
		display = s;
	}
	
	@Override
	public void execute() {
		MainPanel.instance().mainOut(display);
	}

}
