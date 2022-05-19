package View.Pause;

import Model.Utils.GameLoader;
import Model.Utils.GameObject;
import View.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PausePanel extends MyPanel {
    JButton closeBtn = new JButton("close");
    GameListPanel gamesListPanel = null;

    public PausePanel(CardLayout cl, JPanel panelCont, ActionListener controller) throws HeadlessException {
        super(cl, panelCont, controller);
        _initPanel();
        _setCloseButtonEvent();
    }

    private void _initPanel() {
        add(closeBtn);
        gamesListPanel = new GameListPanel(_cl, _panelCont, _controller);
        add(gamesListPanel);
        closeBtn.setPreferredSize(new Dimension(1200, 30));
    }

    private void _setCloseButtonEvent() {
        closeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _cl.show(_panelCont, "1");
            }
        });
    }
}
