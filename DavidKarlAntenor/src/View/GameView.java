package View;

import Model.Event.ViewType;
import View.Gameboard.GameBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameView extends GameFrame {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    public GameView(ActionListener parent) throws HeadlessException {
        super(parent, ViewType.GAME);

        _initFrame();
        add(new GameBoardView());
        // The gameboard must not use Swing components, the exceptions
        // are the button to throw the dice, and the button to save the game
        JButton btnThrowDice = new JButton("Throw dice");
        btnThrowDice.setBounds(windowSizeX - 200, 50, 150, 30);
        add(btnThrowDice);
    }

    private void _initFrame() {
        setSize(windowSizeX, windowSizeY);
        setLayout(null);
        setResizable(false);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }
}
