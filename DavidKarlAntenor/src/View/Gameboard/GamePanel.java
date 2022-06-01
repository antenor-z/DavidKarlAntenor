package View.Gameboard;

import Model.GameException;
import Model.GameSettings;
import View.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class GamePanel extends MyPanel {
    GameSettings settings = GameSettings.getInstance();
    GridBagConstraints gbc = new GridBagConstraints();

    public GameBoardPanel gameBoardPanel = null;
    public GameControlPanel gameControlPanel = null;

    public GamePanel(CardLayout cl, JPanel panelCont, ActionListener controller) throws GameException {
        super(cl, panelCont, controller);
        gameControlPanel = new GameControlPanel(cl, panelCont, controller);
        gameBoardPanel = new GameBoardPanel();
        _initFrame();
        _setLeftPanel();
        _setRightPanel(); 
        _setThrowDiceEvent();
        _setAction1Event();
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameBoardPanel.repaint();
        gameControlPanel.repaint();
    }

    private void _setLeftPanel() {
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(gameBoardPanel, gbc);
    }

    private void _setRightPanel() {
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.BOTH;
        add(gameControlPanel, gbc);
    }

    private void _initFrame() {
        setSize(settings.getGetMaxWidth(), settings.getGetMaxHeight());
        setLayout(new GridBagLayout());
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }
    private void _setThrowDiceEvent() {
    	gameControlPanel.btnThrowDice.addActionListener(new Controller.ThrowDiceCtl(this));
    }
    private void _setAction1Event() {
    	gameControlPanel.action1.addActionListener(new Controller.Action1Ctl(this));
    }
}
