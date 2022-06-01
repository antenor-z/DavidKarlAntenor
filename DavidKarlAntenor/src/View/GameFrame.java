package View;

import Model.Event.ViewType;
import Model.GameSettings;
import Model.PlayerException;
import View.Gameboard.GamePanel;
import View.Pause.PausePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends MyFrame {
    JPanel panelCont = new JPanel();
    CardLayout cl = new CardLayout();
    GameSettings settings = GameSettings.getInstance();

    public GameFrame(ActionListener controller) throws HeadlessException, PlayerException {
        super(controller, ViewType.GAME);
        panelCont.setLayout(cl);
        panelCont.add(new GamePanel(cl, panelCont, controller), "1");
        panelCont.add(new PausePanel(cl, panelCont, controller), "2");
        panelCont.setBounds(0, 0, settings.getGetMaxWidth(), settings.getGetMaxHeight());
        cl.show(panelCont, "1");
        add(panelCont);
        _initFrame();
    }

    private void _initFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(settings.getGetMaxWidth(), settings.getGetMaxHeight());
        setLayout(null);
        setResizable(false);
        setLocation((Toolkit.getDefaultToolkit().getScreenSize().width) / 2 - getWidth() / 2,
                (Toolkit.getDefaultToolkit().getScreenSize().height) / 2 - getHeight() / 2);
    }
}