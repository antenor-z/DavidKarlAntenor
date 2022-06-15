package View;

import Model.Event.ViewType;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import Controller.OpenBtnCtl;
import Controller.PlayBtnCtl;
import Controller.TextChangedCtl;
import Controller.chkBoxChangedCtl;

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
		setTitle("Monopoly - Menu");
		insertCheckBoxes();
		insertLabels();
		insertButtons();
		insertTextFields();
		for(JCheckBox chkBox: checkBoxes) {
			chkBox.addItemListener(new chkBoxChangedCtl(checkBoxes, textFields, btnNext));
		}
		for(JTextField txt: textFields) {
			txt.getDocument().addDocumentListener(new TextChangedCtl(checkBoxes, textFields, btnNext));
		}
		PlayBtnCtl playBtnListener = new PlayBtnCtl(parent, checkBoxes, textFields);
		btnNext.addActionListener(playBtnListener);
		
		OpenBtnCtl openBtnListener = new OpenBtnCtl(parent);
		btnOpen.addActionListener(openBtnListener);
		
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
	
	
}
