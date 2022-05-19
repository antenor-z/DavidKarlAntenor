package View.Pause;

import Model.Utils.GameLoader;
import Model.Utils.GameObject;
import View.MyPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class GameListPanel extends MyPanel {
    GameLoader gameLoader = new GameLoader();
    List<GameObject> games = null;
    JScrollPane scrollPane = new JScrollPane();

    public GameListPanel(CardLayout cl, JPanel panelCont, ActionListener controller) {
        super(cl, panelCont, controller);
        _initPanel();
        _loadGames();
    }

    private void _initPanel() {
        setPreferredSize(new Dimension(1200, 500));
        scrollPane.setPreferredSize(new Dimension(1200, 500));
    }

    private void _loadGames() {
        games = gameLoader.loadGames();
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        for(GameObject game : games ){
            dlm.addElement(game.toString());
        }
        JList list = new JList(dlm);
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        add(scrollPane);
    }
}
