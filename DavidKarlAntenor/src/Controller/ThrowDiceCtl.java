package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GameState;
import Model.TileType;
import Model.Deck.Exception.DeckException;
import Model.Player.PlayerException;
import Model.gameboard.Company;
import Model.gameboard.CompanyException;
import Model.gameboard.GoToPrision;
import Model.gameboard.Land;
import Model.gameboard.LandException;
import Model.gameboard.LuckSetback;
import Model.gameboard.Money;
import Model.gameboard.Prision;
import Model.gameboard.Tile;
import View.MyPanel;
import View.Gameboard.GameBoardPanel;
import View.Gameboard.GameControlPanel;
import View.Gameboard.GamePanel;

public class ThrowDiceCtl implements ActionListener{
	GamePanel gamePanel;
	public ThrowDiceCtl(GamePanel gamePanel)
	{
		this.gamePanel = gamePanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GameState gameState = GameState.getInstance();
		gameState.nextPlayer();
		Tile curentTile = gameState.getTile();
		int[] dices = gameState.throwDice();
	    gamePanel.gameControlPanel.dice1Value = dices[0];
	    gamePanel.gameControlPanel.dice2Value = dices[1];
		if(curentTile.tileType == TileType.Prision)
		{
			Prision prision = (Prision)curentTile;
			try {
				prision.getOut(gameState.turn, gameState.dices[0], gameState.dices[1]);
			
			} catch (PlayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else
		{
	    	try {gameState.turn.goFoward(dices[0] + dices[1]);}
	    	catch (Exception e2) {System.out.println(e2.getMessage());}
	    	curentTile = gameState.getTile();
		}
		
        if(curentTile.tileType == TileType.Land)
        {
        		Land land = (Land)curentTile;
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
	        		gamePanel.gameControlPanel.action2.setText("Build Hotel");
	        		gamePanel.gameControlPanel.action2.setVisible(true);
        		}
        		else
        		{
        			try {
						land.payRent(gameState.turn);
					} catch (LandException | PlayerException e1) {
						e1.printStackTrace();
					}
        		}
        }
        else if(curentTile.tileType == TileType.Company) {
        		Company company = (Company)curentTile;
        		if (company.getOwner() == null)
        		{
	        		gamePanel.gameControlPanel.action1.setText("Buy Copany");
	        		gamePanel.gameControlPanel.action1.setVisible(true);
        		}
        		else if(company.getOwner() != gameState.turn)
        		{
        			try {
						company.payRent(gameState.turn, gameState.dices[0] + gameState.dices[1]);
					} catch (CompanyException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
        		}
        }
        else if(curentTile.tileType == TileType.Money) {
        		Money money = (Money)curentTile;
        		money.execute(gameState.turn);
        }
        else if(curentTile.tileType == TileType.LuckSetback) {
        		LuckSetback luckSetback = (LuckSetback)curentTile;
			try {
				luckSetback.pickCard(gameState.turn);
			} catch (PlayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (DeckException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
        else if(curentTile.tileType == TileType.GoToPrision) {
        		GoToPrision goToPrison = (GoToPrision)curentTile;
        		goToPrison.gotoPrision(gameState.turn);
        }
		else {
			gamePanel.gameControlPanel.action1.setVisible(false);
			gamePanel.gameControlPanel.action2.setVisible(false);
		}
        gamePanel.repaint();
        gameState.dump();
	}
}
