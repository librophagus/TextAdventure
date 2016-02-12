package engine;

import items.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.*;

import engine.commands.*;

import java.util.*;

import places.Map;
import places.*;
import people.*;

/**
 * @author zephyr
 * The main view display for the game data.
 */
public class MainPanel extends JPanel {

	private static MainPanel instance;
	private static final long serialVersionUID = -5221186208437144932L;
	private JTextArea narratorView;
	private JScrollPane narratorScroll, invScroll, roomScroll;
	private JList<Item> invView, roomView;
	private Inventory invList, roomList;
	private String eol = System.getProperty("line.separator");
	private JPanel actionPanel, buttonPanel, statusPanel, storyActionPanel;
	private JSplitPane containerPanel, storyContainerPanel;
	private JTextField statusTime, statusCondition, statusMoney;
	private JButton stats;
	private JPanel moveButtons;
	private JButton look, north, south, east, west, up, down;
	private JPanel objectButtons;
	private JButton contact, stash, licenses, vehicle, jobs, map;
	private JPanel combatButtons;
	private JButton attack, charge, defend, item, talk, flee;
	private JButton flipMove, flipObject, flipCombat;
	private ButtonInputListener buttList;
	private MenuInputListener menList;
	private CardLayout buttPanCL;
	private JMenuItem dropM, getM, lookM, useM;
	private JPopupMenu itemPopup;
	private MouseListener popupListener;
	private ListSelectionModel invLsm, roomLsm;
	private TitledBorder narratorTitle;
	private JTextField userInput;

