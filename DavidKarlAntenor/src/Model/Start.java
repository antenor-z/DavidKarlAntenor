package Model;

import java.util.ArrayList;
class Start extends Tile {
	public Start() {
		super(TileType.Start);
	}
	public ArrayList<String> print()
	{
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Start");
		return ret;
	}
	void execute(Player player) throws PlayerException
	{
		player.addOrSubCash(200);
	}
}
