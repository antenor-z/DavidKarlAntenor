package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import Model.GameException;
import Model.GameState;

public class Action1Ctl implements ActionListener {
    GameState gameState;
    Runnable callback;

    public Action1Ctl(Runnable callback) {
        this.gameState = GameState.getInstance();
        this.callback = callback;
    }

    public void actionPerformed(ActionEvent e) {
        String currentTileType = gameState.getTileType();

        try {
            if (currentTileType.equals("Land")) {
                if (gameState.canBuyLand()) {
                    try {
                        gameState.buyLand();
                    } catch (GameException e1) {
                        String[] options = {"OK"};
                        String[] propeties = gameState.turnGetPropeties().toArray(new String[0]);
                        final JComboBox<String> combo = new JComboBox<>(propeties);
                        JOptionPane.showOptionDialog(null, combo, "aa",
                                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
                                options[0]);
                    }
                } else if (gameState.canBuildHouse()) {
                    gameState.buildHouse();
                }
            } else if (currentTileType.equals("Company")) {
                gameState.buyCompany();
            }
            callback.run();
        } catch (GameException e1) {
            e1.printStackTrace();
        }
    }
}
