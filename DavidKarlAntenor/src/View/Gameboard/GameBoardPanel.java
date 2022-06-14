package View.Gameboard;

import Model.GameException;
import Model.GameState;
import Model.Observed;
import Model.Player;
import Model.PlayerColor;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
	public class GameBoardPanel extends JPanel implements Model.Observer {
    ArrayList<Image> pinsImg = new ArrayList<Image>();
	GameState gameState = GameState.getInstance();

    public GameBoardPanel() throws GameException {
		setPreferredSize(new Dimension(700, 700));
		loadPinsImages();
		gameState.addObserver(this);
    }

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2 = (Graphics2D) g;
		Image i = null;

		try {
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			i = ImageIO.read(new File("./img/tabuleiro.png"));
			g.drawImage(i, 0, -10, null);
			_drawPlayers(g);
		}
		catch(IOException e2) {
			System.out.println("paintComponent: " + e2.getMessage());
			System.exit(1);
		}
	}
	 public void loadPinsImages() {
    	String str;
    	try {
    		for(int i = 0; i < 6; i++) {
    			str = "./img/pinos/pin" + i + ".png";
    			pinsImg.add(ImageIO.read(new File(str)));
    		}
		} catch(IOException e) {
			System.out.println("loadPinsImages: " + e.getMessage());
			System.exit(1);
		}
    }
	    private boolean isBetween(int n, int min, int max) {
    	if(n >= min && n < max) return true;
    	return false;
    }
    public int getXposition2(int tileNumber) {
    	final int spacingX = 56;
    	final int startX;
    	
    	if(isBetween(tileNumber, 0, 11) || isBetween(tileNumber, 20, 31))
    	{
	    	startX = 616;		
    	}
    	else
    	{
    		startX = 15;
    	}
    	if(isBetween(tileNumber, 0, 11)) {
    		return startX - tileNumber * spacingX;
    	}
    	else if(isBetween(tileNumber, 11, 20)) {
    		return startX + 93;
    	}
    	else if(isBetween(tileNumber, 20, 31)) {
    		return startX - (30 - tileNumber) * spacingX;
    	}
    	else if(isBetween(tileNumber, 31, 40)) {
    		return startX + 545;
    	}
    	return 0;
    }
    public int getYposition2(int tileNumber) {
    	final int spacingY = 55;
    	final int startY;
    
    	if(isBetween(tileNumber, 0, 11) || isBetween(tileNumber, 20, 31))
    	{
		    startY = 570;
    	}
    	else 
    	{
	    	startY = 550;
    	}

    	if(isBetween(tileNumber, 0, 11)) {
    		return startY;
    	}
    	else if(isBetween(tileNumber, 11, 20)) {
    		return startY - (tileNumber - 11) * spacingY;
    	}
    	else if(isBetween(tileNumber, 20, 31)) {
    		return startY - spacingY * 10 + 71;
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

    Color toJavaColor(PlayerColor c)
    {
    	Color ret;
    	switch (c)
    	{
    		case Red -> ret = Color.red;
    		case Blue -> ret =  Color.blue;
    		case Gray -> ret =  Color.gray;
    		case Yellow -> ret =  Color.yellow;
    		case Purple -> ret =  Color.pink;
    		case Orange -> ret =  Color.orange;
    		//case PlayerColor.
    		default -> ret = Color.black;
    	}
    	return ret;
    }
	private void _drawPlayers(Graphics g) {
    	for (Player player: gameState.players) {
    		if(player.isBankrupt() == false)
    		{
	    		int color = player.getColor().ordinal();
	    		int x = getXposition(player.getTileNumber(), player.getColor());
	    		int y = getYposition(player.getTileNumber(), player.getColor());
	    		g.drawImage(pinsImg.get(color), x, y, 18, 27, null);
    		}
    	}
    	
    	ArrayList<ArrayList<Object>> fLandsCompany = gameState.getFormatedLandsCompany();
    	for (int i = 0; i < 40; i++) {
    		//for(PlayerColor p: PlayerColor.values())
    		//
    		if(!fLandsCompany.get(i).isEmpty())
    		{
    			fLandsCompany.get(i).get(0);
    			if(fLandsCompany.get(i).size() == 3)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		PlayerColor c = (PlayerColor)fLandsCompany.get(i).get(0);
		    		g.setColor(toJavaColor(c));
		    		g.fillRect(x, y, 32, 18);
		    		g.setColor(Color.white);
		    		g.setFont((new Font(g.getFont().getFamily(), Font.BOLD, 16)));	
		    		g.drawString(fLandsCompany.get(i).get(1).toString(), x + 4, y + 16);
		    		g.drawString(fLandsCompany.get(i).get(2).toString(), x + 20, y + 16);
		    		g.setColor(Color.black);
	    			if(i == 1)
	    			{
			    		g.drawString("↓", x - 12, y + 14);	
	    			}
	    			if( i == 9)
	    			{
			    		g.drawString("↓", x + 30, y + 14);	
	    			}
	    			if(i == 21)
	    			{
			    		g.drawString("↑", x + 30, y + 14);
	    			}
	    			if(i == 29)
	    			{
			    		g.drawString("↑", x - 12, y + 14);
	    			}
	    			if(i == 31)
	    			{
			    		g.drawString("→", x + 20, y + 28);
	    			}
	    			if(i == 39)
	    			{
			    		g.drawString("→", x + 20, y);
	    			}
	    			if(i == 11)
	    			{
			    		g.drawString("←", x, y);
	    			}
	    			if(i == 19)
	    			{
			    		g.drawString("←", x, y + 28);
	    			}
	    		}
    			if(fLandsCompany.get(i).size() == 1)
    			{
		    		int x = getXposition2(i);
		    		int y = getYposition2(i);
		    		
		    		PlayerColor c = (PlayerColor)fLandsCompany.get(i).get(0);
		    		g.setColor(toJavaColor(c));
		    		g.setFont((new Font(g.getFont().getFamily(), Font.TRUETYPE_FONT, 16)));
		    		g.fillRect(x, y, 16, 16);
    			}
    		}
    		//}
    	}
    }

	@Override
	public void note(Observed o) {
		repaint();
	}
}
