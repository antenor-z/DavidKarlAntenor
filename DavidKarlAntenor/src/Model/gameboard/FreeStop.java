package Model.gameboard;
import java.util.ArrayList;

import Model.TileType;

public class FreeStop extends Tile {
	public FreeStop() {
		super(TileType.FreeStop);
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("I'm a freestop tile");
		return ret;
	}
}
