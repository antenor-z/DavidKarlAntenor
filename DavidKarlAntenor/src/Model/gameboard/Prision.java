package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

import java.util.ArrayList;

import Model.TileType;
public class Prision extends Tile {
	public Prision() {
		super(TileType.Prision);
	}
	ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("I'm a Prision tile");
		return ret;
	}
	void getOut(Player player, int diceNumber1, int diceNumber2) throws PlayerException {
		if(diceNumber1 == diceNumber2) {
			player.goFoward(diceNumber1 + diceNumber2);
		}
	}
}
