package View.Gameboard;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameBoardView extends JPanel implements KeyListener {
    ImageIcon img = new ImageIcon("../img/tabuleiro.png");
    JLabel label = new JLabel();
    final private int windowSizeX = 1280;
    final private int windowSizeY = 720;

    public GameBoardView() {
        setSize(windowSizeX, windowSizeY);
        label.setIcon(img);
        add(label);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
