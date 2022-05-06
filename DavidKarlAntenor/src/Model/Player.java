package Model;

public class Player {
	int cash;
	Tile currentTile;
	public Player(int cash, Tile initialTile) {
		this.cash = cash;
		currentTile = initialTile;
	}
}