	/**
	 * Constructs a new MainPanel.
	 */
	private MainPanel() {
		setPreferredSize(new Dimension(750,450));
		setLayout(new BorderLayout());
		setFocusable(true);
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

		//Narrator and User Panels
		narratorView = new JTextArea();
		narratorView.setWrapStyleWord(true);
		narratorView.setLineWrap(true);
		narratorView.setEditable(false);
		narratorView.setRows(18);
		narratorScroll = new JScrollPane(narratorView, 
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		DefaultCaret caret = (DefaultCaret)narratorView.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		narratorTitle = BorderFactory.createTitledBorder("Location: " + Map.instance().getLocation().toString());
		narratorScroll.setBorder(narratorTitle);
		
		userInput = new JTextField();
		userInput.addActionListener(new TextInputListener());


		//Inventory and ground panels
		invList = Hero.instance().getInventory();
		invView = new JList<Item>(invList);
		invView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		invView.setLayoutOrientation(JList.VERTICAL);
		invView.setVisibleRowCount(-1);
		invView.setModel(invList);
		invScroll = new JScrollPane(invView,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		invScroll.setBorder(BorderFactory.createTitledBorder("In Inventory"));
		invLsm = invView.getSelectionModel();
		invLsm.addListSelectionListener(new InventorySelectionHandler());

		roomList = Map.instance().getLocation().getContents();
		roomView = new JList<Item>(roomList);
		roomView.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		roomView.setLayoutOrientation(JList.VERTICAL);
		roomView.setVisibleRowCount(-1);
		roomScroll = new JScrollPane(roomView,
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		roomScroll.setBorder(BorderFactory.createTitledBorder("In the Room"));
		roomLsm = roomView.getSelectionModel();
		roomLsm.addListSelectionListener(new InventorySelectionHandler());

		itemPopup = new JPopupMenu();
		dropM = new JMenuItem("Drop");
		itemPopup.add(dropM);
		getM = new JMenuItem("Get");
		itemPopup.add(getM);
		lookM = new JMenuItem("Look");
		itemPopup.add(lookM);
		useM = new JMenuItem("Use");
		itemPopup.add(useM);

		menList = new MenuInputListener();
		dropM.addActionListener(menList);
		getM.addActionListener(menList);
		lookM.addActionListener(menList);
		useM.addActionListener(menList);
		popupListener = new ItemPopupListener();
		roomView.addMouseListener(popupListener);
		invView.addMouseListener(popupListener);

		containerPanel = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				invScroll, roomScroll);
		containerPanel.setContinuousLayout(true);
		containerPanel.setResizeWeight(0.5);

		//Action and button panels
		actionPanel = new JPanel();
		actionPanel.setLayout(new BorderLayout());
		
//		statusPanel = new JPanel();
//		statusTime = new JTextField("Mid-Morning");
//		statusTime.setEditable(false);
//		statusTime.setFocusable(false);
//		statusTime.setBorder(BorderFactory.createTitledBorder("Time"));
//		statusPanel.add(statusTime);
//		statusPanel.add(Box.createVerticalGlue());
//		
//		statusMoney = new JTextField("100.0 T");
//		statusMoney.setEditable(false);
//		statusMoney.setFocusable(false);
//		statusMoney.setBorder(BorderFactory.createTitledBorder("Funds"));
//		statusPanel.add(statusMoney);
//		statusPanel.add(Box.createVerticalGlue());
//		
//		stats = new JButton("Status");
//		statusPanel.add(stats);
//		statusCondition = new JTextField("Doing Fine");
//		statusCondition.setEditable(false);
//		statusCondition.setFocusable(false);
//		statusCondition.setBorder(BorderFactory.createTitledBorder("Status"));
//		statusPanel.add(statusCondition);
//		statusPanel.add(Box.createVerticalGlue());
		
//		statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.Y_AXIS));
//		statusPanel.setPreferredSize(new Dimension(80,120));
//		actionPanel.add(statusPanel, BorderLayout.WEST);
		
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new CardLayout());
		
		objectButtons = new JPanel();
		objectButtons.setLayout(new GridBagLayout());
		GridBagConstraints cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		contact = new JButton("Contact");
		cons.gridx = 0;
		cons.gridy = 0;
		objectButtons.add(contact, cons);
		stash = new JButton("Stash");
		cons.gridx = 1;
		cons.gridy = 0;
		objectButtons.add(stash, cons);
		licenses = new JButton("Licenses");
		cons.gridx = 2;
		cons.gridy = 0;
		objectButtons.add(licenses, cons);
		vehicle = new JButton("Vehicle");
		cons.gridx = 0;
		cons.gridy = 1;
		objectButtons.add(vehicle, cons);
		jobs = new JButton("Jobs");
		cons.gridx = 1;
		cons.gridy = 1;
		objectButtons.add(jobs, cons);
		map = new JButton("Map");
		cons.gridx = 2;
		cons.gridy = 1;
		objectButtons.add(map, cons);
		cons.gridx = 2;
		cons.gridy = 2;
		flipObject = new JButton("Switch");
		objectButtons.add(flipObject, cons);
		objectButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttonPanel.add(objectButtons, "object");
		
		combatButtons = new JPanel();
		combatButtons.setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		attack = new JButton("Attack");
		cons.gridx = 1;
		cons.gridy = 0;
		combatButtons.add(attack, cons);
		charge = new JButton("Charge");
		cons.gridx = 0;
		cons.gridy = 1;
		combatButtons.add(charge, cons);
		defend = new JButton("Defend");
		cons.gridx = 1;
		cons.gridy = 1;
		combatButtons.add(defend, cons);
		item = new JButton("Item");
		cons.gridx = 2;
		cons.gridy = 1;
		combatButtons.add(item, cons);
		talk = new JButton("Talk");
		cons.gridx = 0;
		cons.gridy = 2;
		combatButtons.add(talk, cons);
		flee = new JButton("Flee");
		cons.gridx = 1;
		cons.gridy = 2;
		combatButtons.add(flee, cons);
		cons.gridx = 2;
		cons.gridy = 2;
		flipCombat = new JButton("Switch");
		combatButtons.add(flipCombat, cons);
		combatButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttonPanel.add(combatButtons, "combat");
		
		moveButtons = new JPanel();
		moveButtons.setLayout(new GridBagLayout());
		cons = new GridBagConstraints();
		cons.fill = GridBagConstraints.HORIZONTAL;
		look = new JButton("Look");
		cons.gridx = 0;
		cons.gridy = 0;
		moveButtons.add(look, cons);
		north = new JButton("NORTH");
		cons.gridx = 1;
		cons.gridy = 0;
		moveButtons.add(north, cons);
		south = new JButton("SOUTH");
		cons.gridx = 1;
		cons.gridy = 2;
		moveButtons.add(south, cons);
		up = new JButton("UP");
		cons.gridx = 2;
		cons.gridy = 0;
		moveButtons.add(up, cons);
		east = new JButton("EAST");
		cons.gridx = 2;
		cons.gridy = 1;
		moveButtons.add(east, cons);
		west = new JButton("WEST");
		cons.gridx = 0;
		cons.gridy = 1;
		moveButtons.add(west, cons);
		down = new JButton("DOWN");
		cons.gridx = 0;
		cons.gridy = 2;
		moveButtons.add(down, cons);
		cons.gridx = 2;
		cons.gridy = 2;
		flipMove = new JButton("Switch");
		moveButtons.add(flipMove, cons);
		moveButtons.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		buttonPanel.add(moveButtons, "move");
		
		actionPanel.add(buttonPanel, BorderLayout.CENTER);

		buttPanCL = (CardLayout)(buttonPanel.getLayout());
		buttPanCL.show(buttonPanel, "move");

		buttList = new ButtonInputListener();
		//stats.addActionListener(buttList);
		look.addActionListener(buttList);
		north.addActionListener(buttList);
		south.addActionListener(buttList);
		east.addActionListener(buttList);
		west.addActionListener(buttList);
		flipMove.addActionListener(buttList);
		
		contact.addActionListener(buttList);
		stash.addActionListener(buttList);
		licenses.addActionListener(buttList);
		vehicle.addActionListener(buttList);
		jobs.addActionListener(buttList);
		map.addActionListener(buttList);
		flipObject.addActionListener(buttList);
		
		attack.addActionListener(buttList);
		charge.addActionListener(buttList);
		defend.addActionListener(buttList);
		item.addActionListener(buttList);
		talk.addActionListener(buttList);
		flee.addActionListener(buttList);
		flipCombat.addActionListener(buttList);
		
		storyActionPanel = new JPanel();
		storyActionPanel.setLayout(new BorderLayout());
		storyActionPanel.add(narratorScroll, BorderLayout.NORTH);
		storyActionPanel.add(userInput, BorderLayout.CENTER);
		storyActionPanel.add(actionPanel, BorderLayout.SOUTH);
		
		storyContainerPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
				storyActionPanel, containerPanel);
		storyContainerPanel.setContinuousLayout(true);
		storyContainerPanel.setResizeWeight(0.75);
		add(storyContainerPanel);
		
		newLocation(Map.instance().getLocation());
	}

