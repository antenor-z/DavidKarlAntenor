package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GameState;
import Model.TileType;
import Model.Player.PlayerException;
import Model.gameboard.CompanyException;
import Model.gameboard.Land;
import Model.gameboard.LandException;
import Model.gameboard.Tile;
import View.MyPanel;
import View.Gameboard.GameBoardPanel;
import View.Gameboard.GameControlPanel;
import View.Gameboard.GamePanel;

public class Action1Ctl implements ActionListener{
	GamePanel gamePanel;
	GameState gameState = GameState.getInstance();
	public Action1Ctl(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e){
		Tile curentTile = gameState.getTile();
		switch(curentTile.tileType) {
		case Land:
			if(gameState.canBuyLand())
			{
		        try {
					gameState.buyLand();
				} catch (LandException | PlayerException e1) {
					e1.printStackTrace();
				}  
			}
			else if(gameState.canBuildHouse())
			{
				 try {
					gameState.buildHouse();
				} catch (LandException | PlayerException e1) {
					e1.printStackTrace();
				}  
			}
			gameState.dump();
	        gamePanel.gameControlPanel.action1.setVisible(false);
	        gamePanel.repaint();
		case Company:
			try {
				gameState.buyCompany();
			} catch (PlayerException | CompanyException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			gameState.dump();
	        gamePanel.gameControlPanel.action1.setVisible(false);
	        gamePanel.repaint();
		
		default:
			break;
		}
	}

}
