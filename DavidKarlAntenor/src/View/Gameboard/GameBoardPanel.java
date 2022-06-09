package View.Gameboard;

import Model.GameException;
import Model.GameState;
import Model.Player;
import Model.PlayerColor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class GameBoardPanel extends JPanel {
    ArrayList<Image> pinsImg = new ArrayList<Image>();
    //Board board;
    public GameBoardPanel() throws GameException {
		setPreferredSize(new Dimension(700, 700));
		loadPinsImages();
    }

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
    	g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
    	    RenderingHints.VALUE_ANTIALIAS_ON);

		super.paintComponent(g);
		Image i = null;
		try {
			i=ImageIO.read(new File("./img/tabuleiro.png"));
		}
		catch(IOException e2) {
			System.out.println(e2.getMessage());
			System.exit(1);
		}
		g.drawImage(i, 0, -10, null);
		_drawPlayers(g);
	}
	 public void loadPinsImages() {
    	String str;
    	try {
    		for(int i = 0; i < 6; i++) {
    			str = "./img/pinos/pin" + i + ".png";
    			pinsImg.add(ImageIO.read(new File(str)));
    		}
		} catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
    }
	    private boolean isBetween(int n, int min, int max) {
    	if(n >= min && n < max) return true;
    	return false;
    }
    public int getXposition2(int tileNumber) {
    	final int spacingX = 55;
    	final int startX;
    	
    	if(isBetween(tileNumber, 0, 11) || isBetween(tileNumber, 20, 31))
    	{
	    	startX = 610;		
    	}
    	else
    	{
    		startX = 15;
    	}
    	if(isBetween(tileNumber, 0, 11)) {
    		return startX - tileNumber * spacingX;
    	}
    	else if(isBetween(tileNumber, 11, 20)) {
    		return startX + 105;
    	}
    	else if(isBetween(tileNumber, 20, 31)) {
    		return startX - (30 - tileNumber) * spacingX;
    	}
    	else if(isBetween(tileNumber, 31, 40)) {
    		return startX + 535;
    	}
    	return 0;
    }
    public int getYposition2(int tileNumber) {
    	final int spacingY = 52;
    	final int startY;
    
    	if(isBetween(tileNumber, 0, 11) || isBetween(tileNumber, 20, 31))
    	{
		    startY = 570;
    	}
    	else 
    	{
	    	startY = 535;
    	}

    	if(isBetween(tileNumber, 0, 11)) {
    		return startY;
    	}
    	else if(isBetween(tileNumber, 11, 20)) {
    		return startY - (tileNumber - 11) * spacingY;
    	}
    	else if(isBetween(tileNumber, 20, 31)) {
    		return startY - spacingY * 10 + 42;
    	}
    	else if(isBetween(tileNumber, 31, 40)) {
    		return startY - (39 - tileNumber) * spacingY;
    	}
    	return 0;
    }
    
    public int getXposition(int tileNumber, Model.PlayerColor color) {
    	final int spacingX = 56;
    	final int startX;
    	
    	if(isBetween(tileNumber, 0, 11) || isBetween(tileNumber, 20, 31))
    	{
	    	switch (color) {
		    	case Blue -> startX = 610;	
		    	case Yellow -> startX = 610;
		    	case Purple -> startX = 610;
		    	case Gray -> startX = 631;
		    	case Red -> startX = 631;
		    	case Orange -> startX = 631;
		    	default -> startX = 0;
	    	}
    	} else {
    		switch (color) {
	    	case Blue -> startX = 15;	
	    	case Yellow -> startX = 35;
	    	case Purple -> startX = 55;
	    	case Gray -> startX = 15;
	    	case Red -> startX = 35;
	    	case Orange -> startX = 55;
	    	default -> startX = 0;
    		}
    	}
    	if(isBetween(tileNumber, 0, 11)) {
    		return startX - tileNumber * spacingX;
    	}
    	else if(isBetween(tileNumber, 11, 20)) {
    		return startX;
    	}
    	else if(isBetween(tileNumber, 20, 31)) {
    		return startX - (30 - tileNumber) * spacingX;
    	}
    	else if(isBetween(tileNumber, 31, 40)) {
    		return startX + 610;
    	}
    	return 0;
    }
    public int getYposition(int tileNumber, Model.PlayerColor color) {
    	final int spacingY = 56;
    	final int startY;
    
    	if(isBetween(tileNumber, 0, 11) || isBetween(tileNumber, 20, 31))
    	{
	    	switch (color) {
		    	case Blue -> startY = 592;
		    	case Red -> startY = 592;
		    	case Yellow -> startY = 617;
		    	case Gray -> startY = 617;
		    	case Purple -> startY = 642;
		    	case Orange -> startY = 642;
		    	default -> startY = 0;}
    	} else {
	    		switch (color) {
	    		
	    		case Blue -> startY = 535;	
		    	case Yellow -> startY = 535;
		    	case Purple -> startY = 535;
		    	case Gray -> startY = 561;
		    	case Red -> startY = 561;
		    	case Orange -> startY = 561;
		    	default -> startY = 0;}
    	}

    	if(isBetween(tileNumber, 0, 11)) {
    		return startY;
    	}
    	else if(isBetween(tileNumber, 11, 20)) {
    		return startY - (tileNumber - 11) * spacingY;
    	}
    	else if(isBetween(tileNumber, 20, 31)) {
    		return startY - spacingY * 10 - 30;
    	}
    	else if(isBetween(tileNumber, 31, 40)) {
    		return startY - (39 - tileNumber) * spacingY;
    	}
    	return 0;
    }

	private void _drawPlayers(Graphics g) {
    	for (Player player: GameState.getInstance().players) {
    		int color = player.getColor().ordinal();
    		int x = getXposition(player.getTileNumber(), player.getColor());
    		int y = getYposition(player.getTileNumber(), player.getColor());
    		g.drawImage(pinsImg.get(color), x, y, 18, 27, null);
    	}
    	
    	//SHOW ALL
    	/*
    	for (int i = 0; i < 40; i++) {
    		for(PlayerColor p: PlayerColor.values())
    		{
	    		int x = getXposition(i, p);
	    		int y = getYposition(i, p);
	    		g.drawImage(pinsImg.get(p.ordinal()), x, y, 18, 27, null);
    		}
    	}
    	*/
   
    	for (int i = 0; i < 40; i++) {
    		//for(PlayerColor p: PlayerColor.values())
    		//{
    			if(i == 3 || i == 4 || i == 6 || i == 8
    					|| i == 13 || i == 14 || i == 17 || i == 23
    					|| i == 26 || i == 28 || i == 33 || i == 34 || i == 36
    					|| i == 38)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 1)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("↓", x - 12, y + 14);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 9)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("↓", x - 12, y + 14);
		    		g.drawString("1", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 21)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("↑", x - 12, y + 14);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("1", x + 22, y + 14);
    			}
    			if(i == 29)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("↑", x - 12, y + 14);
		    		g.drawString("0", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 31)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("→", x + 28, y + 14);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 19)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("←", x - 10, y + 14);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 11)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("←", x - 10, y + 14);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			if(i == 39)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		g.setColor(Color.red);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.drawRect(x, y, 16, 16);
		    		g.drawRect(x + 16, y, 16, 16);
		    		g.drawString("→", x + 28, y + 14);
		    		g.drawString("3", x + 4, y + 14);
		    		g.drawString("0", x + 22, y + 14);
    			}
    			
    		//}
    	}
    }
}
