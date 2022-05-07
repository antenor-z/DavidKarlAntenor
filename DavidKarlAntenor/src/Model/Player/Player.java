package Model.Player;

import Model.gameboard.Board;
import Model.gameboard.Tile;

public class Player {
	private int cash;
	int currentTile;
	int boardSize;
	Board board;
	public Player(int cash, Board board) {
		this.cash = cash;
		this.board = board;
		currentTile = 0;
		boardSize = board.getTiles().length;
	}
	public void advance(int n) {
		currentTile = (currentTile + n) % boardSize;
	}

	public void changeCash(int ammount) throws PlayerException
	{
		if(cash + ammount > 0)
		{
			cash += ammount;
		}
		else
		{
			throw new PlayerException("Cash less than zero");
		}
	}
}
