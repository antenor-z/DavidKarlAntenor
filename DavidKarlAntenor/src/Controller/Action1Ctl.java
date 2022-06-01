package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GameException;
import Model.GameState;
import View.Gameboard.GamePanel;

public class Action1Ctl implements ActionListener{
	GamePanel gamePanel;
	GameState gameState;
	public Action1Ctl(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
		this.gameState = GameState.getInstance();
	}

	public void actionPerformed(ActionEvent e){
		String currentTileType = gameState.getCurrentTileType();
		if(currentTileType.equals("Land"))
		{
			if(gameState.canBuyLand())
			{
		        try
		        {
					gameState.buyLand();
				}
		        catch (GameException e1)
		        {
					e1.printStackTrace();
				}  
			}
			else if(gameState.canBuildHouse())
			{
				try 
				{
					gameState.buildHouse();
				}
				catch (GameException e1)
				{
					e1.printStackTrace();
				}  
			}
			gameState.dump();
	        gamePanel.gameControlPanel.action1.setVisible(false);
	        gamePanel.repaint();
		}
		else if (currentTileType.equals("Company"))
		{
			try
			{
				gameState.buyCompany();
			}
			catch (GameException e1)
			{
				e1.printStackTrace();
			}
			gameState.dump();
	        gamePanel.gameControlPanel.action1.setVisible(false);
	        gamePanel.repaint();
		}
	}
}
