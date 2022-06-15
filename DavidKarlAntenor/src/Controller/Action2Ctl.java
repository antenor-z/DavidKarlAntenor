package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.GameException;
import Model.GameState;

public class Action2Ctl implements ActionListener {
    GameState gameState;
    Runnable callback;

    public Action2Ctl(Runnable callback) {
        this.gameState = GameState.getInstance();
        this.callback = callback;
    }

    public void actionPerformed(ActionEvent e) {
        String currentTileType = gameState.getTileType();

        try {
            if (currentTileType != null && currentTileType.equals("Land")) {
                if (gameState.canBuildHotel()) {
                   gameState.buildHotel();
                }
            }
            callback.run();
        } catch (GameException e1) {
            e1.printStackTrace();
        }
    }
}
