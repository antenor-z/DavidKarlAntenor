package View.Gameboard;

import Model.GameSettings;
import Model.PlayerColor;
import Model.Player.Player;
import Model.gameboard.Board;
import View.Exception.ViewException;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameBoardPanel extends JPanel {
	ArrayList<Player> playersList = new ArrayList<Player>();
    ArrayList<Image> pinsImg = new ArrayList<Image>();
    public GameBoardPanel() {
		setPreferredSize(new Dimension(700, 700));
		loadPinsImages();
		Board b = new Board();
		Player p1 = new Player(4000, b,PlayerColor.Blue);
		Player p2 = new Player(4000, b,PlayerColor.Red);
		Player p3 = new Player(4000, b,PlayerColor.Orange);
		p1.goFoward(23);
		p3.goFoward(39);
		playersList.add(p1);
		playersList.add(p2);
		playersList.add(p3);
    }

	public void paintComponent(Graphics g) {
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
    	} else {int a = 35;
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
	
	public void drawPlayers(ArrayList<Player> playersList) {
    		this.playersList = playersList;
    	}
	private void _drawPlayers(Graphics g) {
    	for (Player player: playersList) {
    		int color = player.getColor().ordinal();
    		int x = getXposition(player.getCurrentTile(), player.getColor());
    		int y = getYposition(player.getCurrentTile(), player.getColor());
    		g.drawImage(pinsImg.get(color), x, y, 18, 27, null);
    	}
    	
    	/*//SHOW ALL
    	for (int i = 0; i < 40; i++) {
    		for(PlayerColor p: PlayerColor.values())
    		{
	    		int x = getXposition(i, p);
	    		int y = getYposition(i, p);
	    		g.drawImage(pinsImg.get(p.ordinal()), x, y, 18, 27, null);
    		}
    	}*/
    }
	
}
