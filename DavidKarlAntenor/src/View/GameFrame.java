package View;

import Model.Event.ViewType;
import View.Gameboard.GameBoardView;
import View.Pause.PauseBtnListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameView extends GameFrame {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    JPanel buttons = new JPanel();

    public GameView(ActionListener controller) throws HeadlessException {
        super(controller, ViewType.GAME);

        _initFrame();
        add(new GameBoardView());

        JButton btnThrowDice = new JButton("Throw dice");
        JButton pauseButton = new JButton("Pause");
        JButton passButton = new JButton("Pass");

        buttons.setLayout(new GridLayout(1, 3));
        buttons.setBounds(windowSizeX - 480, 50, 450, 30);
        add(buttons);

        buttons.add(passButton);
        buttons.add(pauseButton);
        buttons.add(btnThrowDice);
        pauseButton.addActionListener(new PauseBtnListener(controller));
    }

    private void _initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(windowSizeX, windowSizeY);
        setLayout(null);
        setResizable(false);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }
}
