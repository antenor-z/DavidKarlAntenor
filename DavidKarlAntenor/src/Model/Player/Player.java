package Model.Player;

import Model.gameboard.Tile;

public class Player {
	int cash;
	Tile currentTile;
	public Player(int cash, Tile initialTile) {
		this.cash = cash;
		currentTile = initialTile;
	}
	public void advance(int n) {
		
	}
}
