package Model.Player;

import Model.gameboard.Board;
import Model.gameboard.Tile;

public class Player {
	private int cash;
	private int currentTile;
	private final int boardSize;
	Board board;
	PlayerColor color;
	public Player(int cash, Board board, PlayerColor color) {
		this.cash = cash;
		this.board = board;
		this.currentTile = 0;
		this.boardSize = board.getLength();
		this.color = color;
	}
	public boolean goFoward(int n){
		if(n <= 0) {
			return false;
		}
		currentTile = (currentTile + n) % boardSize;
		return true;
	}
	
	public int getCash()
	{
		return cash;
	}

	public void addOrSubCash(int ammount) throws PlayerException {
		if(cash + ammount >= 0)
		{
			cash += ammount;
		}
		else
		{
			throw new PlayerException("Cash less than zero");
		}
	}
	
	public int getCurrentTile() {
		return currentTile;
	}
	
	public PlayerColor getColor() {
		return color;
	}
	
	public void goToTile(int tile) {
		currentTile = tile;
	}
}
