package View;
import Model.GameException;
import Model.GameSettings;
import Model.GameState;
import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import Model.Player.Player;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Menu extends MyFrame {
	JButton btnNext = new JButton("Play");
	JButton btnOpen = new JButton("Open saved game");
	JLabel label = new JLabel("Choose at least 3 colors to play. Add a name for each one.");
	
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	ArrayList<JTextField> textFields = new ArrayList<JTextField>();
	
	String[] colorNames = {
			Model.PlayerColor.Blue.toString(),
			Model.PlayerColor.Red.toString(),
			Model.PlayerColor.Orange.toString(),
			Model.PlayerColor.Gray.toString(),
			Model.PlayerColor.Purple.toString(),
			Model.PlayerColor.Yellow.toString()
	};
	HashMap<Model.PlayerColor, String> playersName = new HashMap<Model.PlayerColor, String>();
	final private int windowSizeX = 450;
	final private int windowSizeY = 320;

	public Menu(ActionListener parent) {
		super(parent, ViewType.START_MENU);
		setSize(windowSizeX, windowSizeY);
		setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		insertCheckBoxes();
		insertLabels();
		insertButtons();
		insertTextFields();
		for(JCheckBox chkBox: checkBoxes) {
			chkBox.addItemListener(new chkStateChanged());
		}
		for(JTextField txt: textFields) {
			txt.getDocument().addDocumentListener(new textChanged());
		}
		PlayBtnListener playBtnListener = new PlayBtnListener(parent);
		btnNext.addActionListener(playBtnListener);
		
	}
	private void insertLabels() {
		label.setBounds(20, 20, 500, 30);
		getContentPane().add(label);
	}
	private void insertButtons() {
		btnNext.setBounds(windowSizeX - 100, windowSizeY - 80, 80, 30);
		btnNext.setEnabled(false);
		btnOpen.setBounds(20, windowSizeY - 80, 150, 30);

		getContentPane().add(btnNext);
		getContentPane().add(btnOpen);
	}
	private void insertCheckBoxes() {
		int color = 0, positionX, positionY;
		final int startX = 80;
		final int startY = 80;
		final int sizeX = 80;
		final int sizeY = 20;
		final int spacingX = 120;
		final int spacingY = 70;
		for(int y = 0; y < 2; y++) {
			positionY = startY + y * spacingY;
			for(int x = 0; x < 3; x++) {

				checkBoxes.add(new JCheckBox(colorNames[color]));
				positionX = startX + x * spacingX;
				checkBoxes.get(color).setBounds(positionX, positionY, sizeX, sizeY);
				getContentPane().add(checkBoxes.get(color));
				color++;
			}
		}
	}

	private void insertTextFields() {
		int positionX, positionY;
		final int startX = 80;
		final int startY = 110;
		final int sizeX = 70;
		final int sizeY = 20;
		final int spacingX = 120;
		final int spacingY = 70;
		for(int y = 0; y < 2; y++) {
			positionY = startY + y * spacingY;
			for(int x = 0; x < 3; x++) {
				JTextField txt = new JTextField();
				textFields.add(txt);
				positionX = startX + x * spacingX;
				txt.setBounds(positionX, positionY, sizeX, sizeY);
				txt.setEnabled(false);
				getContentPane().add(txt);
			}
		}
	}
	
	void validateTextCombo() {
		int count = 0;
		boolean textFieldsSizeOK = true;
		boolean textFieldsAllUnique = true;
		
		for(int i = 0; i < 6; i++) {
			if(checkBoxes.get(i).isSelected()){
				count++;
				textFields.get(i).setEnabled(true);
				String text = textFields.get(i).getText();
				
				if(text.length() == 0 || text.length() > 8) {
					textFieldsSizeOK = false;
				}
				else {
					for(int j = 0; j < 6; j++) {
						String text2 = textFields.get(j).getText();
						if(text2.equals(text) && i != j) {
							textFieldsAllUnique = false;	
							break;
						}
					}
				}
			}
			else {
				textFields.get(i).setEnabled(false);
			}
		}
	
		if(count >= 3 && textFieldsSizeOK && textFieldsAllUnique)
			btnNext.setEnabled(true);
		else
			btnNext.setEnabled(false);
	}
	public class textChanged implements DocumentListener {

		public void insertUpdate(DocumentEvent e) {
			validateTextCombo();
		}

		public void removeUpdate(DocumentEvent e) {
			validateTextCombo();
		}

		public void changedUpdate(DocumentEvent e) {
			validateTextCombo();
		}
		
	}
	public class chkStateChanged implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			validateTextCombo();
		}
	}

	public class PlayBtnListener extends JComponent implements ActionListener {
		private ActionListener _controller;

		public PlayBtnListener(ActionListener controller) {
			_controller = controller;
		}

		public void actionPerformed(ActionEvent e) {
			for(int i = 0; i < 6; i++) {
				if(checkBoxes.get(i).isSelected()){
					Model.PlayerColor pc = Model.PlayerColor.valueOf(checkBoxes.get(i).getText());
					String name = textFields.get(i).getText();
					playersName.put(pc, name);
				}
			}
			GameState gameState = GameState.getInstance();
			for(Map.Entry<Model.PlayerColor, String> player : playersName.entrySet())
			{
				try {
					gameState.addPlayer(player.getValue(), player.getKey());
				} catch (GameException e1) {
					e1.printStackTrace();
				}
			}
			_controller.actionPerformed(new ChangeViewEvent(this, 200, "", ViewType.GAME));
		}
	}
}
