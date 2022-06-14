package View.Gameboard;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import Model.GameSettings;
import Model.Observed;
import Model.Player;
import Model.PlayerColor;
import View.MyPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GameControlPanel extends MyPanel implements Model.Observer {
    GameSettings settings = GameSettings.getInstance();

    JButton btnThrowDice = new JButton("Throw dice");
    JButton pauseButton = new JButton("Pause");
    JButton quitButton = new JButton("Quit");
    // These buttons change name depending of the current player
    // Action 1 may be, for example, buy house or buy company
    public JButton action1 = new JButton("Action 1");
    public JButton action2 = new JButton("Action 2");
    
    public JButton saveBtn = new JButton("Save");

    public JComboBox<String> dice1Selection = new JComboBox<String>();
    public JComboBox<String> dice2Selection = new JComboBox<String>();
    Model.GameState gameState = Model.GameState.getInstance();

    public int dice1Value = 0;
	public int dice2Value = 0;
	String cardImgPath = "";

    public GameControlPanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initPanel();
        _setEvents();
        dice1Selection.addItem("Random");
        dice2Selection.addItem("Random");
        for(int i = 1; i <= 6; i++)
        {
        	dice1Selection.addItem(String.valueOf(i));
        	dice2Selection.addItem(String.valueOf(i));
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
    	Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    	    RenderingHints.VALUE_ANTIALIAS_ON);

        super.paintComponent(g);
        _drawDices(g);
        _drawCard(g);
        _drawPlayersStatus(g);
        _drawTileInfo(g);
    }

    private void _initPanel() {
    	setLayout(null);
        setPreferredSize(new Dimension(500, 700));

        btnThrowDice.setBounds( 20, 10, 110, 30);  add(btnThrowDice);
        pauseButton.setBounds (150, 10, 110, 30); add(pauseButton);
        quitButton.setBounds  (280, 10, 110, 30); add(quitButton);
        action1.setBounds     ( 20, 50, 110, 30); add(action1);
        action2.setBounds     (150, 50, 110, 30); add(action2);
        saveBtn.setBounds     (280, 50, 110, 30); add(saveBtn);
  
        dice1Selection.setBounds( 20, 220, 80, 30); add(dice1Selection);
        dice2Selection.setBounds(150, 220, 80, 30); add(dice2Selection);

        dice1Selection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int sel = dice1Selection.getSelectedIndex();
				if(sel == 0) gameState.dices[0] = -1;
				else gameState.setDice1Preset(sel);
			}
        });
        dice2Selection.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int sel = dice2Selection.getSelectedIndex();
				if(sel == 0) gameState.dices[1] = -1;
				else gameState.setDice2Preset(sel);
			}
        });
        
        saveBtn.addActionListener(new ActionListener()
        {
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser jFileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON file", "json");
				jFileChooser.setAcceptAllFileFilterUsed(false);
				jFileChooser.addChoosableFileFilter(filter);
				int option = jFileChooser.showSaveDialog(null);
				if(option == JFileChooser.APPROVE_OPTION)
				{
					String selectedFile = jFileChooser.getSelectedFile().getAbsolutePath();
					gameState.saveGame(selectedFile);
				}
			}
        });

        action1.setVisible(false);
        action2.setVisible(false);
    }

    private void _drawCard(Graphics g) {
        if (cardImgPath == "") return;
        Image i = null;
        try {
            i=ImageIO.read(new File(cardImgPath));
        }
        catch(IOException e2) {
            System.out.println(e2.getMessage());
            System.exit(1);
        }
        g.drawImage(i, 0, 400, null);
    }
    private Color toJavaColor (PlayerColor c) {
    	switch (c) {
			case Blue: {return Color.blue;}
			case Red: {return Color.red;}
			case Orange: {return new Color(0xEB, 0x9A, 0x21);}
			case Yellow: {return Color.yellow;}
			case Gray: {return Color.gray;}
			case Purple: {return Color.pink;}
			default: {return Color.black;}
    	}
    }
    private void _drawPlayersStatus(Graphics g) {
    	g.setFont(new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 15));
    	int positionY = 120;
    	int positionX = 280;

    	for(Player p: gameState.players) {
    		// drawString doesn't handle \n so we have to do manually
    		if(p.isBankrupt())
    		{
    			g.drawLine(positionX, positionY - 20, positionX+80, positionY+60);
    		}
    		if(p == gameState.turn)
    		{
    			g.setColor(toJavaColor(gameState.turn.getColor()));
    			g.setFont(new Font(g.getFont().getFamily(), Font.ITALIC, 15));
    			g.drawString("Name: " + p.getName() + " [Turn]" , positionX, positionY);
    			g.setFont(new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 15));
    			g.setColor(Color.black);
    		}
    		else
    		{
    			g.drawString("Name: " + p.getName(), positionX, positionY);
    		}
    		positionY += 20;
    		g.drawString("Color: " + p.getColor(), positionX, positionY);
    		positionY += 20;
    		g.drawString("Balance: " + p.getCash(), positionX, positionY);
    		positionY += 40;
    	}
    }
    private void _drawTileInfo(Graphics g){
    	g.setFont(new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 15));
    
    	ArrayList<String> tileInfo = gameState.getTileInfo();
    	int positionY = 350;
    	final int positionX = 20;
    	
    	if(tileInfo.get(0) == "LuckSetback")
    	{
    		String str = "./img/sorteReves/" + tileInfo.get(1);
    		Image imgCard;
			try {
				imgCard = ImageIO.read(new File(str));
				g.drawImage(imgCard, positionX, positionY, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			positionY += 260;
			g.drawString(tileInfo.get(2), positionX, positionY);
    	}
    	else if(tileInfo.get(0) == "Land")
    	{
    		String str = "./img/territorios/" + tileInfo.get(1);
    		Image imgCard;
			try {
				imgCard = ImageIO.read(new File(str));
				g.drawImage(imgCard, positionX, positionY, null);
			} catch (IOException e) {
				e.printStackTrace();
			}
			positionY += 300;
			g.drawString(tileInfo.get(2), positionX, positionY);
    	}
    	else
    	{
	    	for(String line: tileInfo) {
	    		// drawString doesn't handle \n so we have to do manually
	    		g.drawString(line, positionX, positionY);
	    		positionY += 20;
	    	}
    	}
    	
    }

    private void _drawDices(Graphics g) {
    	if(dice1Value == 0 || dice2Value == 0) return;
        String str1 = "./img/dados/die_face_" + dice1Value + ".png";
        String str2 = "./img/dados/die_face_" + dice2Value + ".png";
        Image imgDice1 = null;
        Image imgDice2 = null;
        try {
            imgDice1= ImageIO.read(new File(str1));
            imgDice2=ImageIO.read(new File(str2));
        }
        catch(IOException e2) {
            System.out.println(e2.getMessage());
            System.exit(1);
        }
        g.drawImage(imgDice1, 20, 100, 100, 100, null); 
        g.drawImage(imgDice2, 130, 100, 100, 100, null);
    }

    private void _setEvents() {
        _setPauseButtonEvent();
        _setQuiteButtonEvent();
    }

    private void _setPauseButtonEvent() {
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _cl.show(_panelCont, "2");
            }
        });
    }

    private void _setQuiteButtonEvent() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _controller.actionPerformed(new ChangeViewEvent(this, 200, "", ViewType.START_MENU));
            }
        });}

    @Override
    public void note(Observed o) {

    }
}
