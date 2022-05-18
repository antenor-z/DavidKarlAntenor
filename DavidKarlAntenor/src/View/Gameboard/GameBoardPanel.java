package View.Gameboard;

import Model.GameSettings;
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
    // private int dice1Value = 1, dice2Value = 1;

    public GameBoardPanel() {
		setPreferredSize(new Dimension(700, 700));
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
		g.drawImage(i, -7, -7, null);
		// _drawDices(g);
		//_drawPlayers(g);
		// _drawCard(g);
	}
/*    public void drawDices(int value1, int value2) throws ViewException {
    	if(value1 > 5 || value1 < 0 || value2 > 5 || value2 < 0) {
    		throw new ViewException("Dice value outside range 1: " + value1 + " 2: " + value2);
    	}
    	value1++; value2++;
    	this.dice1Value = value1;
    	this.dice2Value = value2;
    	repaint();
    }*/
    /*private void _drawDices(Graphics g) {
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
    private void _drawPlayers(Graphics g) {
    	String str;
    	ArrayList<Image> pinsImg = new ArrayList<Image>();
    	try {
    		for(int i = 0; i < 6; i++) {
    			str = "./img/pinos/pin" + i + ".png";
    			pinsImg.add(ImageIO.read(new File(str)));
    		}
		}
		catch(IOException e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}
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
    			g.drawImage(pinsImg.get(0), a[i][0]+0 , a[i][1]+ 0, 25, 38, null);
    			g.drawImage(pinsImg.get(1), a[i][0]+30, a[i][1]+ 0, 25, 38, null);
    			g.drawImage(pinsImg.get(2), a[i][0]+60, a[i][1]+ 0, 25, 38, null);
    			g.drawImage(pinsImg.get(3), a[i][0]+0 , a[i][1]+15, 25, 38, null);
    			g.drawImage(pinsImg.get(4), a[i][0]+30, a[i][1]+15, 25, 38, null);
    			g.drawImage(pinsImg.get(5), a[i][0]+60, a[i][1]+15, 25, 38, null);
    		}
    		else {
    			g.drawImage(pinsImg.get(0), a[i][0]+0,  a[i][1]+ 0, 25, 38, null);
    			g.drawImage(pinsImg.get(1), a[i][0]+0,  a[i][1]+20, 25, 38, null);
    			g.drawImage(pinsImg.get(2), a[i][0]+0,  a[i][1]+40, 25, 38, null);
    			g.drawImage(pinsImg.get(3), a[i][0]+25, a[i][1]+ 0, 25, 38, null);
    			g.drawImage(pinsImg.get(4), a[i][0]+25, a[i][1]+20, 25, 38, null);
    			g.drawImage(pinsImg.get(5), a[i][0]+25, a[i][1]+40, 25, 38, null);
    		}	
    	}
    }*/
}