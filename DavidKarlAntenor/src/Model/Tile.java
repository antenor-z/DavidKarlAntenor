package Model;

import java.util.ArrayList;

class Tile {
	private TileType tileType;
	Tile(TileType tileType){
		this.tileType = tileType;
	}
	String getTileType()
	{
		return tileType.toString();
	}
	static private Player cardOwner;
	ArrayList<String> print() {
		ArrayList<String> ret = new ArrayList<String>();
		ret.add("Tile");
		return ret;
	}
	static Player getCardOwner()
	{
		return cardOwner;
	}
	static void setCardOwner(Player p)
	{
		cardOwner = p;
	}
}
