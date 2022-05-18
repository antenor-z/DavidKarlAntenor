package View.Gameboard;

import Model.Event.ChangeViewEvent;
import Model.Event.ViewType;
import Model.GameSettings;
import View.Exception.ViewException;
import View.MyPanel;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class GamePanel extends MyPanel {
    GameSettings settings = GameSettings.getInstance();
    GridBagConstraints gbc = new GridBagConstraints();
    GameBoardPanel gameBoardPannel = new GameBoardPanel();
    JPanel buttons = new JPanel(new GridBagLayout());
    JButton btnThrowDice = new JButton("Throw dice");
    JButton pauseButton = new JButton("Pause");
    JButton quiteButton = new JButton("Quit");

    private int dice1Value = 1, dice2Value = 1;

    public GamePanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initFrame();
        _setLeftPannel();
        _setRightPanel();
        _setEvents();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        _drawDices(g);
        _drawCard(g);
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
        g.drawImage(imgDice2, 0, 250, 100, 100, null);
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
        g.drawImage(i, settings.getGetMaxWidth() - 250, 400, null);
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
        buttons.setBounds(settings.getGetMaxWidth() - 500, 50, 450, 30);
        buttons.add(btnThrowDice);
        buttons.add(pauseButton);
        buttons.add(quiteButton);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(buttons, gbc);
    }

    private void _initFrame() {
        setSize(settings.getGetMaxWidth(), settings.getGetMaxHeight());
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
