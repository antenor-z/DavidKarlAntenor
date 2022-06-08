package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Model.GameException;
import Model.GameState;
import View.Gameboard.GamePanel;

public class ThrowDiceCtl implements ActionListener{
	GamePanel gamePanel;
	public ThrowDiceCtl(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	public void actionPerformed(ActionEvent e) {
		GameState gameState = GameState.getInstance();
		gameState.nextPlayer();
		int[] dices = gameState.throwDice();
	    gamePanel.gameControlPanel.dice1Value = dices[0];
	    gamePanel.gameControlPanel.dice2Value = dices[1];

    	try {gameState.goFoward(dices[0], dices[1]);}
    	catch (Exception e2) {System.out.println(e2.getMessage());}
    	if(gameState.getTileType().equals("Land"))
        {
        		if (gameState.canBuyLand())
        		{
	        		gamePanel.gameControlPanel.action1.setText("Buy Land");
	        		gamePanel.gameControlPanel.action1.setVisible(true);
        		}
        		else if(gameState.canBuildHouse())
        		{
        			gamePanel.gameControlPanel.action1.setText("Build House");
	        		gamePanel.gameControlPanel.action1.setVisible(true);
        		}	
	        	else if(gameState.canBuildHotel())
	        	{
	        		gamePanel.gameControlPanel.action1.setText("Build House");
	        		gamePanel.gameControlPanel.action1.setVisible(true);
	        		gamePanel.gameControlPanel.action2.setText("Build Hotel");
	        		gamePanel.gameControlPanel.action2.setVisible(true);
        		}
        		else
        		{
        			try {
						gameState.landPayRent();
					} catch (GameException e1) {
						e1.printStackTrace();
        		}
        		}
        }
		else if(gameState.getTileType().equals("Company"))
		{
        		if (gameState.companyGetOwner() == null)
        		{
	        		gamePanel.gameControlPanel.action1.setText("Buy Company");
	        		gamePanel.gameControlPanel.action1.setVisible(true);
        		}
        		else if(gameState.companyGetOwner() != gameState.turn)
        		{
        			try
        			{
						gameState.companyPayRent(gameState.dices[0], gameState.dices[1]);
					}
        			catch (GameException e1)
        			{
						e1.printStackTrace();
					}
        		}
        }
		else if(gameState.getTileType().equals("Money")) 
		{
        	gameState.moneyExecute();
        }
		else if(gameState.getTileType().equals("LuckSetback"))
		{
			try
			{
				gameState.luckSetbackPickCard();
			}
			catch (GameException e1)
			{
				e1.printStackTrace();
			}
        }
		else if(gameState.getTileType().equals("GoToPrision"))
		{
        		gameState.gotoPrision();
        }
		else {
			gamePanel.gameControlPanel.action1.setVisible(false);
			gamePanel.gameControlPanel.action2.setVisible(false);
		}
        gamePanel.repaint();
        gameState.dump();
	}
}
