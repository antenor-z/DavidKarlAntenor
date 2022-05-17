package View.Gameboard;

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

    public GameBoardView() {
        setBounds(200, 0, windowSizeX, windowSizeY);
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
	}
}