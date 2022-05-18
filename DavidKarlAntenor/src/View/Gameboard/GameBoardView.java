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
public class GameBoardView extends JPanel {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;
    private int dice1Value = 1, dice2Value = 1;
    PlayersLane playersLane = new PlayersLane();
    ArrayList<Player> playersList = new ArrayList<Player>();
    ArrayList<Image> pinsImg = new ArrayList<Image>();

    public GameBoardView() {
        setBounds(0, 0, windowSizeX, windowSizeY);
        loadPinsImages();
        loadPinsPositionArrays();
        Board b = new Board();
        Player p1 = new Player(4000, b,PlayerColor.Blue);
        Player p2 = new Player(4000, b,PlayerColor.Red);
        playersList.add(p1);
        p1.goFoward(23);
        playersList.add(p2);
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
    public int getXposition(int tileNumber, Model.PlayerColor color) {
    	final int spacingX = 56;
    	final int startX;
       	if(tileNumber <= 10 || (tileNumber >= 20 && tileNumber <= 30)) {
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
    		case Blue -> startX = 580;	
    		case Red -> startX = 600;
	    	case Yellow -> startX = 620;
	    	case Purple -> startX = 580;
	    	case Gray -> startX = 600;
	    	case Orange -> startX = 620;
	    	default -> startX = 0;
    		}
    	}
    	final int startX2 = startX - 10 * spacingX;
    	if(tileNumber <= 10) {
    		return startX - tileNumber * spacingX;
    	} 
    	else if (tileNumber > 10 && tileNumber <= 20) {
    		return startX2;
    	}
    	else if (tileNumber > 20 && tileNumber < 30) {
    		return startX2 + (tileNumber - 20) * spacingX;
    	}
    	else {
    		return startX;
    	}
    }
    public int getYposition(int tileNumber, Model.PlayerColor color) {
    	final int spacingY = 55;
    	final int startY;
    	if(tileNumber <= 10 || (tileNumber >= 20 && tileNumber <= 30)) {
	    	switch (color) {
		    	case Blue -> startY = 592;
		    	case Red -> startY = 592;
		    	case Yellow -> startY = 617;
		    	case Gray -> startY = 617;
		    	case Purple -> startY = 642;
		    	case Orange -> startY = 642;
		    	default -> startY = 0;
	    	}
    	} else {
    		switch (color) {
    		case Blue -> startY = 592;	
    		case Red -> startY = 592;
	    	case Yellow -> startY = 592;
	    	case Gray -> startY = 617;
	    	case Purple -> startY = 617;
	    	case Orange -> startY = 617;
	    	
	    	
	    	default -> startY = 0;
    		}
    	}
    	final int startY2 = startY - 10 * spacingY - 28;
    	if(tileNumber <= 10) {
    		return startY;
    	} 
    	else if (tileNumber > 10 && tileNumber < 20) {
    		return startY - (tileNumber - 10) * spacingY;
    	}
    	else if (tileNumber >= 20 && tileNumber < 30) {
    		return startY2;
    	}
    	else {
    		return startY2 + (tileNumber - 30) * spacingY;
    	}
    }
    public void loadPinsPositionArrays() {
    	int[][] a = new int[40][2];
    	try
		{
			String content = Files.readString(Path.of("./pinsPosition.json"));
			JSONObject obj = new JSONObject(content);
            JSONArray pins = obj.getJSONArray("pins");
      
            for (int i = 0; i < pins.length(); i++) {
                JSONArray pin = pins.getJSONArray(i);
                a[i][0] = pin.getInt(0);
                a[i][1] = pin.getInt(1);
            }
		}
		catch(Exception e)
		{
			System.out.println("Failed to read pinsPosition.json");
			System.out.println(e.getMessage());
		}
    	
    	for(int i = 0; i < 40; i++) {
    		if(i == 0 || (i >= 10 && i <= 20) || (i >= 30 && i <= 39)) {
    			playersLane.add(Model.PlayerColor.Red, a[i][0]+0, a[i][1]);
    			playersLane.add(Model.PlayerColor.Gray, a[i][0]+30, a[i][1]);
    			playersLane.add(Model.PlayerColor.Purple, a[i][0]+60, a[i][1]);
    			playersLane.add(Model.PlayerColor.Yellow, a[i][0]+0, a[i][1]+25);
    			playersLane.add(Model.PlayerColor.Orange, a[i][0]+30, a[i][1]+25);
    			playersLane.add(Model.PlayerColor.Blue, a[i][0]+60, a[i][1]+25);
    		}
    		else {
    			playersLane.add(Model.PlayerColor.Red, a[i][0], a[i][1]);
    			playersLane.add(Model.PlayerColor.Gray, a[i][0], a[i][1]+25);
    			playersLane.add(Model.PlayerColor.Purple, a[i][0], a[i][1]+50);
    			playersLane.add(Model.PlayerColor.Yellow, a[i][0]+25, a[i][1]);
    			playersLane.add(Model.PlayerColor.Orange, a[i][0]+25, a[i][1]+25);
    			playersLane.add(Model.PlayerColor.Blue, a[i][0]+25, a[i][1]+50);
    		}	
    	}
    }
    public void drawPlayers(ArrayList<Player> playersList) {
    	this.playersList = playersList;
    }

    private void _drawPlayers(Graphics g) {
    	/*
    	for (Player player: playersList) {
    		int color = player.getColor().ordinal();
    		int x = getXposition(player.getCurrentTile(), player.getColor());
    		int y = getYposition(player.getCurrentTile(), player.getColor());
    		g.drawImage(pinsImg.get(color), x, y, 18, 27, null);
    	}
    	*/
    	for (int i = 0; i < 40; i++) {
    		for(PlayerColor p: PlayerColor.values())
    		{
	    		int x = getXposition(i, p);
	    		int y = getYposition(i, p);
	    		g.drawImage(pinsImg.get(p.ordinal()), x, y, 18, 27, null);
    		}
    	}
    }
}