	public static MainPanel instance() {
		if (instance == null) {
			instance = new MainPanel();
		}
		return instance;
	}

	public void mainOut(String s) {
		narratorView.append(">>" + s + eol);
	}

	public void updateStatus(String which, String update) {
		switch (which) {
		case "time" : statusTime.setText("Time: " + update);
		break;
		case "status" : statusCondition.setText("Status: " + update);
		break;
		default : break;
		}
	}

	public JList<Item> getRoom() {
		return roomView;
	}

	public JList<Item> getInv() {
		return invView;
	}

	public void newLocation(Room r) {
		//TODO: Change color of button if visited is false?
		roomList = r.getContents();
		roomView.setModel(roomList);
		narratorTitle.setTitle("Location: " + r.toString());
		narratorScroll.repaint();
		if (r.canMove(Direction.NORTH) == null) {
			north.setEnabled(false);
		} else {
			north.setEnabled(true);
		}
		if (r.canMove(Direction.SOUTH) == null) {
			south.setEnabled(false);
		} else south.setEnabled(true);
		if (r.canMove(Direction.EAST) == null) {
			east.setEnabled(false);
		} else east.setEnabled(true);
		if (r.canMove(Direction.WEST) == null) {
			west.setEnabled(false);
		} else west.setEnabled(true);
		if (r.canMove(Direction.UP) == null) {
			up.setEnabled(false);
		} else up.setEnabled(true);
		if (r.canMove(Direction.DOWN) == null) {
			down.setEnabled(false);
		} else down.setEnabled(true);
	}

