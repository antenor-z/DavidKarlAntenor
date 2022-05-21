package Model.Player;

import Model.PlayerColor;
import Model.gameboard.Board;
import Model.gameboard.Tile;

public class Player {
	private int cash;
	private int currentTile;
	private final int boardSize;
	private PlayerColor color;
	public Player(int cash, int boardSize, PlayerColor color)
	{
		this.cash = cash;
		this.currentTile = 0;
		this.boardSize = boardSize;
		this.color = color;
	}
	public void goFoward(int n) throws PlayerException 
	{
		if(n <= 0) 
		{
			throw new PlayerException("Can't go backwards");
		}
		else
		{
			currentTile = (currentTile + n) % boardSize;
		}
	}
	
	public int getCash()
	{
		return cash;
	}

	public void addOrSubCash(int ammount) throws PlayerException
	{
		if(cash + ammount >= 0)
		{
			cash += ammount;
		}
		else
		{
			throw new PlayerException("Cash less than zero");
		}
	}
	
	public int getCurrentTile()
	{
		return currentTile;
	}
	
	public PlayerColor getColor()
	{
		return color;
	}
	
	public void goToTile(int tile)
	{
		currentTile = tile;
	}
}
