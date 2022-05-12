package view;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
@SuppressWarnings("serial")
public class Menu extends JFrame {
	JButton btnNext = new JButton("Next");
	JButton btnOpen = new JButton("Open saved game");
	JLabel label = new JLabel("Choose at least 3 colors to play");
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	String[] colorNames = {"Red", "Green", "Blue", "Gray", "Purple", "Yellow"};
	final private int windowSizeX = 450;
	final private int windowSizeY = 320;
	public Menu() {	
		setSize(windowSizeX, windowSizeY);
		setLayout(null);
		
		insertCheckBoxes();
		insertLabel();
		insertButtons();
	}
	private void insertLabel() {
		label.setBounds(20, 40, 500, 30);
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
		final int sizeX = 120;
		final int sizeY = 70;
		for(int y = 0; y < 2; y++) {
			positionY = startY + y * sizeY;
			for(int x = 0; x < 3; x++) {
				checkBoxes.add(new JCheckBox(colorNames[color]));
				positionX = startX + x * sizeX;
				checkBoxes.get(color).setBounds(positionX, positionY, sizeX, sizeY);
				getContentPane().add(checkBoxes.get(color));
				color++;
			}
		}
	}
	public static void main(String[] args) {
		Menu f=new Menu();
		f.setTitle("Menu");
		f.setVisible(true);
	}
}