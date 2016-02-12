package engine;
import java.awt.Panel;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import people.*;
import places.*;
import guiLexi.*;

public class Driver {

	//private static MainPanel mPanel;
	
	public static void main(String[] args) {
//		KeyMap kmap = KeyMap.instance();
//		Window window = Window.instance();
//		Glyph top = new Column();
//		window.setTop(top);
//		
//		top.insert(new Row("Hello World!", window));
//		
//		System.out.println("Hello World!");
//		
//		window.setContents();
		
		Being hero = Hero.instance();
		Map map = Map.instance();
		StoryTeller story = new StoryTeller();
		JFrame frame = new JFrame("The World At Your Will");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MainPanel mPanel = MainPanel.instance();
		frame.getContentPane().add(mPanel);
		frame.pack();
		frame.setVisible(true);
		
		story.start();
	}
}

