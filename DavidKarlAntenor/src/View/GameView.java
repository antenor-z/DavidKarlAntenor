package View;

import Model.Event.ViewType;
import View.Gameboard.GameBoardView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameView extends GameFrame {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;
    JPanel leftPanel = new JPanel();
    JPanel rightPanel = new JPanel();

    public GameView(ActionListener parent) throws HeadlessException {
        super(parent, ViewType.GAME);
        _initFrame();

        leftPanel.setBounds(0, 0, windowSizeX / 2, windowSizeY);
        leftPanel.setVisible(true);
        leftPanel.add(new GameBoardView());
        add(leftPanel);

        rightPanel.setBounds(windowSizeX / 2, 0, windowSizeX / 2, windowSizeY);
        rightPanel.setVisible(true);
        rightPanel.setBackground(Color.BLACK);
        add(rightPanel);
    }

    private void _initFrame() {
        setTitle("Monopoly");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowSizeX, windowSizeY);
        setLayout(null);
        setResizable(false);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }
}
