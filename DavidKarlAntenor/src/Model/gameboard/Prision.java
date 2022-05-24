package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;
import Model.TileType;
public class Prision extends Tile {
	public Prision() {
		super(TileType.Prision);
	}
	void print(){
		System.out.println("I'm a Prision tile");
	}
	void getOut(Player player, int diceNumber1, int diceNumber2) throws PlayerException {
		if(diceNumber1 == diceNumber2) {
			player.goFoward(diceNumber1 + diceNumber2);
		}
	}
}
