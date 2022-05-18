package View.Gameboard;

import View.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends MyPanel {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    GameBoardPanel gameBoardPannel = new GameBoardPanel();
    JPanel buttons = new JPanel(new GridBagLayout());
    JButton btnThrowDice = new JButton("Throw dice");
    JButton pauseButton = new JButton("Pause");
    JButton passButton = new JButton("Pass");

    public GamePanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initFrame();

        add(gameBoardPannel);
        buttons.setBounds(windowSizeX - 500, 50, 450, 30);
        buttons.add(btnThrowDice);
        buttons.add(pauseButton);
        buttons.add(passButton);
        add(buttons);
        _setPauseButtonEvent();
    }

    private void _initFrame() {
        setSize(windowSizeX, windowSizeY);
        setLayout(new GridLayout(1, 2));
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }

    private void _setPauseButtonEvent() {
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _cl.show(_panelCont, "2");
            }
        });
    }
}
