package view;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
@SuppressWarnings("serial")
public class Menu extends JFrame {
	JButton btnNext = new JButton("Next");
	JButton btnOpen = new JButton("Open saved game");
	JLabel label = new JLabel("Choose at least 3 colors to play");
	ArrayList<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	String[] colorNames = {
			Model.PlayerColor.Blue.toString(), 
			Model.PlayerColor.Red.toString(),
			Model.PlayerColor.Orange.toString(),
			Model.PlayerColor.Gray.toString(),
			Model.PlayerColor.Purple.toString(),
			Model.PlayerColor.Yellow.toString(),};
	HashMap<Model.PlayerColor, String> playersName = new HashMap<Model.PlayerColor, String>();
	final private int windowSizeX = 450;
	final private int windowSizeY = 320;
	public Menu() {	
		setSize(windowSizeX, windowSizeY);
		setLayout(null);
		
		insertCheckBoxes();
		insertLabel();
		insertButtons();
		for(JCheckBox chkBox: checkBoxes) {
			chkBox.addItemListener(new chkStateChanged());
		}
		btnNext.addActionListener(new btnClicked());
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
	public class chkStateChanged implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			int count = 0;
			for(JCheckBox chkBox: checkBoxes) {
				if(chkBox.isSelected())
					count++;
			}
			if(count >= 3) btnNext.setEnabled(true);
			else btnNext.setEnabled(false);
		} 
	}
	public class btnClicked implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for(JCheckBox chkBox: checkBoxes) {
				if(chkBox.isSelected())
				{
					Model.PlayerColor pc = Model.PlayerColor.valueOf(chkBox.getText());
					String s;
					while(true)
					{
						s = (String)JOptionPane.showInputDialog("Name for player " + chkBox.getText());
						if (s == null || (s.length() == 0) || (s.length() > 8))
						{
							JOptionPane.showMessageDialog(null, "Name of player must be between 1 and 8 chars.");
						}
						else if(playersName.containsValue(s))
						{
							JOptionPane.showMessageDialog(null, "This name was already choosen.");
						}
						else break;
					}
					playersName.put(pc, s);	
				}
			}
			JOptionPane.showMessageDialog(null, playersName);
			dispose();
		} 
	}
}