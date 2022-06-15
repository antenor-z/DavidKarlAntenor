package View;
import Model.GameException;
import Model.GameSettings;
import Model.GameState;
import Model.Player;
import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import Controller.OpenBtnCtl;
import Controller.PlayBtnCtl;
import Controller.TextChangedCtl;
import Controller.chkBoxChangedCtl;

@SuppressWarnings("serial")
public class EndFrame extends MyFrame {
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

	public EndFrame(ActionListener parent) {
		super(parent, ViewType.END);
		setSize(windowSizeX, windowSizeY);
		//setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		getContentPane().add(new p());
				
	}
	public class p extends JPanel {
		public void paintComponent(Graphics g) {
		super.paintComponent(g);
			g.drawString("End of game", 120, 10);
		}
	}
}
