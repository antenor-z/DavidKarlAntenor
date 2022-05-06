package Model.Player;

import Model.gameboard.Tile;

public class Player {
	private int cash;
	Tile currentTile;
	public Player(int cash, Tile initialTile) {
		this.cash = cash;
		currentTile = initialTile;
	}
	public void advance(int n) {
		
	}
	public void deposit(int ammount)
	{
		cash += ammount;
	}
	public void withdraw(int ammount) throws PlayerException
	{
		if(cash - ammount > 0)
		{
			cash -= ammount;
		}
		else
		{
			throw new PlayerException("Cash less than zero");
		}
	}
}
