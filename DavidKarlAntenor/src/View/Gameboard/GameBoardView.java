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

import javax.imageio.ImageIO;
import javax.swing.*;

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

    public GameBoardView() {
        setBounds(0, 0, windowSizeX, windowSizeY);
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
		g.drawImage(i, 0, 0, null);	
		_drawDices(g);	
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
}