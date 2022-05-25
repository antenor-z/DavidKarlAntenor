package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GameState;
import Model.TileType;
import Model.Player.PlayerException;
import Model.gameboard.LandException;
import Model.gameboard.Tile;
import View.MyPanel;
import View.Gameboard.GameBoardPanel;
import View.Gameboard.GameControlPanel;
import View.Gameboard.GamePanel;

public class Action1Ctl implements ActionListener{
	GamePanel gamePanel;
	public Action1Ctl(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameState gameState = GameState.getInstance();
        try {
			gameState.buyLand();
		} catch (LandException | PlayerException e1) {
			e1.printStackTrace();
		}
        gameState.dump();
        gamePanel.gameControlPanel.action1.setVisible(false);
        gamePanel.repaint();
	}

}
