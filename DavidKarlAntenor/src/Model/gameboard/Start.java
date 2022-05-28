package Model.gameboard;

import Model.Player.Player;
import Model.Player.PlayerException;

import java.util.ArrayList;

import Model.TileType;
public class Start extends Tile {
	public Start() {
		super(TileType.Start);
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("I'm a Start tile");
		return ret;
	}
	void execute(Player player) throws PlayerException
	{
		player.addOrSubCash(200);
	}
}
