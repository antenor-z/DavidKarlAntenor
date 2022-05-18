package View.Gameboard;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import View.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends MyPanel {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    GridBagConstraints gbc = new GridBagConstraints();

    GameBoardPanel gameBoardPannel = new GameBoardPanel();
    JPanel buttons = new JPanel(new GridBagLayout());
    JButton btnThrowDice = new JButton("Throw dice");
    JButton pauseButton = new JButton("Pause");
    JButton quiteButton = new JButton("Quit");

    public GamePanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initFrame();
        _setLeftPannel();
        _setRightPanel();
        _setEvents();
    }

    private void _setEvents() {
        _setPauseButtonEvent();
        _setQuiteButtonEvent();
    }

    private void _setLeftPannel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(gameBoardPannel, gbc);
    }

    private void _setRightPanel() {
        buttons.setPreferredSize(new Dimension(500, 700));
        buttons.setBounds(windowSizeX - 500, 50, 450, 30);
        buttons.add(btnThrowDice);
        buttons.add(pauseButton);
        buttons.add(quiteButton);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);
    }

    private void _initFrame() {
        setSize(windowSizeX, windowSizeY);
        setLayout(new GridBagLayout());
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

    private void _setQuiteButtonEvent() {
        quiteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _controller.actionPerformed(new ChangeViewEvent(this, 200, "", ViewType.START_MENU));
            }
        });
    }
}
