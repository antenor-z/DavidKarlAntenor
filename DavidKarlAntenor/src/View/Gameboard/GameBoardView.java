package View.Gameboard;

import View.Utils.MyImage;
import javax.swing.*;

public class GameBoardView extends MyImage {
    ImageIcon img = new ImageIcon("../img/tabuleiro.png");
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    public GameBoardView() {
        setVisible(true);
        setBounds(0, 0, windowSizeX, windowSizeY);
        setIcon(img);
    }
}
