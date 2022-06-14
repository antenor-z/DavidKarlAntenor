package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

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
		String currentTileType = gameState.getTileType();
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
		        	
		        	String[] options = {"OK"};
		        	String[] propeties = gameState.turnGetPropeties().toArray(new String[0]);
		        	final JComboBox<String> combo = new JComboBox<>(propeties);
		        	int selection = JOptionPane.showOptionDialog(null, combo, "aa",
		        		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options,
		        		        options[0]);
		        	System.out.println(selection);
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
