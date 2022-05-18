package View.Pause;

import Model.GameSettings;
import View.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanel extends MyPanel {
    JButton closeBtn = new JButton("close");

    public PausePanel(CardLayout cl, JPanel panelCont, ActionListener controller) throws HeadlessException {
        super(cl, panelCont, controller);
        add(closeBtn);
        _setCloseButtonEvent();
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
