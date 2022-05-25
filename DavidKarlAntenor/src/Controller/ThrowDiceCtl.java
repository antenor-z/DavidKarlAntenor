package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Model.GameState;
import Model.TileType;
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
        int[] dices = gameState.throwDice();
        gameState.nextPlayer();
        gamePanel.gameControlPanel.dice1Value = dices[0];
        gamePanel.gameControlPanel.dice2Value = dices[1];
        try {gameState.turn.goFoward(dices[0] + dices[1]);}
        catch (Exception e2) {System.out.println(e2.getMessage());}
        Tile curentTile = gameState.getTile();
        gameState.dump();
        switch(curentTile.tileType) {
        	case Land:
        		System.out.println("Land");
        		gamePanel.gameControlPanel.action1.setText("Buy Land");
        		gamePanel.gameControlPanel.action1.setVisible(true);
			default:
				break;
        }
        gamePanel.repaint();
	}

}
