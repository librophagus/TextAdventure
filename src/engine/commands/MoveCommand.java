package engine.commands;

import places.Direction;

public class MoveCommand extends Command {

	Direction go;
	
	public MoveCommand(Direction dir) {
		super();
		go = dir;
	}

	@Override
	public void execute() {
		map.move(go);
	}

}
