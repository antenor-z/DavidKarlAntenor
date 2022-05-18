package View;

import Model.Event.ViewType;
import View.Gameboard.GamePanel;
import View.Pause.PausePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class GameFrame extends MyFrame {
    final private int windowSizeX = 1200;
    final private int windowSizeY = 700;

    JPanel panelCont = new JPanel();
    CardLayout cl = new CardLayout();

    public GameFrame(ActionListener controller) throws HeadlessException {
        super(controller, ViewType.GAME);
        panelCont.setLayout(cl);
        panelCont.add(new GamePanel(cl, panelCont, controller), "1");
        panelCont.add(new PausePanel(cl, panelCont, controller), "2");
        panelCont.setBounds(0, 0, windowSizeX, windowSizeY);
        cl.show(panelCont, "1");
        add(panelCont);
        _initFrame();
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
