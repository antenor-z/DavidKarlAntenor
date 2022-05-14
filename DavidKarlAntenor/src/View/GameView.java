package View;

import Model.Event.ViewType;
import View.Gameboard.GameBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameView extends GameFrame {
    final private int windowSizeX = 1280;
    final private int windowSizeY = 720;

    public GameView(ActionListener parent) throws HeadlessException {
        super(parent, ViewType.GAME);

        JPanel gameBoardView = new GameBoardView();
        add(gameBoardView);
        gameBoardView.setVisible(true);
        _initFrame();
    }

    private void _initFrame() {
        setSize(windowSizeX, windowSizeY);
        setLayout(null);
        setResizable(false);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }
}