		private class TextInputListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent evt) {
				String input = userInput.getText();
				narratorView.append("<<" + input + eol);
				Parser.instance().process(input);
				userInput.setText("");
			}
		}

	private class ButtonInputListener implements ActionListener {

		private Hashtable<JButton,Command> keyMap;

		public ButtonInputListener() {
			keyMap = new Hashtable<JButton,Command>();
			//keyMap.put(stats, new DisplayCommand(Hero.instance().getStatus()));
			keyMap.put(look, new LookCommand());
			keyMap.put(north, new MoveCommand(Direction.NORTH));
			keyMap.put(south, new MoveCommand(Direction.SOUTH));
			keyMap.put(east, new MoveCommand(Direction.EAST));
			keyMap.put(west, new MoveCommand(Direction.WEST));
			keyMap.put(flipMove, new SwapCardCommand(buttPanCL, buttonPanel, "object"));
			
//			keyMap.put(contact, null);
//			keyMap.put(stash, null);
//			keyMap.put(licenses, null);
//			keyMap.put(vehicle, null);
//			keyMap.put(jobs, null);
//			keyMap.put(map, null);
			keyMap.put(flipObject, new SwapCardCommand(buttPanCL, buttonPanel, "combat"));
			
//			keyMap.put(attack, null);
//			keyMap.put(charge, null);
//			keyMap.put(defend, null);
//			keyMap.put(item, null);
//			keyMap.put(talk, null);
//			keyMap.put(flee, null);
			keyMap.put(flipCombat, new SwapCardCommand(buttPanCL, buttonPanel, "move"));
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			Command comm = keyMap.get(source);
			if (comm != null) {
				comm.execute();
			} else mainOut("Not implemented.");

		}

	}

	private class MenuInputListener implements ActionListener {

		private Hashtable<JMenuItem,Command> keyMap;

		public MenuInputListener() {
			keyMap = new Hashtable<JMenuItem,Command>();
			keyMap.put(dropM, new DropGetCommand(invView, roomView));
			keyMap.put(getM, new DropGetCommand(roomView, invView));
			keyMap.put(lookM, new ExamineCommand());
			keyMap.put(useM, new UseCommand());
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Object source = e.getSource();
			Command comm = keyMap.get(source);
			if (comm != null) {
				comm.execute();
			} else mainOut("Not implemented.");

		}

	}

	private class ItemPopupListener extends MouseAdapter {

		public void mousePressed(MouseEvent e) {
			maybeShowPopup(e);
		}

		public void mouseReleased(MouseEvent e) {
			maybeShowPopup(e);
		}

		private void maybeShowPopup(MouseEvent e) {
			if (e.getComponent() == invView) {
				if (e.isPopupTrigger()) {
					invView.setSelectedIndex(invView.locationToIndex(e.getPoint()));
					roomView.clearSelection();
					itemPopup.show(e.getComponent(),
							e.getX(), e.getY());
				}
				return;
			}
			if (e.getComponent() == roomView) {
				if (e.isPopupTrigger()) {
					roomView.setSelectedIndex(roomView.locationToIndex(e.getPoint()));
					invView.clearSelection();
					itemPopup.show(e.getComponent(),
							e.getX(), e.getY());
				}
				return;
			}
		}
	}

	private class InventorySelectionHandler implements ListSelectionListener {

		public void valueChanged(ListSelectionEvent e) {
			ListSelectionModel lsm = (ListSelectionModel) e.getSource();
			if (lsm.isSelectionEmpty()) {
				return;
			}
			if (lsm == roomLsm) {
				if (!e.getValueIsAdjusting()) {
					invView.clearSelection();
				}
				return;
			}
			if (lsm == invLsm) {
				if (!e.getValueIsAdjusting()) {
					roomView.clearSelection();
				}
				return;
			}
		}
	}

}
