package View.Gameboard;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import Model.GameSettings;
import View.MyPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class GameControlPanel extends MyPanel {
    GameSettings settings = GameSettings.getInstance();
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel buttons = new JPanel(new GridBagLayout());
    JButton btnThrowDice = new JButton("Throw dice");
    JButton pauseButton = new JButton("Pause");
    JButton quiteButton = new JButton("Quit");

    private int dice1Value = 1, dice2Value = 1;

    public GameControlPanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initPanel();
        _setEvents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        _drawDices(g);
        _drawCard(g);
    }

    private void _initPanel() {
        setPreferredSize(new Dimension(500, 700));
        buttons.setBackground(Color.BLACK);
        buttons.add(btnThrowDice);
        buttons.add(pauseButton);
        buttons.add(quiteButton);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);
    }

    private void _drawCard(Graphics g) {
        String str = "./img/sorteReves/chance" + 30 + ".png";
        Image i = null;
        try {
            i=ImageIO.read(new File(str));
        }
        catch(IOException e2) {
            System.out.println(e2.getMessage());
            System.exit(1);
        }
        g.drawImage(i, 0, 400, null);
    }

    private void _drawDices(Graphics g) {
        String str1 = "./img/dados/die_face_" + dice1Value + ".png";
        String str2 = "./img/dados/die_face_" + dice2Value + ".png";
        Image imgDice1 = null;
        Image imgDice2 = null;
        try {
            imgDice1= ImageIO.read(new File(str1));
            imgDice2=ImageIO.read(new File(str2));
        }
        catch(IOException e2) {
            System.out.println(e2.getMessage());
            System.exit(1);
        }
        g.drawImage(imgDice1, 0, 100, 100, 100, null);
        g.drawImage(imgDice2, 110, 100, 100, 100, null);
    }

    private void _setEvents() {
        _setPauseButtonEvent();
        _setQuiteButtonEvent();
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
