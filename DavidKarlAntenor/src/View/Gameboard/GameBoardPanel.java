package View.Gameboard;

import View.Exception.ViewException;
import View.Utils.MyImage;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.json.JSONArray;
import org.json.JSONObject;

import Model.PlayerColor;
import Model.Player.Player;
import Model.gameboard.Board;
import Model.gameboard.Company;
import Model.gameboard.FreeStop;
import Model.gameboard.GoToPrision;
import Model.gameboard.Land;
import Model.gameboard.LuckSetback;
import Model.gameboard.Money;
import Model.gameboard.Prision;
import Model.gameboard.Start;

/*
public class GameBoardView extends MyImage {
    ImageIcon img = new ImageIcon("../img/tabuleiro.png");
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    public GameBoardView() {
        setBounds(0, 0, windowSizeX, windowSizeY);
        setIcon(img);
    }
}
*/
public class GameBoardPanel extends JPanel {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;
    private int dice1Value = 1, dice2Value = 1;
    ArrayList<Player> playersList = new ArrayList<Player>();
    ArrayList<Image> pinsImg = new ArrayList<Image>();

    public GameBoardPanel() {
        setBounds(0, 0, windowSizeX, windowSizeY);
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
		_drawDices(g);
		_drawPlayers(g);
		_drawCard(g);
	}
    public void drawDices(int value1, int value2) throws ViewException {
    	if(value1 > 5 || value1 < 0 || value2 > 5 || value2 < 0) {
    		throw new ViewException("Dice value outside range 1: " + value1 + " 2: " + value2);
    	}
    	value1++; value2++;
    	this.dice1Value = value1;
    	this.dice2Value = value2;
    	repaint();
    }
    private void _drawDices(Graphics g) {
    	String str1 = "./img/dados/die_face_" + dice1Value + ".png";
    	String str2 = "./img/dados/die_face_" + dice2Value + ".png";
    	Image imgDice1 = null;
    	Image imgDice2 = null;
    	try {
			imgDice1=ImageIO.read(new File(str1));
			imgDice2=ImageIO.read(new File(str2));
		}
		catch(IOException e2) {
			System.out.println(e2.getMessage());
			System.exit(1);
		}
		g.drawImage(imgDice1, windowSizeX - 200, 100, 100, 100, null);
		g.drawImage(imgDice2, windowSizeX - 200, 250, 100, 100, null);
    }
    private void _drawCard(Graphics g) {
    	String str = "./img/sorteReves/chance" + 30 + ".png";
    	Image i = null;
		try {
			i=ImageIO.read(new File(str));
		}
		catch(IOException e2) {
			System.out.println(e2.getMessage());
			System.exit(1);
		}
		g.drawImage(i, windowSizeX - 250, 400, null);
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