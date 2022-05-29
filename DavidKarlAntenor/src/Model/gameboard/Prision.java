package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

import java.util.ArrayList;

import Model.TileType;
public class Prision extends Tile {
	Player playerJustArrived = null;;
	public Prision() {
		super(TileType.Prision);
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Prision");
		return ret;
	}
	public void getOut(Player player, int diceNumber1, int diceNumber2) throws PlayerException {
		if(player == playerJustArrived)
		{
			if(diceNumber1 == diceNumber2) {
				player.goFoward(diceNumber1 + diceNumber2);
			}
			playerJustArrived = null;
		}
		else
			playerJustArrived = player;
	}
}
