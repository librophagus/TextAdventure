package engine.commands;

import java.awt.CardLayout;
import javax.swing.JPanel;

public class SwapCardCommand extends Command {
	
	CardLayout cl;
	JPanel root;
	String panel;
	
	public SwapCardCommand(CardLayout cl, JPanel panel, String which) {
		super();
		this.cl = cl;
		root = panel;
		this.panel = which;
	}
	
	public SwapCardCommand(CardLayout cl, JPanel panel) {
		super();
		this.cl = cl;
		root = panel;
		this.panel = null;
	}

	@Override
	public void execute() {
		if (panel == null) {
			cl.next(root);
			return;
		}
		cl.show(root, panel);
	}

}
