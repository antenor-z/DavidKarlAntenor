package View.Gameboard;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import Model.Player.Player;
import Model.Player.PlayerException;
import Model.GameSettings;
import Model.PlayerColor;
import View.MyPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameControlPanel extends MyPanel {
    GameSettings settings = GameSettings.getInstance();
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel buttons = new JPanel(new GridBagLayout());
    JButton btnThrowDice = new JButton("Throw dice");
    JButton pauseButton = new JButton("Pause");
    JButton quitButton = new JButton("Quit");
    // These buttons change name depending of the current player
    // Action 1 may be, for example, buy house or buy company
    public JButton action1 = new JButton("Action 1");
    public JButton action2 = new JButton("Action 2");
    Model.GameState gameState = Model.GameState.getInstance();

    public int dice1Value = 0;
	public int dice2Value = 0;
	String cardImgPath = "";

    public GameControlPanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initPanel();
        _setEvents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        _drawDices(g);
        _drawCard(g);
        _drawPlayersStatus(g);
    }

    private void _initPanel() {
        setPreferredSize(new Dimension(500, 700));
        buttons.setBackground(Color.BLACK);
        buttons.add(btnThrowDice);
        buttons.add(pauseButton);
        buttons.add(quitButton);
        buttons.add(action1);
        buttons.add(action2);
        action1.setVisible(false);
        action2.setVisible(false);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);
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
			case Orange: {return Color.orange;}
			case Yellow: {return Color.yellow;}
			case Gray: {return Color.gray;}
			case Purple: {return Color.pink;}
			default: {return Color.black;}
    	}
    }
    private void _drawPlayersStatus(Graphics g) {
    	g.setFont(new Font(g.getFont().getFamily(), Font.BOLD, 20));
    	int positionY = 150;
    	if(gameState.turn == null) {
    		g.drawString("Turn: ---", 300, positionY);
    	} else {
	    	g.setColor(toJavaColor(gameState.turn.getColor()));
	    	String t = "Turn: " + gameState.turn.getName() + " (" + gameState.turn.getColor() + ")";
	    	g.drawString(t, 300, positionY);
	    	g.setColor(Color.black);
    	}
    	positionY += 40;
    	for(Player p: gameState.players) {
    		// drawString doesn't handle \n so we have to do manually
    		g.drawString("Name: " + p.getName(), 300, positionY);
    		positionY += 20;
    		g.drawString("Color: " + p.getColor(), 300, positionY);
    		positionY += 20;
    		g.drawString("Balance: " + p.getCash(), 300, positionY);
    		positionY += 40;
    	}
    }
    private void _drawTileInfo(Graphics g) {
    	//gameState.getTileInfo();
    	// TODO
    	
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
        g.drawImage(imgDice1, 0, 100, 100, 100, null);
        g.drawImage(imgDice2, 110, 100, 100, 100, null);
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
}
