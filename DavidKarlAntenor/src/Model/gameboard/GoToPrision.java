package Model.gameboard;

import Model.Player.Player;

public class GoToPrision extends Tile{
	void print(){
		System.out.println("I'm a GoToPrision tile");
	}
	void gotoPrision(Player player) {
		player.goToTile(10);
	}
}
