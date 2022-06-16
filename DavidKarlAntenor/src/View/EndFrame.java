package View;
import Model.GameState;
import Model.Event.ViewType;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;


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
		setTitle("Monopoly - End of Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width)/2 - getWidth()/2, (Toolkit.getDefaultToolkit().getScreenSize().height)/2 - getHeight()/2);
		getContentPane().add(new p());
				
	}
	public class p extends JPanel {
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			Graphics2D g2d = (Graphics2D)g;
			g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setFont(new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 25));
			ArrayList<String> s = GameState.getInstance().getEndText();
			int posY = 40;
			int posX = windowSizeX / 4;
			for(String line: s)
			{
				g2d.drawString(line, posX, posY);
				posY += 40;
			}
		}
	}
}
