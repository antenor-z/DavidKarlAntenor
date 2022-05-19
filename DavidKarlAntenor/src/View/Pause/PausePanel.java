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

    JPanel buttonsGrid = new JPanel();
    JButton saveBtn = new JButton("Save");
    JButton loadBtn = new JButton("Load");
    JButton resumeBtn = new JButton("Resume");
    JButton quiteBtn = new JButton("Quite");

    public PausePanel(CardLayout cl, JPanel panelCont, ActionListener controller) throws HeadlessException {
        super(cl, panelCont, controller);
        _initPanel();
        _setEvents();
    }

    private void _initPanel() {
        add(closeBtn);
        gamesListPanel = new GameListPanel(_cl, _panelCont, _controller);
        add(gamesListPanel);
        closeBtn.setPreferredSize(new Dimension(1200, 30));
        _setButtonsGrid();
    }

    private void _setButtonsGrid() {
        buttonsGrid.setLayout(new GridLayout(2, 2));
        buttonsGrid.setPreferredSize(new Dimension(1200, 115));
        buttonsGrid.add(saveBtn);
        buttonsGrid.add(loadBtn);
        buttonsGrid.add(resumeBtn);
        buttonsGrid.add(quiteBtn);
        add(buttonsGrid);
    }

    private void _setEvents() {
        _setCloseButtonEvent();
        _setResumeButtonEvent();
    }

    private void _setResumeButtonEvent() {
        resumeBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                _cl.show(_panelCont, "1");
            }
        });
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
