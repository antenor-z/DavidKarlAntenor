package Model.gameboard;

public class Board {
	Tile[] tiles;
	final int numberOfTiles = 40;
	public Board()
	{
		tiles = new Tile[numberOfTiles];
	}
}
